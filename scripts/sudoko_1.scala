import scala.collection.mutable.Map

// obtaining the "cross product" of two lists
def cross(l1:List[Int], l2:List[Char]) = for(x<-l1;y<-l2) yield(x,y)


val rows = List(1,2,3,4,5,6,7,8,9)
val cols = List('a','b','c','d','e','f','g','h','i')

// all 9x9 = 81 positions on the sudoku board
val all = cross(rows, cols)


// in the following, we want to create the neighbours map: for every board 
// location, it contains a list of "neighbour" positions: all positions on the 
// same row and column and all positions in one of the 3x3 subboard

// let's create these 3x3 subboards first
val n1 = rows.sliding(3,3).toList
val n2 = cols.sliding(3,3).toList

// obtaining the 3x3 groups
val groups = for(x<-n1;y<-n2) yield cross(x,y)

// "almost" the final thing; only difference is that it maps onto a list 
val intermed = Map[(Int,Char), List[(Int,Char)]]()

// fill up the map
// go through all elements
for(element <- all) {
  // and all groups
  for(g<-groups) {
    // if the group contains the element
    if(g.contains(element)) {
      // then the group forms part of the 3x3 subboard
      // retrieve the elements from the same row
      val rowAdd = all.filter(x=>x._1 == element._1)
      // and the element from the same column
      val colAdd = all.filter(x=>x._2 == element._2)
      // and throw it all in a list
      intermed += (element -> (g ++ rowAdd ++ colAdd))
    }
  }
}

//println(neighbours)
//println(neighbours((4,'c')))

// so far, so good, but: (i) we have duplicated positions & (ii) the element 
// itself is contained in the list as well
// so we use a set -> this gets rid of the duplicates
val neighbours = Map[(Int,Char), Set[(Int,Char)]]()

for(element <- all) {
  // get the list
  val temp = intermed(element)
  // convert to set -> this removes duplicates
  val s1 = temp.toSet
  // remove the element from the set & assign to the final neighbour map
  neighbours += (element -> (s1 - element))
}

//and we are done; a simple check:
//println(neighbours(4,'c'))




def parseStr(s:String) = {
  val data = all.zip(s)
  //println(data)
  val board = Map[(Int,Char), Set[Int]]()
  for(e <-data) {
    val value = if(e._2=='.') Set(1,2,3,4,5,6,7,8,9) else Set(e._2.asDigit)
    //println(e + "..." + value)
    board += e._1 -> value
  }
  board
}

// not working / incomplete
def printBoard(b:Map[(Int,Char), Set[Int]]) = {
  val width = (for(pos<-all) yield b1(pos).size).max
  for(r<-rows) {
    var s = ""
    for (c<-cols) {
      s += b((r,c))
    }
    println(s)
  }
}

// one round of constraint propagation 
def constProp(b:Map[(Int,Char), Set[Int]]) = {
  //val pos = (1,'a')
  // go over all positions
  for(pos<-all) {
    // basic idea is to init a set with the allowed values of the current position
    // run over all neighbours and remove the neighbour's values from the set
    var neighbourValues = b(pos) 
    //println(neighbourValues)
    for(n<-neighbours(pos)) {
      // special case: if the neighbour's value is unconstrained, then do not remove
      if(b(n).size != 9) neighbourValues = neighbourValues -- b(n)
      //println(n, b(n))
    }
    //println(neighbourValues)
    // and update the current position
    b(pos) = neighbourValues
  }
  b
}


val brd1 = "....3..9....2....1.5.9..............1.2.8.4.6.8.5...2..75......4.1..6..3.....4.6." 

val b1 = parseStr(brd1)
println(b1)
//printBoard(b1)

val bnew = constProp(b1)
println(bnew)
