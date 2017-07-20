val mp = Map(1->"One", 2->"Two", 5->"Five")

// this throws an Exception when the key is not found ; try with 4...
val res1 = mp(1)
println(res1)

// this returns an Option with None in case the key does not exist
val res2 = mp.get(1)
println(res2)

// we can use a match expression to deal with the Option type...
def processRes(res: Option[String]) = {
  res match {
    case Some(n) => println("Key found; returned: " + n)
    case None => println("No value found :-(")
  }
}

processRes(res2)

// let's build a list of Options
val res3 = for(i<- 1 to 5) yield mp.get(i)
println(res3)

// an option type is like a list; if there is Some, it is a list with one element; in case of None, it is an empty list
// we can use flatten to remove the None Values and "unpack" the Somes
println(res3.flatten)


