object chap03c {
	
	val history = scala.collection.mutable.Map[(Int, Int), Int]()
	var calls: Int = 0

	def StirlingSecond(n: Int, k: Int): Int = {

		history.getOrElseUpdate((n,k), StirlingSecondInt(n, k))
		//StirlingSecondInt(n, k)

	}


	def StirlingSecondInt(n: Int, k: Int): Int = {
		//println("Called with:", n, k)
		calls = calls + 1
		(n,k) match {
			case (0,0) => 1
			case (n,1) => 1
			case (m,n) if m == n => 1
			case (n,k) => StirlingSecond(n-1,k-1) + k*StirlingSecond(n-1,k)
			case _ => error("not implemented")
		}
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
		println(StirlingSecond(10,5))
		println("Number of calls: " + calls)

	}

}