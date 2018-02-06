def matchIntValue(x:Int): Unit = {
	x match {
		case 0 => println("zero")
		case x if x < 0 => println("negative")
		case x if x > 0 => println("positive")
	}
}

matchIntValue(-3)
matchIntValue(0)
matchIntValue(3)


def matchList(lst:List[Int]): Unit = {
	lst match {
		case List() => println("empty list")
		case List(1,_) => println("2 elements, 1st element = 1")
		case List(4,xs@_*) => println("starts with 4, other elements = " + xs)
		case _ => println("everything else")
	}
}

matchList(List())
matchList(List(1,2))
matchList(List(4,2,3))
matchList(List(1,2,3))



def matchStringList(inp:List[String]): Unit = {
	inp match {
		case List("inc", a, value) => println("increment", a, value)
		case List("jmp", _, value) => println("jmp", value)
		case _ => println("everything else")
	}
}

matchStringList(List("inc","ax","34"))
matchStringList(List("jmp","ax","10"))
matchStringList(List("jmp","10"))

