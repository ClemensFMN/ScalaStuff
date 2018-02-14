// differences between contravariant, covariant & invariant

class SuperClass {}
class MainClass extends SuperClass
class SubClass extends MainClass {}

// define different boxes for the classes above
class CovariantBox[+T] {}
class ContravariantBox[-T]{}
class InvariantBox[T]{}

def method1(box:CovariantBox[MainClass]){}

// this fails
// method1(new CovariantBox[SuperClass])
method1(new CovariantBox[MainClass])
method1(new CovariantBox[SubClass])
// in other words, in case of a covariant box, the box may hold the class
// itself or a subclass

def method2(box:ContravariantBox[MainClass]){}

method2(new ContravariantBox[SuperClass])
method2(new ContravariantBox[MainClass])
// this fails
// method2(new ContravariantBox[SubClass])
// in other words, in case of a contravariant box, the box may hold the class
// itself or a superclass

def method3(box:InvariantBox[MainClass]){}

// this fails
// method3(new InvariantBox[SuperClass])
method3(new InvariantBox[MainClass])
// this fails
// method3(new InvariantBox[SubClass])
// in case of invariant boxes, the box may only hold the class itself








