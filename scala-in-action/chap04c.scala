

//call-by-name vs call-by-value
//http://stackoverflow.com/questions/13337338/call-by-name-vs-call-by-value-in-scala-clarification-needed

object chap04c {
	
	// function with side effect + returning an int
	def func1(x: Int): Int = {
		println("Hello from func1")
		2*x
	}

	// expect an integer as parameter -> we can use func1
	// the integer is evaluated _before_ it is passed to the function
	def callByValue(x: Int) = {
		println(x)
		println(x)
	}

	// expect an integer, but the integer is _not_ evaluated before it is passed to the function
	// -> every time x is used, it is evaluated anew
	def callByName(x: => Int ) = {
		println(x)
		println(x)
	}

	def runit() {

		callByValue(func1(4))
		callByName(func1(4))

		// calling a function without parameter but _, will return a function object
		println(callByName _)

		// create a partial function " function pointer"
		val f1 = func1 _
		// and execute it
		println(f1(10))


	}

}