object CaseClasses {
	
	abstract class Cls
	case class c1(x: Int) extends Cls
	case class c2(x: String) extends Cls
	case class c3(x1: Int, x2: Int) extends Cls

	def simp(x: Cls) = {
		x match {
			case c1(x) => println("class c1: " + x)
			case c2(x) => println("class c2: " + x)
			case c3(x1, x2) => println("class c3: " + x1 + "..." + x2)
		}
	}

	def runit() = {
		simp(c1(3))
		simp(c2("hello"))
		simp(c3(4,5))
	}

}