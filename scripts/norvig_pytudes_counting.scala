// problem 2

// a regex pattern catching two non-consecutive A's OR three subsequent L's
def matches(s: String):Boolean = {
  val ptn = "(A.*A)|(LLL)".r
  ptn.findFirstMatchIn(s) != None
}
/*
matches("A") -> false
matches("AA") -> true
matches("AxAA") -> true
matches("AxLL") -> false
matches("LAxLL") -> false
matches("AxLLL") -> true
*/

/*
this function takes a list of element and produces the cross-product of n elements

Maybe not the most efficient one and was horribly complicated to write (despite the few lines :-))
*/
def product[T](x: Vector[T], n: Int):Vector[Vector[T]] = {
  n match {
    case 1 => for(elem <- x) yield Vector(elem) // the corner case - pack each input element in a vector
    case n => {
      val res = for(elem <- x) yield product(x, n-1).map(e => elem +: e) //append to each product(x, n-1) one element from x
      res.flatten // we need to flatten the result as the above line produces a vector[vector[vector[T]]]
    }
  }
}

// allowed chars are L, A, P
// create all combinations
val prod = product(Vector('L', 'A', 'P'), 5)
// convert them to strings
val prodstrings = prod.map(x=>x.mkString)

// and count how many absence sceanrios are OK (require no counseling talk)
prodstrings.map(matches).count(x=>x==false)
