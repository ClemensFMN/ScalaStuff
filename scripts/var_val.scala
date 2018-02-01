import collection.mutable._
import scala.collection.mutable


// the list itself is immutable, but bound to a var
// so we cannot change the list per se, but - since we use a var - we
// can assign the var to a new list
var lst = List(1,3,4,5,6)

// here we append 10 to the list and reassign it to the same var
lst = 10 :: lst

// the effect is that we have appended 10 to the list
println(lst)

// if we use a val, this does NOT work
val lst2 = List(1,3,4,5,6)
// we cannot reassign a val - the next line gives a compile error
// lst2 = 10 :: lst2

// we can use a mutable collection type
val ar1 = mutable.ArrayBuffer(1,2,3,4,5)
// ... which allows appending elements
// note that this changes the list as such; i.e. we can use this with a val as
// the object ar1 points to does NOT change
ar1 += 10
println(ar1)

