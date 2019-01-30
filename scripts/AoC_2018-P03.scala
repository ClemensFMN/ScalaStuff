/*
The idea is to have a map (Int,Int)->List of Claim IDs (which are Strings; e.g. #1)

#123 @ 3,2: 5x4

...........
...........
...#####...
...#####...
...#####...
...#####...
...........
...........
...........

Every claim line updates this mapping:

No claim before: A new key-value pair is added
Already a claim: Claim ID is added to the list

For the line above this will be the following updates

(3,2)->#123, (4,2)->#123, (5,2)->#123, (6,2)->#123, (7,2)->#123, (8,2)->#123
(3,3)->#123 ... 
...
(3,5)->#123               ...                                    (8,5)->#123

*/


def updateClaimMap(clmPos:(Int,Int), clmID:String, clmMap:Map[(Int,Int),List[String]]):Map[(Int,Int),List[String]] = {

  if(clmMap.contains(clmPos)) {
    val newVal = clmID :: clmMap(clmPos)
    val newMap = clmMap + (clmPos -> newVal)
    newMap
  }
  else {
    val newMap = clmMap + (clmPos -> List(clmID))
    newMap
  }
}


// some initial claimmap for testing purposes
val clmMap1 = Map((1,1)->List("123"), (1,2)->List("123"))
// add a claim to an already claimed position 
println( updateClaimMap((1,1), "xxx", clmMap1) )
// add a claim to a new position
println( updateClaimMap((3,3), "xxx", clmMap1) )



// parse claim lines and update the provided claim map accordingly
def processClaimLine(s:String, clmMap:Map[(Int,Int),List[String]]):Map[(Int,Int),List[String]] = {
  var res = clmMap

  val pattern="(#\\d+) @ (\\d+),(\\d+):(\\d+)x(\\d+)".r
  val pattern(id,xpos,ypos,w,h)=s

  println(id)

  for(x<-xpos.toInt+1 to xpos.toInt+w.toInt) {
    for(y<-ypos.toInt+1 to ypos.toInt+h.toInt) {
      res = updateClaimMap((x,y), id, res)
    }
  }
  res
}

// testing
var clmMap = Map[(Int,Int),List[String]]()
val s="#123 @ 3,2:5x4"
println( processClaimLine(s, clmMap))

// define some claim lines
val lines = """#123 @ 3,2:5x4
#124 @ 4,3:1x1
#125 @ 10,10:1x1
#126 @ 5,4:1x1
"""

// use a fold to update the claim map based on line-wise input
var res = lines.lines.foldLeft(Map[(Int,Int),List[String]]()) {
  (acc,i) => // acc contains the map updated so far, i contains the current claim line
  processClaimLine(i, acc)
}

// how many fields are claimed by more than one?
val numMoreOne = res.values.map(_.size).count(_>1)

