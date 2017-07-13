abstract class Expr

case class Number(num:Int) extends Expr
case class Plus(left:Expr, right:Expr) extends Expr

def evl_exp(e: Expr):Int = {
  e match {
    case Number(n) => n
    case Plus(l,r) => evl_exp(l) + evl_exp(r)
  }
}


val e1 = Plus(Number(3), Plus(Number(1), Number(4)))
println(evl_exp(e1))

