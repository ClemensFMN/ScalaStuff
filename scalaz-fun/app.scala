import scalaz._
import std.option._, std.list._ // functions and type class instances for Option and List



object app {


    def main(args: Array[String]) {


		println(Apply[Option].apply2(some(1), some(2))((a, b) => a + b))

		println(Traverse[List].traverse(List(1, 2, 3))(i => some(i)))

	}
}
