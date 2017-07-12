import java.io._
import scala.io.Source._


val f = new File(".")

val fl = f.listFiles()

for(s <- fl) {
   println(s + "   " + s.length())
}

var source = fromFile("java_int_file.scala")
var lines = source.getLines

for (l <- source) {
   print(l)
}

source.close()