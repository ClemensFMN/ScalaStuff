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

// parsing a "sudoku string" describing a problem into a Map[(Int,Char), Set[Int]]() structure
def parseStr(s:String) = {
  val data = all.zip(s)
  //println(data)
  val board = Map[(Int,Char), Set[Int]]()
  for(e <-data) {
    val value = if(e._2=='0') Set(1,2,3,4,5,6,7,8,9) else Set(e._2.asDigit)
    //println(e + "..." + value)
    board += e._1 -> value
  }
  board
}

// convert the values of one board position into a string
def posString(s: Set[Int]) = s.toList.sorted.fold("")((s,i) => s+i.toString)

// print the whole board
def printBoard(b:Map[(Int,Char), Set[Int]]) = {
  // obtain length of the longest sequence
  //val width = (for(pos<-all) yield b(pos).size).max
  val width = b.values.map(x=>x.size).max
  for(r<-rows) {
    // for every row, make a new string
    var s = ""
    for (c<-cols) {
      // append every column's value
      s += posString (b((r,c)))
      // and fill with spaces
      val space = " " * (width - b(r,c).size + 1)
      s += space
    }
    // print out the row
    println(s)
  }
}

// one round of constraint propagation; i.e. we go over all board positions ONCE
def constProp(b:Map[(Int,Char), Set[Int]]) = {
  // create an empty map to hold the board after the constraint propagation
  val bnew = Map[(Int,Char), Set[Int]]()
  // go over all positions
  for(pos<-all) {
    // basic idea is to collect the values from  all neigbours when they have ONE value
    var neighbourValues = Set[Int]()
    for(n<-neighbours(pos)) {
      // if the neighbour value has length = 1, add this vlaue to the collection
      if(b(n).size == 1) {
        neighbourValues = neighbourValues ++ b(n)
      }
    }
    // remove the collected values from the current position's values
    bnew(pos) = b(pos) -- neighbourValues
  }
  (bnew, bnew == b)
}


def constPropComplete(b:Map[(Int,Char), Set[Int]]) = {
  var bnew = b
  var changed = false
  while(changed == false) {
     val res = constProp(bnew)
     printBoard(res._1)
     println
     changed = res._2
     bnew = res._1
  }
  bnew
}


abstract class Result
case class Solution() extends Result
case class Ambiguous() extends Result
case class NotSolvable() extends Result

def isSolution(b:Map[(Int,Char), Set[Int]]) = {
  // if all positions have length 1 we have a solution
  if(b.values.map(_.size).forall(_==1)) Solution()

  // if there is at least one position with length 0, the thing is not solvable
  else if(b.values.map(_.size).exists(_==0)) NotSolvable()
  else Ambiguous()

}

// taken from the article http://norvig.com/sudoku.html
// also the first entry in http://norvig.com/easy50.txt
// this sudoku can be completely solved via constraint propagation...
//val brd1 = "003020600900305001001806400008102900700000008006708200002609500800203009005010300"

// a hard puzzle which can not be solved with CP alone...
val brd1 = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"



var b1 = parseStr(brd1)
printBoard(b1)

println()

val res = constPropComplete(b1)

printBoard(res)
println(isSolution(res))




