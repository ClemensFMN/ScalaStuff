/*
42. Find the radius that covers the maximum number of points

Given the coordinates of N points in a plane. Assume the 
standard 2D quadrant system for representation of points.

Problem is - Find the centre and radius of a circle 
which passes through the maximum number of points. 
The centre must be one out of the given N points.

I have no idea where this problem comes from...
*/

package alg
import scala.collection.mutable._

object CenterPoint {
	
	def distance(p1:(Double, Double), p2:(Double, Double)):Double = {
		Math.abs(p1._1 - p2._1) + Math.abs(p1._2 - p2._2)
	}

	def cntrpoint(pnts: Array[(Double,Double)]):((Double,Double),Double) = {
		// we store the maximum radius needed to cover all points for a given point in a map
		val maxRadius = Map.empty[(Double,Double), Double]

		for(o <- pnts) {
			// given an initial point o, find the distances to all other points
			// this includes o itself, which yields a zero distance...
			// since we are interested in the max, this does not matter
			val dists = for{p <- pnts} yield distance(o,p)
			// we search for the maximum distance
			// which we store in our map
			maxRadius(o) = dists.max
		}
		println(maxRadius)
		// finally, we search for the point requiring the minimum radius
		val minradius = maxRadius.minBy(e => e._2)
		println(minradius)
		minradius
	}

	def runit() {
		// our points
		val points = Array((0d,0d), (1d,0d), (0d,0.8d), (2d,2d), (0.7d,0.5d))
		cntrpoint(points)
	}
}