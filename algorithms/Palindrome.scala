object Palindrome {
	

	def reverse_int_to_vec_int(x: Int, lst: List[Int]): List[Int] = {
		x match {
			case x if x < 10 => x :: lst
			case _ 			 =>	val new_val = (x - x % 10) / 10
								val last_digit = x % 10
								reverse_int_to_vec_int(new_val, last_digit :: lst)
		}
	}

	def reverse_int(x: Int): Int = {
		reverse_int_to_vec_int(x, List()).reverse.foldLeft(0)((x, acc) => acc + 10*x)
	}

	def reverse_int_v2(x: Int): Int = {
		var temp = x
		var lst = List[Int]()
		var last_digit = 0
		while(temp > 10) {
			last_digit = temp % 10
			temp = (temp - temp % 10) / 10
			lst = last_digit :: lst
		}
		lst = temp :: lst
		lst.reverse.foldLeft(0)((x, acc) => acc + 10*x)
	}

	def isPalindrome(x: Int) = {
		x == reverse_int(x)
	}

	def makePalindrome(x: Int): (Int, Int) = {
		var temp = x
		var runs = 0
		while(!isPalindrome(temp)) {
			temp = temp + reverse_int(temp)
			runs = runs + 1
		}
		(temp, runs)
	}

	def runit() = {
		//println(reverse_int(4))
		
		println(reverse_int(1256))
		println(reverse_int_v2(1256))

		//println(isPalindrome(345))
		//println(isPalindrome(343))

		//println(makePalindrome(748))
	}
}