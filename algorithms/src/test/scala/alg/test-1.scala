package alg

import org.scalacheck._
import org.scalacheck.Prop._
import org.scalacheck.Gen._


object Test1 extends Properties("QSort") {

	property("sorting, v1") = forAll {(lst: List[Int]) => lst.sorted == Qsort.qsort_v1(lst)}
	property("sorting, v2") = forAll {(lst: List[Int]) => lst.sorted == Qsort.qsort_v2(lst)}
}


object Test2 extends Properties("Palindrome") {

	val smallInteger = Gen.choose(0,10000)

	property("reverse reverse") = forAll(smallInteger){x:Int => 
			(x % 10 != 0) ==> 
			(x == Palindrome.reverse_int(Palindrome.reverse_int(x)))}

	property("first element") = forAll(smallInteger)(x => Palindrome.reverse_int_to_vec_int(x, List()).last == x%10)
}

