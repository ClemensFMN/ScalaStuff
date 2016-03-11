object CaseClasses {
	
	abstract class Cls
	case class c1(x: Int) extends Cls
	case class c2(x: String) extends Cls
	case class c3(x1: Int, x2: Int) extends Cls

	// some stupid example
	def simp(x: Cls) = {
		x match {
			case c1(x) => println("class c1: " + x)
			case c2(x) => println("class c2: " + x)
			case c3(x1, x2) => println("class c3: " + x1 + "..." + x2)
		}
	}

	def simp2(x: Cls) = {
		x match {
			case c1(x) => x
			case c2(x) => 0
			case c3(x1,x2) => x1 + x2
		}
	}

	// a tree ADT
	abstract class Tree
	// consisting of branches (inside the tree)
	case class Branch(vl: Int, left: Tree, right: Tree) extends Tree
	// and leaves
	case class Leaf(vl: Int) extends Tree

	// implementing a simple tree traversal
	def traverseTree(t: Tree): Unit = {
		t match {
			case Leaf(x) => println("Leaf: " + x)
			case Branch(v,l,r) => println("Branch: " + v); traverseTree(l); traverseTree(r)
		}
	}

	def countLeves(t: Tree): Int = {
		t match {
			case Leaf(_) => 1
			case Branch(v,l,r) => countLeves(l) + countLeves(r)
		}
	}


	abstract class Expression
	case class Value(x: Int) extends Expression
	case class BinOp(op: String, left: Expression, right: Expression) extends Expression

	def evalExp(ex: Expression): Int = {
		ex match {
			case Value(x) => x
			case BinOp("+", l, r) => evalExp(l) + evalExp(r)
			case BinOp("*", l, r) => evalExp(l) * evalExp(r)
		}
	}


	def runit() = {
		simp(c1(3))
		simp(c2("hello"))
		simp(c3(4,5))

		// we can iterate over a vector of case classes
		val items = Vector(c1(3), c2("hello"), c3(5,6))
		// and yield the matching function
		val r = for(i <- items) yield simp2	(i)
		println(r)
	

		val t1 = Branch(1, Branch(2, Branch(3, Leaf(4), Leaf(5)), Leaf(6)), Leaf(7))
		traverseTree(t1)
		println("Number of leaves: " + countLeves(t1))

		val e1 = BinOp("+", Value(3), BinOp("*", Value(4), Value(5)))
		println("Result: " + evalExp(e1))

	}

}