// Exercise 4.1
// Implement a corecursive add function working with positive integers. The implementation of the add function
// shouldn’t use the plus (+) or minus (-) operators, but only two functions:
//    fun inc(n: Int) = n + 1
//    fun dec(n: Int) = n - 1
// Here’s the function signature:
//    fun add(a: Int, b: Int): Int
fun inc(n: Int) = n + 1
fun dec(n: Int) = n - 1
tailrec fun add(a: Int, b: Int): Int = if (b == 0) a else add(inc(a), dec(b))

// Exercise 4.2
// Write a recursive factorial value function. Remember that a value function is a function declared with the
// val keyword:
//    val factorial: (Int) -> Int =
val factorial: (Int) -> Int by lazy {
    { n: Int -> if (n == 0) 1 else n * factorial(n - 1) }
}

// Exercise 4.3
// Create a tail-recursive version of the Fibonacci function
fun fibonacci(number: Int): Int {
    tailrec fun fib(n: Int, /* fib(n-1) */ fib1: Int, /* fib(n-2) */ fib2: Int): Int =
        when {
            (n == 0) -> 1
            (n == 1) -> fib1 + fib2
            else -> fib(n - 1, fib2, fib1 + fib2)
        }
    return fib(number, 0, 1)
}