
class MyFraction(n: Int, d: Int) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  private val numer = n / g
  private val denom = d / g

  // aux constructor
  def this(n: Int) = this(n, 1)

  override def toString = numer + "/" + denom

  // adding two fractions
  def +(that: MyFraction) = new MyFraction(numer * that.denom + that.numer * denom, denom * that.denom)

  // adding an int to a fraction
  def +(that: Int) = new MyFraction(numer + that * denom, denom)
  def -(that: Int) = new MyFraction(numer - that * denom, denom)

  def reciprocial() = new MyFraction(d, n)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private def intPart():Int = Math.floor(this.n / this.d).toInt

  private def fractPart():MyFraction = this - intPart()

  private def isZero() = (n==0)

  def toContinuedFraction():ContinuedFraction = {
    def go(f:MyFraction):List[Int] = {
      val fpart = f.fractPart()
      if(fpart.isZero()) {
        List[Int](f.intPart())
      }
      else {
        f.intPart()::go(fpart.reciprocial())
      }
    }
    new ContinuedFraction(go(this))
  }
}

// represent continued fractions
class ContinuedFraction(coeff:List[Int]) {

  override def toString() = coeff.toString()

  def calcValue: Float = {
    val revCoeff = coeff.reverse
    var res:Float = revCoeff.head
    for(el <- revCoeff.tail) {
      res = 1/res + el
    }
    res
  }
}


object App {

  implicit def intToMyFraction(x: Int) = new MyFraction(x)

  def main(args: Array[String]): Unit = {
    println("Hello World")

    val f1 = new MyFraction(3, 12)
    val f2 = new MyFraction(2, 5)

    println(f1)
    println(f1 + f2)
    println(f2 + 1)

    // for this to work, we need the implicit conversion from above
    println(1 + f2)

    // this should yield a value of 3.245
    val cf1 = new ContinuedFraction(List(3,4,12,4))
    println(cf1.calcValue)

    val tst1 = new MyFraction(649, 200)
    println(tst1.toContinuedFraction())
    println(tst1.toContinuedFraction().calcValue)

  }
}