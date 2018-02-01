// an example to represent tree structures via ADTs
abstract class TreeStuff
// TODO replace with generic type instead of int
case class Node(value:Int) extends TreeStuff
case class Branch(value:Int, l:TreeStuff, r:TreeStuff) extends TreeStuff


def printTree(t:TreeStuff):Unit = {
  t match {
    case Node(x) => println("Node: " + x)
    case Branch(x,l,r) => {
      println("Branch: " + x)
      printTree(l)
      printTree(r)
    }
  }
}

def depthFirstTraversal(t:TreeStuff):Unit = {
  t match {
    case Node(x) => println("Node: " + x)
    case Branch(x,l,r) => {
      depthFirstTraversal(l)
      depthFirstTraversal(r)
      println("Branch: " + x)
    }
  }
}

def bredthFirstTraversal(t:TreeStuff):Unit = {
  t match {
    case Node(x) => println("Node: " + x)
    case Branch(x,l,r) => {
      println("Branch: " + x)
      bredthFirstTraversal(l)
      bredthFirstTraversal(r)
    }
  }
}


def sumNodes(t:TreeStuff):Int = {
  var s:Int = 0
  t match {
    case Node(x) => {
      s += x
      s
    }
    case Branch(x,l,r) => {
      s += x
      s += sumNodes(l)
      s += sumNodes(r)
      s
    }
  }
}

val myTree = Branch(0, Branch(1, Node(2), Node(3)), Node(4))

printTree(myTree)

println(sumNodes(myTree))
println
depthFirstTraversal(myTree)
println
bredthFirstTraversal(myTree)
