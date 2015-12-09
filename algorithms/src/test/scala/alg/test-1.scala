package alg

import org.scalacheck._
import org.scalacheck._

object Test1 extends Properties("QSort") {

	property("sorting, v1") = Prop.forAll {(lst: List[Int]) => lst.sorted == Qsort.qsort_v1(lst)}
	property("sorting, v2") = Prop.forAll {(lst: List[Int]) => lst.sorted == Qsort.qsort_v2(lst)}
}


object Test2 extends Properties("Palindrome") {

	// tricky to test, as 120 becomes 21
	val smallInteger = Gen.choose(11,19)
	property("palindrome, 1") = Prop.forAll (smallInteger)(x=> x == Palindrome.reverse_int(Palindrome.reverse_int(x)))

	val smallInteger2 = Gen.choose(0,10000)
	property("palindrome, 2") = Prop.forAll(smallInteger2)(x => Palindrome.reverse_int_to_vec_int(x, List()).last == x%10)
}

