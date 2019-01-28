val mp1 = Map('a'->1, 'b'->5)

println(mp1('a'))

println(mp1.getOrElse('a', 0))
println(mp1.getOrElse('x',0))

// perform a lookup in a string-int map
// the string is not in the map -> add it with value 1
// the string has been in the map -> add it with value+1
def add_to_map(mp: Map[Char, Int], x: Char):Map[Char, Int] = {
  val k = mp.getOrElse(x, 0)
  mp + (x->(k+1))
}

println(add_to_map(mp1, 'a')) // Map(a -> 2, b -> 5)
println(add_to_map(mp1, 'x')) // Map(a -> 1, b -> 5, gdfbdfb -> 1)

// count letters in a string
def countLetters(s:String):Map[Char, Int] = {
  s.foldLeft(Map[Char,Int]()){ // start with an empty map and fold...
    (acc, i) =>
    add_to_map(acc, i) // the map update from add_to_map into it
  }
}

println(countLetters("hallo welt")) // Map(e -> 1, t -> 1, a -> 1,   -> 1, l -> 3, h -> 1, w -> 1, o -> 1)
