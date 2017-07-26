// own implementation of map
def myMap[A,B](f:A=>B, lst:List[A]): List[B] = {
  for{x <- lst} yield f(x)
}


val lst = List(1,2,3,4)
// I don't know why we need a type param in the function...
val res = myMap((x:Int) => x+1, lst)
// interestingly, the "real" thing does NOT require a type in the mapping 
// function...
//val res = lst.map(x=>x+1)
println(res)


val res2 = for{x <- lst if x < 2} yield(x)
println(res2)

val res3 = for{x <- lst} yield(x*x)
println(res3)

val res4 = for{x <- lst; y <-lst } yield(x,y)
println(res4)

val res5 = for{x <- lst; y <-lst if(x < y)} yield(x,y)
println(res5)



