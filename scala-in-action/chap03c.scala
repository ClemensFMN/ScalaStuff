object chap03c {
	
	// this is the map which holds already calculated stirling numbers
	val history = scala.collection.mutable.Map[(Int, Int), Int]()
	// debug variable to count the number of calls
	//var calls: Int = 0

	// the public interface method
	def StirlingSecond(n: Int, k: Int): Int = {

		// perform a look-up for the (n,k) pair; if not in the map, start calculating
		history.getOrElseUpdate((n,k), StirlingSecondInt(n, k))
		//StirlingSecondInt(n, k)

	}

	// the function doing the real work
	def StirlingSecondInt(n: Int, k: Int): Int = {
		//println("Called with:", n, k)
		//calls = calls + 1
		// based on a pattern match, calculate the stirling numbers according
		// to the "textbook definition"
		(n,k) match {
			case (0,0) => 1
			case (n,1) => 1
			case (m,n) if m == n => 1
			case (n,k) => StirlingSecond(n-1,k-1) + k*StirlingSecond(n-1,k)
			case _ => error("not implemented")
		}
	}


	def FibonacciV1(n: Int): Int = {
		n match {
			case 1 => 1
			case 2 => 1
			case _ => FibonacciV1(n-1) + FibonacciV1(n-2)
		}
	}


	def FibonacciV2(n: Int) = {

		val historyFib = scala.collection.mutable.Map[Int, Int]()
		var calls: Int = 0

		def FibonacciCalc(n: Int): Int = {
			calls = calls + 1
			n match {
				case 1 => 1
				case 2 => 1
				case _ => FibonacciInt(n-1) + FibonacciInt(n-2)
			}
		}

		def FibonacciInt(n: Int): Int = {
				historyFib.getOrElseUpdate(n, FibonacciCalc(n))
				//FibonacciCalc(n)
		}
	
	(FibonacciInt(n), calls, historyFib)

	}




	def runit() = {

/*
		history += (1,1) -> 4
		history += (1,5) -> 10

		println(history)

		println(history.getOrElseUpdate((1,1), 7))
		println(history.getOrElseUpdate((1,2), 7))

		println(history)
*/
		//println(StirlingSecond(10,5))
		

		for(i <- 1 to 10) {
			println(FibonacciV1(i))

			var x = FibonacciV2(i)
			println("Argument: " + i + " => Result: " + x)
		}
	}

}