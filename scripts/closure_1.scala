// a closure binds the open terms with values at the time of closure

// this function creates an adder function which is parametrized by an "outer" parameter a
def makeAdder(a:Int) = (x:Int) => x + a

// call the function to return one adder
val add1 = makeAdder(1)
println(add1(1))

val add5 = makeAdder(5)
println(add5(1))

// Honestly, I'm a bit lost what cool stuff I can do with that...




