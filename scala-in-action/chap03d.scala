// Example - 1
// a stupid trait
trait T1 {
	def tst() = println("T1")
}

// another one
trait T2 {
	def tst2() = println("T2")
}

// extending a class with a trait adds the trait's methods to the class
class C1 extends T1
class C2 extends C1 with T2

// Example - 2
// we have a simple base class
class SimpleLog(printFunc: String => Unit) {
	def log(s: String) = printFunc(s)
}

// and define traits which override functions from the baseclass
trait DateLog extends SimpleLog {
	override def log(s: String) = super.log("Date=xxx" + s)
}

trait TimeLog extends SimpleLog {
	override def log(s: String) = super.log("Time=xxx" + s)
}


// Example - 3
// the trait defines a rich API in terms of an abstract function
trait RichAPI {
	def simpleFunc(x:Int): Int
	def richFunction1(x:Int) = simpleFunc(x) + 1
	def richFunction2(x:Int) = simpleFunc(x) + 2
}

// a class extending the trait defines the abstract function
class CTest extends RichAPI {
	override def simpleFunc(x: Int): Int = 2*x
}

object chap03d {
	def runit() {
		// Example - 1
		val c1 = new C1()
		c1.tst()
		//val c2 = new C2()
		val c2 = new C1() with T2
		c2.tst2()

		// Example - 2
		val sl = new SimpleLog(println)
		sl.log("Hello SimpleLog")

		// we can use the traits as stackable modifications of the baseclass...
		val dl = new SimpleLog(println) with DateLog
		dl.log("Hello DateLog")

		val dtl = new SimpleLog(println) with DateLog with TimeLog
		dtl.log("Hello DateTimeLog")

		val tdl = new SimpleLog(println) with TimeLog with DateLog
		tdl.log("Hello TimeDateLog")

		// Example - 3
		val ct = new CTest()
		println(ct.simpleFunc(2))
		println(ct.richFunction1(2))
		println(ct.richFunction2(2))

	}
}
