package app

import org.scalatest._

class MatcherTest extends FunSuite with Matchers {

    test("1 + 3 should be 4") {
        val result = 1 + 3
        result shouldBe 4
    }
    
    test("List(1,2,3) should have length 3") {
        List(1,2,3) should have length 3
    }
}