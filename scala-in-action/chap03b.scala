trait SimpleObj
case class ObjA extends SimpleObj
case class ObjB extends SimpleObj


trait SimpleOption
case class NoOption extends SimpleOption
case class Option1(x: Int) extends SimpleOption
case class Option2(x: Int, y: Int) extends SimpleOption



object chap03b {

	def doSomething(input: SimpleObj): Int = {
		input match {
			case ObjA() => 1
			case ObjB() => 2
		}
	}

	def doSomethingv2(input: Int, op: SimpleOption): Int = {
		op match {
			case NoOption() => input
			case Option1(x) => input * x
			case Option2(x,y) => input * x + y
		}
	}

	def runit() = {

		println(doSomething(ObjA()))
		println(doSomething(ObjB()))

		println()

		println(doSomethingv2(3, NoOption()))
		println(doSomethingv2(3, Option1(3)))
		println(doSomethingv2(3, Option2(2,4)))

	}
}