/*
Problem #7
 We have a tree and search for the root element
 we build up the tree and store in every node the parent; i.e. we build up the
 tree in the reverse order

 findRoot starts at any node (ideally a leave) and walks backwards (towards every node's parent)
 when there is no parent in a node, we have found the root of the tree
*/

import scala.collection.mutable._
/*
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


val n1 = Node("root", None)
val n2 = Node("mom", Some(n1))
val n3 = Node("son", Some(n2))

val res = findRoot(n3)
println(res)

val res2 = findRoot2(n3,List())
println(res2)
*/



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
A probably easier design (not so elegant :-() is to throw the whole stuff in a map
of the form sibling => parent
 {"d"=>"b", "b"=>"a", "c"=>"a", "e" => "a"}
*/
/*
val prnt = Map.empty[String, String]

val s = "b d\na b c \na e"
val splt = s.split("\n").map(_.trim)
//splt.foreach(println)

for(ln <- splt) {
	val r = ln.split(" ").toArray
	val parent = r(0) // the parent node is the first node
	for(i <- 1 to r.length-1) {
		prnt(r(i)) = parent // all other nodes have "parent" as parent
	}
}

//println(prnt)
// some arbitrary starting node
val node = "d"
// go back in the map
def findRoot3(n:String):String = {
	if(prnt.contains(n)) {
		findRoot3(prnt(n)) // redo with the current node as parent (if available)
	}
	else n // otherwise, we are done
}

val res = findRoot3(node)
println(res)
*/

/*
 b d
 a b c
 a e

Another (probably simpler approach): When all nodes have one common root,
then this root must only appear in the first column and never in column 2...
In the example above, the first line tells us that d cannot be root, but b
is a possible candidate.

We can formulate an algorithm as follows: We keep two sets, (i) possible candidates for root (set is denoted as C) and (ii) nodes which cannot be root nodes N (set is denoted as N)

Initially, these sets are empty and are updated with every line of the stings: The leftmost node of every line is a possible candidate, therefore added to C. All other  nodes of the line cannot be root nodes (the leftmost node is their parent) and are therefore added to N. Then, we subtract the set N from C and continue with the next line.

C = {}, N = {}
Read line "b d"
   add b to C => C = {b}, add d to N => N = {d}
   subtract N from C => C = {b}, N = {d}
Read line "a b c"
   add a to C => C = {a,b}, add b and c to N => N = {b,c,d}
   subtract N from C => C = {a}, N = {b,c,d}
Read line "a e"
   add a to C => C = {a,b}, add e to N => N = {b,c,d,e}
   subtract N from C => C = {a}, N = {b,c,d,e}
a is the root element
*/

val s = "b d\na b c \na e"
val splt = s.split("\n").map(_.trim)


// the algorithm with (somewhat clumsy) if statements
def findRoot4(s:Array[String], C: Set[String], N:Set[String]):(Set[String],Set[String]) = {
	if(s.length == 0) {
		(C,N)	// we are done and return both C and N
	}
	else {	// we are not done
		val ln = s(0).split(" ") //split the line at the spaces
		val Cnew = C + ln(0) // update the C set
		val Nnew = N ++ ln.slice(1, ln.length) // update the N set (slice is required!)
		findRoot4(s.slice(1, s.length), Cnew--Nnew,Nnew) // and start again with the remaining lines
	}
}
findRoot4(splt, Set(), Set())


// and with pattern matching on the input array
def findRoot5(sin:Array[String], C: Set[String], N:Set[String]):(Set[String],Set[String]) = {
	sin match {
		case Array() => (C,N)
		case Array(s, srest@_*) => {
			val ln = s.split(" ")
			val Cnew = C + ln(0)
			val Nnew = N ++ ln.slice(1, ln.length)
			findRoot5(srest.toArray, Cnew--Nnew,Nnew)	
		}
	}
}
findRoot5(splt, Set(), Set())


// try to use a list instead of an array to get things simpler
val splt2 = s.split("\n").map(_.trim).toList

def findRoot6(sin:List[String], C: Set[String], N:Set[String]):(Set[String],Set[String]) = {
	sin match {
		case List() => (C,N) // match the empty list
		case s :: srest => { // something left to do
			val ln = s.split(" ")
			val Cnew = C + ln(0)
			val Nnew = N ++ ln.slice(1, ln.length)
			findRoot6(srest, Cnew--Nnew,Nnew)	
		}
	}
}
// -> maybe that's a bit easier than the array approach(?)
findRoot6(splt2, Set(), Set())

