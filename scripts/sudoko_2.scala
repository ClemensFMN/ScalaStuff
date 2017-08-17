import scala.collection.mutable.Map

// obtaining the "cross product" of two lists
def cross(l1:List[Int], l2:List[Char]) = for(x<-l1;y<-l2) yield(x,y)


val rows = (1 to 9).toList
val cols = ('a' to 'i').toList

// all 9x9 = 81 positions on the sudoku board
val all = cross(rows, cols)


// in the following, we want to create the neighbours map: for every board 
// location, it contains a list of "neighbour" positions: all positions on the 
// same row and column and all positions in one of the 3x3 subboard

// let's create these 3x3 subboards first
val n1 = rows.grouped(3).toList
val n2 = cols.grouped(3).toList

// obtaining the 3x3 groups
val groups = for(x<-n1;y<-n2) yield cross(x,y)

// the type of the neighbours map
val neighbours = Map[(Int,Char), Set[(Int,Char)]]()

// fill up the map: go through all elements
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
      // and throw it all in a list, convert to a set (= remove duplicates)
      val temp = (g ++ rowAdd ++ colAdd).toSet
      // remove the element itself & we are done
      neighbours += (element -> (temp - element))
    }
  }
}

//println(neighbours)
println(neighbours((4,'c')))

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
    // basic idea is to collect the values from all neigbours when they have ONE value
    var neighbourValues = Set[Int]()
    for(n<-neighbours(pos) if b(n).size==1) {
        neighbourValues = neighbourValues ++ b(n)
    }
    // remove the collected values from the current position's values
    bnew(pos) = b(pos) -- neighbourValues
  }
  bnew
}

// run several rounds of constraint propagation until the result does not 
// change anymore
// then we decide whether the resulting board is solved, ambiguous or not 
// solvable & return this result
def constPropComplete(b:Map[(Int,Char), Set[Int]]) = {
  var bnew = b
  var changed = true
  while(changed == true) {
     val res = constProp(bnew)
     //printBoard(res)
     changed = bnew != res
     bnew = res
  }
  (bnew, isSolution(bnew))
}

// the possible results from isSolution
abstract class Result
case class Solution() extends Result
case class Ambiguous() extends Result
case class NotSolvable() extends Result

// take a board & return one of the Result case classes
def isSolution(b:Map[(Int,Char), Set[Int]]) = {
  // if all positions have length 1 we have a solution
  if(b.values.map(_.size).forall(_==1)) Solution()
  // if there is at least one position with length 0, the thing is not solvable
  else if(b.values.map(_.size).exists(_==0)) NotSolvable()
  // on all other cases, there is some ambiguity; i.e. at least one field has 
  // more than one allowed value
  else Ambiguous()
}

// the global variable holding information whether we are finished or not
var finished = false

// that's now the final solve function
// we take a board and run one round of constraint propagation over it
// now there are three outcomes
// 1 Const. Prop. returns Solution -> we print the solution and set 
// finished = true
// 2 Const. Prop. returns NotSolvable -> we return immediately
// 3 Const. Prop. returns Ambiguous -> we continue fixing ambiguous board 
// positions and start all over again
def solveIt(b:Map[(Int,Char), Set[Int]]):Unit = {
  // run CP
  val (bnew, solution) = constPropComplete(b)
  var result = bnew

  if(!finished) { // we do the whole thing only when NOT finished
    // decide upon CP result
    solution match {
      // case 1
      case Solution() => {
        println("found solution")
        printBoard(bnew)
        finished = true
      }
      // case 2
      case NotSolvable() => {
      //println("got stuck")
      }
      // case 3
      case Ambiguous() => {
        for(pos <- bnew.keys) {
          // choose one of the ambiguous positions
          if(bnew(pos).size > 1) {
            // fix the value
            // we assign temp(pos) consecutively a value from bnew(pos) if we 
            // are NOT finished
            for(cand <- bnew(pos) if !finished) {
              // store the old position
              var temp = bnew
              temp(pos) = Set(cand)
              //println("go again @ position: " + pos + ", with new value: " + temp(pos))
              // and do it again
              solveIt(temp)
            }
          }
        }
      }
    }
  }
}




// taken from the article http://norvig.com/sudoku.html
// also the first entry in http://norvig.com/easy50.txt
// this sudoku can be completely solved via constraint propagation...
//val brd1 = "003020600900305001001806400008102900700000008006708200002609500800203009005010300"
//slighlty more DOFs -> not solvable via CP alone
val brd1 = "000000000900305001001806400008102900700000008006708200002609500800203009005010300"

// not sure about this one
//val brd1 = "100900000090061000605020100040050030000408760087030005800700500000203070004000601'"


// a hard puzzle which can not be solved with CP alone...
//val brd1 = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
//
// special case of a board which becomes stuck after one round opf CP - what does isSolution say?
//val brd1 = "440000805030000000000700000020000060000080400000010000000603070500200000104000000"



var b1 = parseStr(brd1)
printBoard(b1)

println()



//val res = constPropComplete(b1)
//printBoard(res._1)
//println(res._2)

solveIt(b1)


