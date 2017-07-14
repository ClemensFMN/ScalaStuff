// a closure binds the open terms with values at the time of closure

// this function creates an adder function which is parametrized by an "outer" parameter a
def makeAdder(a:Int) = (x:Int) => x + a

// call the function to return one adder
val add1 = makeAdder(1)
println(add1(1))

val add5 = makeAdder(5)
println(add5(1))

// Honestly, I'm a bit lost what cool stuff I can do with that...

// one idea is this:
// we define a function which creates parametrized predicates...
def makeFilterFct(threshold: Int) = (x:Int) => x>threshold


val ar = (0 to 10).toArray

// and now we call the function -> we get one specific predicate
def filt5 = makeFilterFct(5)
ar.filter(filt5).foreach(println)

// another call yields ANOTHER predicate...
def filt8 = makeFilterFct(8)
ar.filter(filt8).foreach(println)

