object chap02 {
	
	// function takes two parameters; one int and on function returning Unit
	// ("nothing")
	// because the function is curried, the second argument can be provided
	// inside curly braces
	def repeat(times: Int)(op: => Unit) = {
		for (x <- 1 to times) {
			op
		}
	}

	def runit() = {
		// second argument in curly braces (curried function); see above
		repeat(3){
			println("hello")
		}
	}

}