// good old list creation via explicit constructor
val l1 = List(1,2,3)
println(l1)

// create a vector of tuples (index, value)
val l1ind = l1.indices.zip(l1)
println(l1ind)

// via range
val l2 = List.range(1,10)
println(l2)

// partition according to predicate
val l2part = l2.partition(x => x%2==0)
println(l2part)

val l2group = l2.groupBy(x => x%2==0)
println(l2group)

// Group elements in fixed size blocks by passing a "sliding window" over them
// first param = length of window
// second param = distance between first elements of successive groups
val slideIter = l2.sliding(3,1)
slideIter.foreach(print)
println

val l22 = (1 to 10).toList
println(l22)

// which can take a step value as well...
val l3 = List.range(1,10,2)
println(l3)

// filling a list with a fixed value
val l4 = List.fill(5)(0)
println(l4)

// mapping over a range
val l5 = List.range(1,10).map(x => x*x)
println(l5)

val l6 = l5.filter(_ < 30)
println(l6)

