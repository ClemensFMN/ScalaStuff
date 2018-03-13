/* advent of code #3
we encode movements as L(left), U(p), D(own), & R(ight)
and observe that the spiral is created by the following movement pattern: R-U-L-L-D-D-R-R-R-U-U-U-L-L-L-L-D-D-D-D ...
i.e. apart from the first two it follows the order L-D-R-U with each movement step (e.g. L) repeated several times
the repetitions are increased as the spiral increases: it contains the L and D N times and R U N+1 times
before N is incremented

timeseq contains the number of repetitions
mvmt the cylic movement
fnl is then the repetitions in timsep applied to mvmt
*/

val N = 10 // the number of steps we want to create

//val evnnums = loop(0).take(N).toList // we create a list with the first N even numbers
// less magic - this is longer than N, but the zip below takes care of this...
val evnnums = for(i <- 0 to N) yield 2*i

println(evnnums)

val timeseq = evnnums.flatMap(x => List(x,x,x+1,x+1)) // this list contains the number of repetitions
println(timeseq)

val movement = List('L', 'D', 'R', 'U').toArray
// some magic from here https://stackoverflow.com/questions/2097851/scala-repeat-a-finite-list-infinitely
//val mvmt = Stream.continually(movement.toStream).flatten.take(N).toList
// less magic - using the remainder to produce a cyclic array
val mvmt = for(i <- 0 to N) yield movement(i % 4)
println(mvmt)

val mvmt_time = mvmt.zip(timeseq) // a list with pairs containing the actual movement and the number of times the movement shall be applied
println(mvmt_time)

val fnl = mvmt_time.flatMap(e => List.fill(e._2)(e._1)) // now repeat the the movement according to the number of repetitions - that's it
println(fnl)
