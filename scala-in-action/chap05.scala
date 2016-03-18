
class TestC(val x: Int) {
	def area = x*x
}

class TestC2(var x: Int) {
	def area = x*x
}

// a class with a setter but which returns a new class instead of updating the state of the class
// feels a bit awkward
class TestC3(private var _x: Int) {
	def x = _x
	def x_=(xi: Int) = {new TestC3(xi)}
	def area = x*x
}

object chap05 {
	
	def runit() = {
		val c1 = new TestC(4)
		println(c1.area)
		// prevent change of state by using vals 
		// -> this is not possible c1.x = 2
		// only way to change x is to create a new class...

		val c2 = new TestC2(5)
		println(c2.area)

		c2.x = 10
		println(c2.area)		

		var c3 = new TestC3(6)
		println(c3.area)
		val c4 = (c3.x = 20)
		println(c4.area)

	}

}