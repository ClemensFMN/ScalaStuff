object Chap06 {

	def nextState(prevState: Int, inp: Int): (Int, Int) = {
		(prevState, inp) match {
			case (0,0) => (0,0)
			case (1,0) => (1,0)
			case (0,1) => (1,1) // inp = 1 -> state change
			case (1,1) => (0,1) // inp = 1 -> state change
			case _ => (0,0)
		}
	}

	def sequence(Szero: Int, inp: List[Int]): List[Int] = {
		var out = List[Int]()
		var Sold = Szero

		inp.foreach(x => {
				val (stateNext, outp) = nextState(Sold, x)
				out = outp :: out
				Sold = stateNext
			}
		)
		out
	}


	def runit() = {
		println(nextState(0,0))

		println(sequence(0, List(0,1,0,0,1,0)))

	}
}