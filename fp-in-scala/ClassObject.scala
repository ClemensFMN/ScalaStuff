class C(n: Int) {
	private var n_int = n
	def increment() = {n_int = n_int + 1}
	override def toString() = "Value = " + n_int
}


object OTst {
	def objectFunction() = println("Hello World")
}

class D(n: Int) {
	private var n_int = n
	def increment() = {n_int = n_int + 1}
	override def toString() = "Value = " + n_int
}

object D {
	// having a companion object with an apply method, allows for val d = D(4)
	def apply(n: Int):D = new D(n)
}

