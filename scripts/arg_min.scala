// given a list of (random) numbers, find the position and value of the minimum value

//list length
val N = 5

// create a list with random elements; from http://alvinalexander.com/scala/how-to-generate-random-numbers-characters-sequences-in-scala
val rng = scala.util.Random
val data = for(i<- 0 to N) yield rng.nextInt(10)

// zip the data together with an index -> this yields a list of tuples (value,index)
// val full = data.zip(Range(0,N))
// somewhat nicer in that the length of the array is not needed for the zip...
val full = data.zipWithIndex

full.foreach(println)

// search the list minimum based on first element in the tuples
val min = full.minBy(e => e._1)

println("Minimum value is: " + min._1 + ", located at: " + min._2)

