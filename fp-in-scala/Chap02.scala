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

    def runit() = {
    	val f = (x:Int)=>2 * x
        val g = (x:Int)=>x + 1
        val fg = compose(g,f)
        println(fg(4))

        val l = List(1,2,3)
        println(myFold(l, (x:Int,acc:Int) => x + acc, 0))
    }
}
