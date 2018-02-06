/*
 Problem #8: More or less a CPU Simulation
*/

// we define our operations as case classes
abstract class Op
// straightforward
case class Inc(name:String, value:Int) extends Op
case class Dec(name:String, value:Int) extends Op
// this one is a bit mroe tricky as the condition is defined in a block
case class IncCond(name:String, value:Int, cond:Int=>Boolean) extends Op

// the registers are modeled as map (String->Int)
def applyOp(o:Op, sin:Map[String, Int]):Map[String,Int] = {
	o match {
		case Inc(n, v) => {
			val old = sin.getOrElse(n,0) // we consider the case that the reg is not "known"
			sin + (n -> (v + old)) // and update the state accordingly
		}
		case IncCond(n,v,blk) => { // the conditional ops a re a bit more tricky
			val old = sin.getOrElse(n,0)
			val nw = if(blk(old)) {	// as we need to evaluate the condition
						v + old 
						}
					 else old
			sin + (n -> nw)
		}
	}
}

// this one executes a list of operations. It executes command by command
// and calls itself with the new state and the remaining commands
def applyOpList(ops:List[Op], init:Map[String,Int]):Map[String,Int] = {
	ops match {
		case Nil => init
		case o :: os => {
			val newState = applyOp(o, init)
			applyOpList(os, newState)
		}
	}
}



val state = Map[String,Int]()
applyOp(Inc("a", 1), state)
applyOp(IncCond("a", 3, a => a==1), state)

val cmds = List( Inc("a", 1),
	             Inc("b", 5),
	             IncCond("a", 3, a => a==1))

applyOpList(cmds, state)

/*
 TODO
 This is all nice and good but the interesting question is how do we get the
 command strings into the case classes? This is especially super tricky with IncCond. How to turn the string into a scala code block?

*/
