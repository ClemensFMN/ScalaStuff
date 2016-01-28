import breeze.linalg._
import breeze.stats.distributions._
import breeze.optimize._
import breeze.plot._


object App {


    def main(args: Array[String]) {
/*
        println("Hello World")

        val x = DenseVector(3,-5)//.zeros[Double](5)
	println(x)

	val m = DenseMatrix((1,4),(-2,7)) //.zeros[Int](5,5)
	println(m)

	println(m*x)

	val poi = new Poisson(3.0)
	println(poi.sample(5))

	val f = new DiffFunction[DenseVector[Double]] {
            def calculate(x: DenseVector[Double]) = {
                (norm((x - 3d) :^ 2d,1d),(x * 2d) - 6d);
                }
            }

        val lbfgs = new LBFGS[DenseVector[Double]](maxIter=100, m=3) // m is the memory. anywhere between 3 and 7 is fine. The larger m, the more memory is needed.
	val result = lbfgs.minimize(f,DenseVector(0,0,0))
	println(result)
*/		
val f = Figure()
val p = f.subplot(0)
val x = linspace(0.0,1.0)
p += plot(x, x :^ 2.0)
p += plot(x, x :^ 3.0, '.')
p.xlabel = "x axis"
p.ylabel = "y axis"

val p2 = f.subplot(2,1,1)
val g = breeze.stats.distributions.Gaussian(0,1)
p2 += hist(g.sample(100000),100)
p2.title = "A normal distribution"


    }
} 
