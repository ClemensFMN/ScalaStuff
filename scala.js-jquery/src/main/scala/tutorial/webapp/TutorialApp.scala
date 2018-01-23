package tutorial.webapp

import org.scalajs.dom
import org.scalajs.dom.html._
import org.scalajs.jquery.{JQueryEventObject, jQuery}

object TutorialApp {
  def main(args: Array[String]): Unit = {
    jQuery(() => setupUI())
  }

  def setupUI(): Unit = {
    jQuery("body").append("<p>Hello World</p>")

    jQuery("#click-me-button").click(() => addClickedMessage())
    jQuery("#click-me-button-2").click(() => addClickedMessage2())

    jQuery("#color").change(() => colorHandler(_))
    jQuery("#color").submit(() => colorHandler(_))

    val canvas = dom.document.createElement("canvas").asInstanceOf[Canvas]
    val renderer = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.onmousedown = { (e: dom.MouseEvent) =>
      val rect = canvas.getBoundingClientRect()
      println(e.clientX - rect.left, e.clientY - rect.top)
      renderer.fillStyle = "#ff0000"
      renderer.fillRect(e.clientX - rect.left, e.clientY - rect.top, 10, 10)
    }

    canvas.width = 400
    canvas.height = 300
    dom.document.body.appendChild(canvas)

    /*
    renderer.fillStyle = "#ff0000"
    renderer.fillRect(0, 0, 50, 50)
    renderer.fillStyle = "#00FF00"
    renderer.fillRect(50, 50, 100, 100)
    */

    // somehow this does not work
    // val button2 = dom.document.createElement("HTMLButtonElement").asInstanceOf[HTMLButtonElement]
    // dom.document.body.appendChild(button2)
  }

  def addClickedMessage(): Unit = {
    jQuery("body").append("<p>You clicked the button!</p>")
  }

  def addClickedMessage2(): Unit = {
    println("Hello World")
  }

  def colorHandler(evtData: JQueryEventObject) : Unit = {
    println(evtData)
    println("Color")
  }
}
