object ListStuff {

  def myHead[T](l: List[T]): T = {
    l match {
      case Nil => error("Empty List")
      case x::xs => x
    }
  }

  // tail recursion does not work here, because we build up a chain of
  // "unresolved values" myLength(xs); the sum 1 + myLength(xs) can not be
  // evaluated during the current function call but needs to wait on the stack
  // till the recursion ends
  def myLength[T](l: List[T]): Int = {
    l match {
      case Nil => 0
      case x::xs => 1 + myLength(xs)
    }
  }

  
  def myLength2[T](l: List[T]): Int = {
    @annotation.tailrec
    def _myLength2[T](l: List[T], len: Int): Int = {
      l match {
        case Nil => len
        // this case can be evaluated right away: call _myLength2 with new
        // parameters; no waiting for the result of _myLength2 is necessary
        case x::xs => _myLength2(xs, 1 + len)
      }
    }
    _myLength2(l, 0)
  }


  def myMap[T](func: T=>T, l: List[T]): List[T] = {
    l match {
      case Nil => Nil
      case x::xs => func(x) :: myMap(func, xs)
    }
  }

  def myMap2[T](func: T=>T, l: List[T]): List[T] = {
    def _myMap2[T](lold: List[T], lnew: List[T]): List[T] = {
      lold match {
        case Nil => lnew.reverse
        // for (currently) unknown reasons, this does not work...
        case x::xs => _myMap2(xs, func(x) :: lnew)
      }
    }
    _myMap2(l, Nil)
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

    val l1 = List(1,2,3,5,7,4)

    println(myHead(l1))
    println(myLength(l1))
    println(myLength2(l1))
    println(myMap( (x:Int)=>2*x, l1))
    println(myMap2( (x:Int)=>x, l1))
    println(myDrop(l1, 2))
    println(myAt(l1, 2))
    println(mySum(l1))
    
    println(l1.zipWithIndex)
    
    println(myDoubleMatch(List(2,3)))
    println(myDoubleMatch(List(2,3,4)))
    println(myDoubleMatch(List(2,3,4,5)))
  }
}