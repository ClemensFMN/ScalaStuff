// https://github.com/fpinscala/fpinscala/blob/master/answers/src/main/scala/fpinscala/gettingstarted/GettingStarted.scala

object Chap02 {

	def compose[A,B,C](f:B=>C, g:A=>B): A=>C = {
        a => f(g(a))
    }

    def myFold[T](l:List[T], f:(T,T)=>T, z: T): T = {
        l match {
            case Nil => z
            case x :: xs => f(x, myFold(l.tail, f, z))
        }
    }

    // this is a function taking one parameter which return a function (from Int to Int)
    def parametricAdder(p: Int): (Int => Int) = {
        // the returned function's parameter is x and we add the parameter p to it
        x => x + p
    }

    def findFirst[A](as:Array[A], p: A => Boolean) : Int = {
        def loop(n: Int): Int = {
            if(n >= as.length) -1
            else if (p(as(n))) n
            else loop(n+1)
        }
        loop(0)
    }

    def myPartial[A,B,C](a:A, f:(A,B) => C): B => C = {
        (b:B) => f(a,b) // return a function which takes a parameter b of type B and returns the value f(a,b)
    }

    def myCurry[A,B,C](f:(A,B)=>C):A=>(B=>C) = {
        a => (b => f(a,b))
    }

    def myUncurry[A,B,C](f:A=>(B=>C)):(A,B)=>C = {
        (a:A, b:B) => f(a)(b)
    }


    def runit() = {
    	val f = (x:Int)=> 2*x
        val g = (x:Int)=> x+1
        val fg = compose(g,f) // this yields 2*x+1
        println(fg(4))
        val gf = compose(f,g) // this yields 2*(x+1)
        println(gf(4))

        val l = List(1,2,3)
        println(myFold(l, (x:Int,acc:Int) => x + acc, 0))

        // create one such function
        val add3 = parametricAdder(3)
        // and use it
        println(add3(7))

        println(findFirst(Array(1,2,3,4), (x:Int)=>x==2))
        println(findFirst(Array(1,2,3,4), (x:Int)=>x>2))

        val f1 = (x:Int, y:Int) => x+y
        val fpartial = myPartial(2,f1)
        println(fpartial(3))

        val fcurry = myCurry(f1)
        val funcurry = myUncurry(fcurry)
        println(f1(2,3), fcurry(2)(3), funcurry(2,3))

    }
}
