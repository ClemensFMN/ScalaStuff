class C(n: Int) {
	private var n_int = n
	def increment() = {n_int = n_int + 1}
	override def toString() = "Value = " + n_int
}


object OTst {
	def objectFunction() = println("Hello World")
}

// by declaring the constructor private, val d1 = new D(4) is not possible
// anymore -> object creation is possible via the companion object only!
class D private(n: Int) {
	private var n_int = n
	def increment() = {n_int = n_int + 1}
	override def toString() = "Value = " + n_int
}

object D {
	// having a companion object with an apply method, allows for val d = D(4)
	def apply(n: Int):D = new D(n)
}


object ClassObject {
	def runit() = {
		val c1 = new C(3)
        println(c1)

        OTst.objectFunction

		/*
        val d1 = new D(4)
        println(d1)
        d1.increment()
        println(d1)
		*/
        // this is made possible via the companion object...
        val d2 = D(4)
        println(d2)
        d2.increment()
        println(d2)
	}
}


