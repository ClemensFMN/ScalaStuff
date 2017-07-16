import scala.collection.mutable.Map

// obtaining the "cross product" of two lists
def cross(l1:List[Int], l2:List[Char]) = for(x<-l1;y<-l2) yield(x,y)


val rows = List(1,2,3,4,5,6,7,8,9)
val cols = List('a','b','c','d','e','f','g','h','i')

// all 9x9 = 81 positions of the sudoku space
val all = cross(rows, cols)


// now lets create lists of list
val n1 = rows.sliding(3,3).toList
val n2 = cols.sliding(3,3).toList

// obtaining the 3x3 groups
val groups = for(x<-n1;y<-n2) yield cross(x,y)

// some kind of intermediate structure
val neighbours = Map[(Int,Char), List[(Int,Char)]]()

// go through all elements
for(element <- all) {
  // and all groups
  for(g<-groups) {
    // if the group contains the element
    if(g.contains(element)) {
      // then the group forms part of the neighbours
      // retrieve the elements from the same row
      val rowAdd = all.filter(x=>x._1 == element._1)
      // and the element from the same column
      val colAdd = all.filter(x=>x._2 == element._2)
      // and throw it all in a list
      neighbours += (element -> (g ++ rowAdd ++ colAdd))
    }
  }
}

//println(neighbours)
//println(neighbours((4,'c')))

// so far, so good, but: (i) we have duplicated & (ii) the element itself is contained in the value
// use a set -> this gets rid of the duplicates
val neighbours_final = Map[(Int,Char), Set[(Int,Char)]]()

for(element <- all) {
  val temp = neighbours(element)
  val s1 = temp.toSet
  // and remove the element  
  neighbours_final += (element -> (s1 - element))
}


println(neighbours_final(4,'c'))




