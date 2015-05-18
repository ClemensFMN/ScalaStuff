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

	}

}

