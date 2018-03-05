// Stuff from Functional Prgramming Simplified

// p.595 (map vs flatMap)

List("Hallo Clemens").map(s => s.split(""))
List("Hallo Clemens").flatMap(s => s.split(""))


// p.600
def makeInt(s: String):Option[Int] = {
  try{
    Some(s.trim.toInt)
  } catch {
    case e: Exception => None
  }
}

makeInt("4")
makeInt("dkfj")

// what happens if we want to add the result of two makeInts?
// adding the option values does not work
// but this does
makeInt("4").map(x => makeInt("5").map(y => x+y))
// and this can be flattened via
makeInt("4").flatMap(x => makeInt("5").map(y => x+y))
// with the snytax becoming nicer when we use a for expression
for{
  x <- makeInt("4")
  y <- makeInt("5")
} yield x+y

for{
  x <- makeInt("abc")
  y <- makeInt("5")
} yield x+y

// p.623
// any scala scala class, that implements map and flatMap is a monad.
// p.645ff - creating a wrapper class with map & flatMap
// using a case class to simplify instantiation
case class IntWrapper(v:Int) {
  def map(f:Int => Int):IntWrapper = IntWrapper(f(v))
  def flatMap(f:Int => IntWrapper):IntWrapper = f(v)
  override def toString = "IntWrapper(" + v.toString + ")"
}

// and test-driving the thing...
val iw1 = IntWrapper(3)

for {
  a <- IntWrapper(3)
  b <- IntWrapper(10)
} yield a+b

// the main observation here is that we can use IntWrapper in the for loop, 
// because it provide a map & flatMap method. Classes need not necessarily
// be collections to use them in a for loop...
// p.670 - Debuggable

case class Debuggable(value:Int, msg:String) {
  def map(f:Int=>Int):Debuggable = Debuggable(f(value), msg)
  def flatMap(f:Int=>Debuggable):Debuggable = {
    val nextValue = f(value)
    Debuggable(nextValue.value, msg + "..." + nextValue.msg)
  }
}

def f1(a:Int):Debuggable = Debuggable(2*a, "Function f1")
def f2(a:Int):Debuggable = Debuggable(a+1, "Function f2")

for {
  a <- f1(4)
  b <- f2(a)
} yield b

