// taken from map_stuff_1.scala


/*
    abcdef contains no letters that appear exactly two or three times.
    bababc contains two a and three b, so it counts for both.
    abbcde contains two b, but no letter appears exactly three times.
    abcccd contains three c, but no letter appears exactly two times.
    aabcdd contains two a and two d, but it only counts once.
    abcdee contains two e.
    ababab contains three a and three b, but it only counts once.

Of these box IDs, four of them contain a letter which appears exactly twice, and three of them contain a letter which appears exactly three times. Multiplying these together produces a checksum of 4 * 3 = 12.


words where a letter which appears twice = bababc, abbcde, aabcdd, abcdee
words where a letter which appears 3 times = bababc, abcccd, ababab

*/


// perform a lookup in a string-int map
// the string is not in the map -> add it with value 1
// the string has been in the map -> add it with value+1
def add_to_map(mp: Map[Char, Int], x: Char):Map[Char, Int] = {
  val k = mp.getOrElse(x, 0)
  mp + (x->(k+1))
}

// count letters in a string
def countLetters(s:String):Map[Char, Int] = {
  s.foldLeft(Map[Char,Int]()){ // start with an empty map and fold...
    (acc, i) =>
    add_to_map(acc, i) // the map update from add_to_map into it
  }
}

//println(countLetters("bababc")) // Map(b -> 3, a -> 2, c -> 1)

// check whether one or more letters appears twice. Note that this is does not depend on how many letters appear twice; i.e. AABCDEF and AABBCD are both true 
def containsTwoSameLetters(lst:Iterable[Int]):Boolean = lst.count(_ == 2) > 0

// check whether one or more letters appears three times
def containsThreeSameLetters(lst:Iterable[Int]):Boolean = lst.count(_ == 3) > 0


//println(containsTwoSameLetters(countLetters("bababc").values))
//println(containsThreeSameLetters(countLetters("bababc").values))


// take a list of strings and count how often two same letters appear
def cntTwoSame(inp:List[String]):Int = {
  inp.map(countLetters) // count letters per string
     .map(_.values)     // get the counts
     .map(containsTwoSameLetters(_)) // appear chars (at least) two times?
     .count(_ == true) // count how often this happens
}


cntTwoSame(List("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"))


def cntThreeSame(inp:List[String]):Int = {
  inp.map(countLetters) // count letters per string
     .map(_.values)     // get the counts
     .map(containsThreeSameLetters(_)) // appear chars (at least) two times?
     .count(_ == true) // count how often this happens
}

cntThreeSame(List("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"))

