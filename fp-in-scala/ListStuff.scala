object ListStuff {

  def myHead[T](l: List[T]): T = {
    l match {
      case Nil => error("Empty List")
      case x::xs => x
    }
  }

  def myLength[T](l: List[T]): Int = {
    l match {
      case Nil => 0
      case x::xs => 1 + myLength(xs)
    }
  }

  def myMap[T](func: T=>T, l: List[T]): List[T] = {
    l match {
      case Nil => Nil
      case x::xs => func(x) :: myMap(func, xs)
    }
  }

  @annotation.tailrec
  def myDrop[T](l: List[T], pos: Int): List[T] = {
    l match {
      case Nil => Nil
      case x::xs => if(pos==1) xs else myDrop(xs, pos-1)
    }
  }
  
  @annotation.tailrec
  def myAt[T](l: List[T], pos: Int): T = {
    l match {
      case Nil => error("Empty List")
      case x::xs => if(pos==0) x else myAt(xs, pos-1)
    }
  }

  def mySum(l: List[Int]):Int = {
    l match {
      case Nil => 0
      case x::xs => x + mySum(xs)
    }
  }

  def myDoubleMatch(l: List[Int]): Int = {
    l match {
      case List(x,y) => x
      case List(x,y,_) => x + y
      case List(x,y,_*) => x * y
      case _ => 0
    }
  }
  
  def runit() {

    val l1 = List(1,2,4,6,2,1)

    println(myHead(l1))
    println(myLength(l1))
    println(myMap( (x:Int)=>2*x, l1))
    println(myDrop(l1, 2))
    println(myAt(l1, 2))
    println(mySum(l1))
    
    println(l1.zipWithIndex)
    
    println(myDoubleMatch(List(2,3)))
    println(myDoubleMatch(List(2,3,4)))
    println(myDoubleMatch(List(2,3,4,5)))
  }
}