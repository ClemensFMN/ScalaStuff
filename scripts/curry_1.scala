// a simple function taking one argument
def f1(x:Int) = x+1
// calling the function
println(f1(3))
// since the function takes ONE argument, we can provide the argument inside curly braces as well...
println(f1{3})

// if we want to play the game with functions taking more than one argument, we need currying...
// currying "converts" functions with several arguments into several (partial functions) each taking one argument

// type signature is: sum_curry: (x: Int)(y: Int)Int
def sum_curry(x:Int)(y:Int) = x + y
println(sum_curry(3)(4))

// partial application; i.e. the second parameter is left out
// type signature is: add3: Int => Int
val add3 = sum_curry(3)_
// we can call the function like a normal function (it IS a normal function after all)
val res1 = add3(4)

// we can call this function with curly braces as well (see top of file)
val res2 = add3{4}
println(res1)
println(res2)

// but we can play this trick as well; the first argument parametrizes the 
// function, the last one is provided inside curly braces
val res3 = sum_curry(3) {
  4
}
println(res3)

def applyFunc(x: Int)(f: Int=>Int) = f(x)
val res10 = applyFunc(3){
  x=>x+1
}
println(res10)



