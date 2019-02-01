// unfortunately not my own solution...
// taken / inspired from https://github.com/matelaszlo/advent-of-code-scala/blob/master/src/main/scala/com/lmat/adventofcode/year2018/Day05.scala

def areReacting(c1: Char, c2: Char):Boolean = {
  c1 != c2 && c1.toLower == c2.toLower
}


// we have a function which runs through the input string once and continuously builds up a built string
def reactOnce(inp:Vector[Char], built:Vector[Char]):Vector[Char] = {
  inp match { // pattern matching on the input vector
    case p1 +: p2 +: rest if areReacting(p1, p2) => reactOnce(rest, built) // the first two components are reacting -> continue with the rest of input & keep built 
    case p1 +: p2 +: rest                        => reactOnce(p2 +: rest, built :+ p1) // otherwise, continue one char further down the input & add p1 to the built list
    case p                                       => built ++ p // corner case one element left => add to built and we are done
  }
}

// call reactOnce 
def iterate(polymer: Vector[Char]): Vector[Char] = {
      val next = reactOnce(polymer, Vector())
      if(next == polymer) polymer
      else iterate(next)
}


val s = "abBAc"
val inp = s.toCharArray.toVector


reactOnce(inp, Vector())
iterate(inp)



/*
def match1(x:Vector[Int]):Unit = {
  x match { // simpler(?) match stuff
    case Vector(v1)         => println(v1) // match one-element vector
    case Vector(v1,v2)      => println(v1, v2) // match two-element vector
    case Vector(v1,v2,rest @ _*) => println(v1, v2, rest) // match longer vectors (min length 3)
  }
}

match1(Vector(1)) // -> 1
match1(Vector(1,2))// -> (1,2)
match1(Vector(1,2,3))// -> (1,2,Vector(3))
match1(Vector(1,2,3,4))// -> (1,2,Vector(3, 4))


def match2(x:Vector[Int]):Unit = {
  x match {
    case Vector(v1,v2,rest @ _*) if v1==v2 => println("equal", v1, v2, rest)
    case Vector(v1,v2,rest @ _*)           => println("not equal", v1, v2, rest)
    case Vector(rest @ _*)                 => println(rest)
  }
}

match2(Vector(1,1,3))// -> (equal,1,1,Vector(3))
match2(Vector(1,2,3))// -> (not equal,1,2,Vector(3))
match2(Vector(1,1,3,4))// -> (equal,1,1,Vector(3, 4))
match2(Vector(1,2,3,4))// -> (not equal,1,2,Vector(3, 4))
match2(Vector(1,1))// -> (equal,1,1,Vector())
match2(Vector(1))// -> Vector(1)

 */





