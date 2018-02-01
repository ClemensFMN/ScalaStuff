import scala.collection.mutable._

class Node(Id : Int) {
  private val ngbs = new scala.collection.mutable.ArrayBuffer[Node]()

  def --> (other: Node) = {
    ngbs += other
  }

  def getId() = Id
  
  def getNeighbours() = ngbs
  
  override def toString() : String = {
    val s = ngbs.foldLeft("")((rep, item) => rep + " " + item.getId())
    "Node, Id = " + Id + " Neighbours: " + s
  }
}


def traverseDF(start: Node):Unit = {
  val ngbs = start.getNeighbours()
  for(item <- ngbs) {
    println(item)
    println(item.getNeighbours.length)
    traverseDF(item)
  }
}


def traverseBF(start: Node):Unit = {
  val ngbs = start.getNeighbours()
  for(item <- ngbs) {
    println(item)
  }
  for(item <- ngbs) {
    traverseBF(item)
  }
}


val n1 = new Node(1)
val n2 = new Node(2)
val n3 = new Node(3)
val n4 = new Node(4)
val n5 = new Node(5)


n1 --> n2
n1 --> n3
n2 --> n4
n2 --> n5

println(n1)
println()

traverseDF(n1)
println()
traverseBF(n1)
