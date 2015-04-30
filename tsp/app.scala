import java.util.Random

object Hi {

    // gives all tour of cities starting in start
    def allTours(cities: Vector[Int], start: Int) = {
        for{x <- cities.permutations if x.head==start} yield x:+start
    }

    // distance between two (2D) points
    def dist(a: (Int,Int), b: (Int,Int)) = {
        Math.abs(a._1 - b._1) + Math.abs(a._2 - b._2)
    }

    // the length of a path between cities located at coord
    def pathLength(coord: Vector[(Int,Int)], path: List[Int]): Int = {
        path match {
            case x::y::ys => dist(coord(x),coord(y)) + pathLength(coord, path.tail)
            case _ => 0
        }
    }

    // the imperative version of the nearest-neighbour traversal
    def nn_path(coord: Vector[(Int,Int)], start: Int) = {
        val N = coord.length
        // initial path starts @ start
        var path = start :: Nil
        // the unvisited cities are all cities but start
        var unvisited = (0 to N-1).toSet -- path
        // as long as there are unvisited cities, we continue
        while(unvisited.size > 0) {
            // calc the dist from the current city to all unvisited ones
            val dists = for(x<-unvisited) yield (x, dist(coord(path.head), coord(x)))
            // we take the closest city as the nex tcity
            val new_elem = dists.minBy(x => x._2)
            // add it to the path
            path = new_elem._1 :: path
            // and remove it from the unvisited cities
            unvisited = unvisited - new_elem._1
        }
        // include the start again to get a closed path
        path = start :: path
        (path, pathLength(coord, path))
    }

    // the same thing done recursivley
    def nn_path_recursive(coord: Vector[(Int,Int)], start: Int) = {
        // this function does all the real work
        def nn_path2(coord: Vector[(Int,Int)], start: Int, unvisited: Set[Int]): List[Int] = {
            //we have unvisited cities
            if(unvisited.size>0) {
                // so lets calculate the distances to them
                val dists = for(x<-unvisited) yield (x, dist(coord(start), coord(x)))
                // take the min
                val new_elem = dists.minBy(x => x._2)
                // and recursivley go again
                new_elem._1 :: nn_path2(coord, new_elem._1, unvisited - new_elem._1)
            }
            // we have finished everything => stop (return Nil)
            else Nil
        }
        //this part does just the boilerplate
        val N = coord.length
        var unvisited = (0 to N-1).toSet - start
        val res = start :: (nn_path2(coord, start, unvisited) :+ start)
        (res.reverse, pathLength(coord, res))

    }


    def main(args: Array[String]) {

        // the cities for the problem
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
