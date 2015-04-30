import java.util.Random

object Hi {

    def allTours(cities: Vector[Int], start: Int) = {
        for{x <- cities.permutations if x.head==start} yield x:+start
    }


    def dist(a: (Int,Int), b: (Int,Int)) = {
        Math.abs(a._1 - b._1) + Math.abs(a._2 - b._2)
    }

    def pathLength(coord: Vector[(Int,Int)], path: List[Int]): Int = {
        path match {
            case x::y::ys => dist(coord(x),coord(y)) + pathLength(coord, path.tail)
            case _ => 0
        }
    }

    def nn_path(coord: Vector[(Int,Int)], start: Int) = {
        val N = coord.length
        var path = start :: Nil
        var unvisited = (0 to N-1).toSet -- path
        while(unvisited.size > 0) {
            val dists = for(x<-unvisited) yield (x, dist(coord(path.head), coord(x)))
            val new_elem = dists.minBy(x => x._2)
            path = new_elem._1 :: path
            unvisited = unvisited - new_elem._1
        }
        path = start :: path
        (path, pathLength(coord, path))
    }

    def nn_path_recursive(coord: Vector[(Int,Int)], start: Int) = {

        def nn_path2(coord: Vector[(Int,Int)], start: Int, unvisited: Set[Int]): List[Int] = {
            if(unvisited.size>0) {
                val dists = for(x<-unvisited) yield (x, dist(coord(start), coord(x)))
                val new_elem = dists.minBy(x => x._2)
                new_elem._1 :: nn_path2(coord, new_elem._1, unvisited - new_elem._1)
            }
            else Nil
        }

        val N = coord.length
        var unvisited = (0 to N-1).toSet - start
        val res = start :: (nn_path2(coord, start, unvisited) :+ start)
        (res.reverse, pathLength(coord, res))

    }


    def main(args: Array[String]) {

        val cities = Vector(0,1,2,3,4)
        val at = allTours(cities, 0);

        val rnd = new Random()
        rnd.setSeed(12345)

        val city_coords = for{i<-cities} yield(rnd.nextInt(50), rnd.nextInt(50))
        for(c <- city_coords) println(c)

        println(dist(city_coords(0), city_coords(2)))
  
        println(pathLength(city_coords, List(0,2,4)))
  
        var res = Vector[(Vector[Int], Int)]()
        
        for(path <- at) {
            println(path + " -> " + pathLength(city_coords, path.toList))
            res = res :+ (path, pathLength(city_coords, path.toList))
        }

        println(res.minBy(x => x._2))

        println(nn_path(city_coords, 0))

        println(nn_path_recursive(city_coords, 0))
  }
} 
