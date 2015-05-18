trait SimpleOption

case class NoOption extends SimpleOption

case class Option1(x: Int) extends SimpleOption

case class Option2(x: Int, y: Int) extends SimpleOption


object chap03b {

	def doSomething(input: Int, op: SimpleOption): Int = {
		op match {
			case NoOption() => input
			case Option1(x) => input * x
			case Option2(x,y) => input * x + y
		}
	}


	def runit() = {
		println(doSomething(3, NoOption()))
		println(doSomething(3, Option1(3)))
		println(doSomething(3, Option2(2,4)))
	}
}


