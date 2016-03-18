// example from Scala in Action
abstract class MyMaybe[+A] {
	def isEmpty: Boolean
	def get: A
}

case class MyJust[A](value: A) extends MyMaybe[A] {
	def isEmpty = false
	def get = value
}

object MyNil extends MyMaybe[Nothing] {
	def isEmpty = true
	def get = throw new NoSuchElementException("Nil.get")
}



// example from http://like-a-boss.net/2012/09/17/variance-in-scala.html
class Animal
class Mammal extends Animal
class Dog extends Mammal

// example from https://coderwall.com/p/dlqvnq/simple-example-for-scala-covariance-contravariance-and-invariance
class Box1[A] {}
class Box2[+A] {}
class Box3[-A] {}


object chap04b {

	def testMyMaybe(inp: MyMaybe[Int]): Unit = {
		inp match {
			case MyJust(x) => println("Value equals: " + x + "  " + inp.isEmpty)
			case MyNil => println("Nothing " + inp.isEmpty)
		}
	}

	// example from https://coderwall.com/p/dlqvnq/simple-example-for-scala-covariance-contravariance-and-invariance
	def usage1(box:Box1[Mammal]){}
	def usage2(box:Box2[Mammal]){}
	def usage3(box:Box3[Mammal]){}


	def runit() {
		testMyMaybe(MyJust(89))
		testMyMaybe(MyNil)

		println()
		// Vector is a covariant class => if A is a subtype of B then Vector[A] is a subtype of Vector[B]. 
		// in our example, Dog is a subtype of Animal; therefore Vector[Dog] is a subtype of Vector[Animal]
		// this is the classical use-case
		val v1: Vector[Animal] = Vector.empty[Animal]
		// but we are also allowed to store a subtype (Vector[Dog])  in the supertype (Vector[Animal])
		val v2: Vector[Animal] = Vector.empty[Dog]
		// the opposite is not allowed
		//val v3: Vector[Dog] = Vector.empty[Animal]

		// Array is an invariant class => a subtype must not be used in place of the supertype
		val v4: Array[Animal] = Array.empty[Animal]
		// this does not work
		// val v5: Array[Animal] = Array.empty[Dog]


		// Box1 is invariant -> we are only allowed to use the same type
		// this does not work usage1(new Box1[Animal])
		usage1(new Box1[Mammal])
		// this does not work usage1(new Box1[Dog])

		// Box2 is covariant -> we may use the type and a subtype
		// this does not work usage2(new Box2[Animal])
		usage2(new Box2[Mammal])
		usage2(new Box2[Dog])

		// Box3 is contravariant  -> we may use the type and the supertype
		usage3(new Box3[Animal])
		usage3(new Box3[Mammal])
		// this does not work usage3(new Box3[Dog])

	}
}