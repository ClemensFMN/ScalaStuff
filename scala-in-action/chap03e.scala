



object chap03e {

	implicit class RangeMaker(left: Int) {
		def -->(right: Int): Range = left to right
	}

	// the best way to use implicit classes (which define implicit conversion) is probably to group 
	// all conversions taking an int in this very class
	implicit class IntConversions(left: Int) {
		def sumsi(right: Int): Int = left + right
		def outerProd(right: Int): (Int, Int) = (left, right)
		// thi does not get called??
		def *(right: Int): Int = left + right
	}


	def runit() = {

		val rm1 = new RangeMaker(1).-->(10)
		println(rm1)

		val rm2 = new RangeMaker(1)-->(10)
		println(rm2)


		// this calls the Int method -->(10) which is not defined in the standard lib
		// --> we need an implicit conversion from Int
		val myrange = 1 --> 10;
		println(myrange)

		// defining the implicit class as above, we can extend Int by more or less useful methods...
		val intcnv1 = 1 sumsi 4
		println(intcnv1)

		val intcnv2 = 4 outerProd 5
		println(intcnv2)

		val intcnv3 = 4 * 5
		println(intcnv3)
	}
}