// the example from Programming in Scala, Section 12.5

import scala.collection.mutable.ArrayBuffer 

// an abstract base class
abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

// a concrete implementation
class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) { buf += x }
}

// now define a trait which derives from the (abstract) base class -> we 
// can mix this trait only into classes which also derive from IntQueue
trait Doubling extends IntQueue {
  // we override an abstract method - this is allowed ONLY for traits & 
  // will work when we mix the trait into a concrete implementation of the 
  // base class
  abstract override def put(x: Int) {super.put(2*x)}
}


// now define a trait which derives from the (abstract) base class -> we 
// can mix this trait only into classes which also derive from IntQueue
trait Incrementing extends IntQueue {
  // we override an abstract method - this is allowed ONLY for traits & 
  // will work when we mix the trait into a concrete implementation of the 
  // base class
  abstract override def put(x: Int) {super.put(x+1)}
}


trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if(x >= 0) super.put(x)
  }
}


val queue = new BasicIntQueue()
queue.put(1)
queue.put(2)
println(queue.get())
println(queue.get())


// we can use the trait to define a new class
// class MyQueue extends BasicIntQueue with Doubling
// and then instantiate MyQueue
// or we define & instantiate in one line
val queue2 = new BasicIntQueue with Doubling
queue2.put(1)
queue2.put(2)
println(queue2.get())
println(queue2.get())
println()

val queue3 = new BasicIntQueue with Incrementing
queue3.put(1)
queue3.put(2)
println(queue3.get())
println(queue3.get())
println()


// we can combine / stack traits on top of each other...
// order IS important - the rightmost trait is applied first and then it goes 
// to the left...
// so we filter first -> -1 is removed and increment afterwards
val queue4 = new BasicIntQueue with Incrementing with Filtering
queue4.put(1)
queue4.put(-1)
println(queue4.get())
println()

// incrementing first & filtering afterwards
val queue5 = new BasicIntQueue with Filtering with Incrementing
queue5.put(1)
queue5.put(-1)
println(queue5.get())
println(queue5.get())
println()

