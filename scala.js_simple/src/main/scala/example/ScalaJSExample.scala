package example
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.Random



@JSExport
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val renderer = canvas.getContext("2d")
                    .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = canvas.parentElement.clientWidth
    canvas.height = canvas.parentElement.clientHeight

    renderer.fillStyle = "#ff0000"
    renderer.fillRect(0, 0, canvas.width/2, canvas.height/2)

    renderer.fillStyle = "#00FF00"
    renderer.fillRect(canvas.width/2, canvas.height/2, canvas.width, canvas.height)

    //dom.window.setInterval(() => run, 50)
  }
}
