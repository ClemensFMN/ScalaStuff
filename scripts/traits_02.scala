// I don't know of this is a sensible "real-life" example for traits
// we have an abstract base class which only defines the process function
abstract class BaseClass {
  def log(x: Int)
  def f(x: Int): Int
  def process(x: Int) = {
    log(x)
    val res = f(x)
    log(res)
    res
  }
}

// the actual function is defined in a derived class, which is STILL 
// abstract (log is not defined)
abstract class ConClass1 extends BaseClass {
  def f(x:Int) = x + 1
}

// and the trait defines the logging function
trait ConsoleLogger extends BaseClass {
  override def log(x: Int) = println(x)
}

trait ConsoleLogger2 extends BaseClass {
  override def log(x: Int) {
    println("****")
    println(x)
    println("****")
  }
}

// this is an abstract class because log is NOT implemented
// val c1 = new ConClass1()
// -> this does not work
// println(c1.process(1))

val c2 = new ConClass1() with ConsoleLogger
println(c2.process(1))


val c3 = new ConClass1() with ConsoleLogger2
println(c3.process(1))
