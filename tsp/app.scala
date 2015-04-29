object Hi {

    def dist(coord: Vector[Int], a: Int, b: Int) = {
        Math.abs(coord(b) - coord(a))
    }

    def pathLength(coord: Vector[Int], path: List[Int]): Int = {
        path match {
            case x::y::ys => dist(coord,x,y) + pathLength(coord, path.tail)
            case _ => 0
        }
    }

    def nn_path(coord: Vector[Int], start: Int) = {
        val N = coord.length
        var path = start :: Nil
        var unvisited = (0 to N-1).toSet -- path
        while(unvisited.size > 0) {
            val dists = for(x<-unvisited) yield (x, dist(coord, path.head, x))
            val new_elem = dists.minBy(x => x._2)
            path = new_elem._1 :: path
            unvisited = unvisited - new_elem._1
        }
        start::path
    }


    def main(args: Array[String]) {

        val coord = Vector(3,7,5,10,-7)
  
        println(dist(coord, 0, 2))
  
        println(pathLength(coord, List(0,2,4)))
  
        println(nn_path(coord, 0))
  }
} 
