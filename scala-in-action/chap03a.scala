// Example of the factory pattern

// the abstract "master" class
abstract class Customer {
	def calcPrice(price: Double): Double
}

// with subclass 1
class PrivateCustomer extends Customer {
	override def calcPrice(price: Double): Double = 1.2*price
}

// and subclass 2
class CompanyCustomer extends Customer {
	override def calcPrice(price: Double): Double = 1.1*price
}

// companion object to the abstract master class -> here is the dispatch logic
// to creating the "correct" subclass
object Customer {
	def apply(roleName:String) = roleName match {
		case "private" => new PrivateCustomer
		case "company" => new CompanyCustomer
	}
}


object chap03a {
	def runit() = {

		// not using the companion object
		val pc = new PrivateCustomer()
		println(pc.calcPrice(10))
		val cc = new CompanyCustomer()
		println(cc.calcPrice(10))

		// using the companion object -> one entry point for the application
		val pc1 = Customer("private")
		println(pc1.calcPrice(10))
		val pc2 = Customer("company")
		println(pc2.calcPrice(10))
	}
}
