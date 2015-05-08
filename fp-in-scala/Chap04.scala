object Chap04 {

	trait MyOption[+A] {
		def myMap[B](f: A => B): MyOption[B] = this match {
    		case MyNone => MyNone
    		case MySome(a) => MySome(f(a))
  		}
	}
	case class MySome[+T](x: T) extends MyOption[T]
	case object MyNone extends MyOption[Nothing]


	def mean(xs: Seq[Double]): MyOption[Double] = {
		if (xs.isEmpty) MyNone
		else MySome(xs.sum / xs.length)
	}

	def myHead[T](xseq: Seq[T]): MyOption[T] = {
		xseq match {
			case Nil => MyNone
			case (x::xs) => MySome(x)
		}
	}

	def lift[A,B](f: A => B): MyOption[A] => MyOption[B] = _ myMap f

	// some stupid function which returns MyNone when the argument = 0;
	// otherwise returns the argument
	def funnyFunc(x: Int): MyOption[Int] = x match {
		case 0 => MyNone
		case _ => MySome(x)
	}

	def runit() = {

		val l1 = Seq[Double](1,2,4)
		println(mean(l1))
		println(myHead(l1))

		val l2 = Seq[Double]()
		println(mean(l2))
		println(myHead(l2))

		val o1 = MySome(3.0)
		println(o1.myMap(x => 2*x))

		val o2 = MyNone
		println(o2.myMap((x:Int) => 2*x))

		println
		println(funnyFunc(2))
		println(funnyFunc(0))
		println

		// the idea is that myMap itself handles the distinction between
		// MySome & MyNone. This has the "drawback" that functions can only be
		// applied via myMap...
		println(funnyFunc(0).myMap(x=>2*x))
		println(funnyFunc(1).myMap(x=>2*x))

		// On the other hand, we can lift any function and use the lifted
		// function with MyOption types...
		val liftedFunction = lift((x:Int)=>2*x)
		println
		println(liftedFunction(funnyFunc(0)))
		println(liftedFunction(funnyFunc(1)))

	}
}
