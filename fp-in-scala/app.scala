object Hi {


    def main(args: Array[String]) {

        //ListStuff.runit
        //Chap02.runit
        //CaseClasses.runit
        //Chap03.runit()

        val c1 = new C(3)
        println(c1)

        OTst.objectFunction

        val d1 = new D(4)
        println(d1)
        d1.increment()
        println(d1)

        val d2 = D(4)
        println(d2)
        d2.increment()
        println(d2)
    }
} 
