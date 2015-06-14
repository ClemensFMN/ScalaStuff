class SomeAttributesRO(val surname: String, val famname: String)
class SomeAttributesRW(var surname: String, var famname: String)
class SomeAttributesPrivate(surname: String, famname: String) {
	def getSurname() = surname
}

class SomeAttributesPrivate2(val surname: String, val famname: String, private var _age: Int) {
	// getter function
	def age = _age
	// setter function
	def age_=(newAge: Int) = _age = newAge
}

// a simple class to test operators
class MyBool(x: Boolean) {
	// every method taking one argument can be used as infix operator
	def and(that: MyBool): MyBool = if (x) that else this
	def or(that: MyBool): MyBool = if (x) this else that
	def negate: MyBool = new MyBool(!x)
	// we need to be careful with the spacing here
	def unary_! : MyBool = new MyBool(!x)
	def ! : MyBool = new MyBool(!x)
	override def toString() = x.toString
}


object chap03 {

	def runit()  = {
		val sa1 = new SomeAttributesRO("James", "Bond")
		println(sa1.surname, sa1.famname)
		// that's not possible: sa1.surname="Susi"

		val sa2  = new SomeAttributesRW("James", "Bond")
		println(sa2.surname, sa2.famname)
		sa2.surname = "Mr James"
		println(sa2.surname, sa2.famname)

		val sa3 = new SomeAttributesPrivate("James", "Bond")
		//does not work - the attributes are private: println(sa3.surname)
		println(sa3.getSurname)

		val sa4 = new SomeAttributesPrivate2("James", "Bond", 34)
		println(sa4.surname, sa4.famname)
		// of course not: println(sa4._age)
		println(sa4.age)
		sa4.age = 35
		println(sa4.age)

		val mb1 = new MyBool(true)
		val mb2 = new MyBool(false)
		// this is actually mb1.or(mb2) => syntactic sugar allows for this:
		println(mb1 or mb2)
		println(mb1 and mb2)
		println(! mb1)
		println(mb1!)

	}

}
