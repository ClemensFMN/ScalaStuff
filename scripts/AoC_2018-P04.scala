import scala.collection.mutable._

val inp = """[1518-11-01 00:00] Guard 10 begins shift
[1518-11-01 00:05] falls asleep
[1518-11-01 00:25] wakes up
[1518-11-01 00:30] falls asleep
[1518-11-01 00:55] wakes up
[1518-11-01 00:00] Guard 99 begins shift"""

case class TimeStamp(m:Int,d:Int,min:Int)


def buildMap(s:String) = {

  val guardPattern = "\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\] Guard (\\d+) begins shift".r
  val asleepPattern = "\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\] falls asleep".r
  val wakePattern = "\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\] wakes up".r

  val SleepMp = Map[(Int, TimeStamp),Boolean]()

  var currentId = 0
  var startTS = TimeStamp(0,0,0)
  var stopTS = TimeStamp(0,0,0)

  for(s<-inp.lines) {
    println(s)

    s match {
      case guardPattern(y,m,d,h,min,id) => {
        println("Guard" + id)
        currentId = id.toInt
      }
      case asleepPattern(y,m,d,h,min) => {
        println("asleep")
        startTS = TimeStamp(m.toInt, d.toInt, min.toInt)
      }
      case wakePattern(y,m,d,h,min) => {
        println("awake")
        stopTS = TimeStamp(m.toInt, d.toInt, min.toInt)
        //println(currentId)
        //println(startTS)
        //println(stopTS)
        for(mval <-startTS.min to stopTS.min) {
          SleepMp += (currentId, TimeStamp(startTS.m, startTS.d, mval))->true
        }
      }
      case _ => println("error")
    }
  }
  println(SleepMp)

}




