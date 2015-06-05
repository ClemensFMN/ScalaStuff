object Chap04 {

	// start of ADT definition -> a triat which every MyOption type provides
	trait MyOption[+A] {
		// the core idea of MyOption: When a MyNone is processed, it still
		// stays a myNone;  otherwise we apply the function onto the value
		// wrapped into the MySome
		def myMap[B](f: A => B): MyOption[B] = this match {
    		case MyNone => MyNone
    		case MySome(a) => MySome(f(a))
  		}
	}
	// MyOption can actually be (i) a MySome
	case class MySome[+T](x: T) extends MyOption[T]
	// or (ii) a MyNone
	case object MyNone extends MyOption[Nothing]

	//we can use the new datatype in some functions to indicate that the
	//operation is not possible / meaningful
	def mean(xs: Seq[Double]): MyOption[Double] = {
		if (xs.isEmpty) MyNone
		else MySome(xs.sum / xs.length)
	}

	// same here
	def myHead[T](xseq: Seq[T]): MyOption[T] = {
		xseq match {
			case Nil => MyNone
			case (x::xs) => MySome(x)
		}
	}

	// we can also "lift" existing functions (mapping from type A to B) into
	// functions that operate in the MyOption domain
	def lift[A,B](f: A => B): MyOption[A] => MyOption[B] = _ myMap f

	// some stupid example function which returns MyNone when the argument =
	// 0; otherwise returns the argument wrapped in MySome
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

		println("test myMap")
		println(MySome(3.0).myMap(x => 2*x))
		println(MyNone.myMap((x:Int) => 2*x))

		println("testing funnyFunc")
		println(funnyFunc(2))
		println(funnyFunc(0))
		println

		// here we start with function chaining: we want to take the result
		// from funnyFunc, multiply it by 2, and add 1. f1 and f2 themselves
		// do not know about MyOption types; they can only deal with Ints.

		def f1(x:Int):Int = 2*x
		def f2(x:Int):Int = x+1

		// If funnyFunc were to return Int, we could write f2(f1(funnyFunc)).
		// But it doesn't, and returns MyOption instead. So f1 and f2 need to
		// deal with arguments of type MyOption.

		// option 1 is to use myMap, which accepts a function dealing with
		// Ints (and not MyOptions) and apply it via myMap, wich knows how to
		// deal with MyOptions.
		println(funnyFunc(0).myMap(f1).myMap(f2))
		println(funnyFunc(1).myMap(f1).myMap(f2))

		// option 2 is to lift the functions into the MyOption domain using
		// the lift function.
		val f1_lifted = lift(f1)
		val f2_lifted = lift(f2)
		println
		// then we can chain the lifted functions like we would do wit
		println(f2_lifted(f1_lifted(funnyFunc(0))))
		println(f2_lifted(f1_lifted(funnyFunc(1))))


	}
}
