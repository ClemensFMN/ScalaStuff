package app

import org.scalatest._

class AppTest extends FlatSpec {

    "An empty Set" should "have size 0" in {
        assert(Set.empty.size == 0)
    }

    "A 3-element list" should "have 3 elements" in {
        val l = List(1,2,3)
        assert(l.length == 3)
    }
    
    "A non-empty list" should "have a head" in {
        assert(List(1,2,3).head == 1)
    }
}