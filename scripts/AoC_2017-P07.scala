/*
Problem #7
 We have a tree and search for the root element
 we build up the tree and store in every node the parent; i.e. we build up the
 tree in the reverse order

 findRoot starts at any node (ideally a leave) and walks backwards (towards every node's parent)
 when there is no parent in a node, we have found the root of the tree
*/

import scala.util.matching._

case class Node(name:String, parent:Option[Node])

def findRoot(strt:Node):Node = {
	strt match {
		case Node(s, None) => strt
		case Node(s, Some(n)) => {
			println(s);
			findRoot(n)
		}
	}
}


val n1 = Node("root", None)
val n2 = Node("mom", Some(n1))
val n3 = Node("son", Some(n2))

val res = findRoot(n3)
println(res)

