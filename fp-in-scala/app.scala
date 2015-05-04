
object Hi {

    def compose[A,B,C](f:A=>B, g:B=>C): A=>C = {
        g.compose(f)
    }


    def myFold[T](l:List[T], f:(T,T)=>T, z: T): T = {
        l match {
            case Nil => z
            case x :: xs => f(x, myFold(l.tail, f, z))
        }
    }


    def main(args: Array[String]) {

        val f = (x:Int)=>2 * x
        val g = (x:Int)=>x + 1
        val fg = compose(f,g)
        println(fg(4))

        val l = List(1,2,3)
        println(myFold(l, (x:Int,acc:Int) => x + acc, 0))

    }
} 
