import breeze.linalg._
import breeze.stats.distributions._
import breeze.optimize._


object App {


    def main(args: Array[String]) {

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

    }
} 
