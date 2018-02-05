/*
Problem #7
 We have a tree and search for the root element
 we build up the tree and store in every node the parent; i.e. we build up the
 tree in the reverse order

 findRoot starts at any node (ideally a leave) and walks backwards (towards every node's parent)
 when there is no parent in a node, we have found the root of the tree
*/

import scala.util.matching._
import scala.collection.mutable._

// a node has a name & an optional parent
case class Node(name:String, parent:Option[Node])
// start at a leave and walk "up"
def findRoot(strt:Node):Node = {
	strt match {
		case Node(s, None) => strt // in case our node has no parent, we are done
		case Node(s, Some(n)) => { // otherwise we call ourselves with the parent
			println(s);
			findRoot(n)
		}
	}
}

// slightly more advanced in that we additionally build up a list of already visited names
def findRoot2(p:(Node, List[String])):(Node, List[String]) = {
	val (strt, visited) = p
	strt match {
		case Node(s, None) => (strt, visited) // we are done
		case Node(s, Some(n)) => {
			println(s);
			findRoot2(n, s::visited) //otherwise, call ourselves with the parent and the name appended to the list
		}
	}	
}

/*
 we simplify our live and consider lines of the form p1 p2 ... pN
 Example
 b d
 a b c
 a e

 The first line should create nodes b and d with b the parent in d
 However, the parent of b becomes only known in the second line -> we need to
 be able to change a node's parent after creation

 Another OI: How do we get these nodes in the global(?) namespace so that findRoot can use them...
 
*/
/*
def parseString(s:String):Array[Node] = {
	val ar = Array[Node]
	val splt = s.split("\\s+")
	val parent = Node("splt[0]", None)
	if(splt.length > 1) {
		for(i <- 1 to splt.length-1) {

		}
	}
	ar

}
*/

/*
val n1 = Node("root", None)
val n2 = Node("mom", Some(n1))
val n3 = Node("son", Some(n2))

val res = findRoot(n3)
println(res)

val res2 = findRoot2(n3,List())
println(res2)

*/

/*
A probably easier design (not so elegant :-() is to throw the whole stuff in a map
 {"d"=>"b", "b"=>"a", "c"=>"a", "e" => "a"}
*/

val s = "b d\n a b c \n a e"

val prnt = Map.empty[String, String]

val splt = s.split("\n").map(_.trim)

splt.foreach(println)

for(ln <- splt) {
	val r = ln.split(" ").toArray
	val parent = r(0)
	for(i <- 1 to r.length-1) {
		prnt(r(i)) = parent
	}
}

//println(prnt)

val node = "d"

def findRoot3(n:String):String = {
	if(prnt.contains(n)) {
		findRoot3(prnt(n))
	}
	else n
}

val res = findRoot3(node)
println(res)
