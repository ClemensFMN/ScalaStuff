import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object QSort extends Properties("QSort") {

	property("sorting, v1") = forAll {(lst: List[Int]) => lst.sorted == Qsort.qsort_v1(lst)}
	property("sorting, v2") = forAll {(lst: List[Int]) => lst.sorted == Qsort.qsort_v2(lst)}
}

