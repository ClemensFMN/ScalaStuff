object App {

	def qsort_v1(A: List[Int]): List[Int] = {
		if(A.size <= 1) {
			A
		}
		else {
			// the last element is the pivot element
			val pivot = A.last
			// all items from the remaining list and smaller than pivot are stored in Aleft
			val Aleft = A.take(A.size-1).filter(x => x < pivot)
			// all items from the remaining list and larger / equal than pivot are stored in Aright
			val Aright = A.take(A.size-1).filter(x => x >= pivot)
			// sort the two partitions
			val Aleftnew = qsort_v1(Aleft)
			val Arightnew = qsort_v1(Aright)
			// and build up the result
			Aleftnew ++ ( pivot :: Arightnew)
		}
	}

	// solution based on list match
	def qsort_v2(A: List[Int]): List[Int] = {
		A match {
  			case Nil => Nil	// empty list -> we are done
  			case pivot::xs =>
  					// partition according to pivot
    				val (left, right) = xs partition (_ < pivot)
    				// process the partitions and build up the final result
    				qsort_v2(left) ++ (pivot :: qsort_v2(right))
  		}
	}

	def main(args: Array[String]) {
		println("Hello World")

		//val A = List(1,5,3,10,7,15,8)
		//val A = List(1, 0, 1, -10, 15, 12, 6)
		val A = List(3, -5, -10, 1, 3, 3, -1)

		println(qsort_v1(A))
		println(qsort_v2(A))

	}
}