// Somewhat streamlined traits_02.scala - the base class just leaves the log function undefined...
abstract class BaseClass {
  def log(x: Int)
  def process(x: Int) = {
    log(x)
    val res = x+1
    log(res)
    res
  }
}

// the trait defines the logging function
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


val c2 = new BaseClass() with ConsoleLogger
println(c2.process(1))


val c3 = new BaseClass() with ConsoleLogger2
println(c3.process(1))
