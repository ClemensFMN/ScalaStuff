
/*
if we had defined Cett as contravariant in T; i.e. class Cell[+T], then this 
would have been possible:
	val c1 = new Cell[String]("abc")
  	val c2: Cell[Any] = c1
  	c2.set(1)
  	val s: String = c1.get 
-> not good :-( anyway, the compiler does not allow the +T definition...
*/
class Cell[T](init: T) {
	private var current = init
	def get = current
	def set(x: T) {current = x}
}


object chap04a {
	
	def runit() = {
		val c1 = new Cell[Int](34)
		println(c1.get)
		c1.set(35)
		println(c1.get)

		// a list is contravariant; if T is a subtype of S, then List[T] is
		// also a subtype of List[S]
		val l1 = List[String]("sd", "dvfdbfb", "fbfdbf")
		// String is a subtype of Any
		val s = new String("ddvv")
		val a:Any = s
		// -> List[String] is a subtype of List[Any]
		val l2:List[Any] = l1
	}
}