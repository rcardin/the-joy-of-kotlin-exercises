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

// Exercise 4.4
// Write a tail-recursive version of the makeString function
val <T> List<T>.tail: List<T>
    get() = drop(1)
val <T> List<T>.head: T
    get() = first()
fun <T> makeString(list: List<T>, delim: String): String {
    tailrec fun makeString_(list: List<T>, acc: String): String =
        when {
            list.isEmpty() -> acc
            acc.isEmpty() -> makeString_(list.tail, "${list.head}")
            else -> makeString_(list.tail, "$acc$delim${list.head}")
        }
    return makeString_(list, "")
}

// Exercise 4.5
// Create a generic version of your tail-recursive function that can be used for sum, string, and makeString. Call
// this function foldLeft, then write sum, string, and makeString in terms of this new function.
fun <T, U> foldLeft(list: List<T>, z: U, f: (U, T) -> U): U {
    tailrec fun foldLeft_(list: List<T>, acc: U): U =
        if (list.isEmpty())
            acc
        else
            foldLeft_(list.tail, f(acc, list.head))
    return foldLeft_(list, z)
}
fun <T> makeStringWithFoldLeft(list: List<T>, delim: String): String {
    return foldLeft(list, "",
        {acc, curr ->
            if (acc.isEmpty())
                "$curr"
            else
                "$acc$delim$curr"
        }
    )
}

// Exercise 4.6
// Write this abstracted function and call it foldRight. Then write the string function in terms of foldRight
fun <T, U> foldRight(list: List<T>, z: U, f: (T, U) -> U): U =
    if (list.isEmpty())
        z
    else
        f(list.head, foldRight(list.tail, z, f))

// Exercise 4.7
// Define a reverse function using a fold.
fun <T> prepend(list: List<T>, elem: T): List<T> = listOf(elem) + list
fun <T> reverse(list: List<T>): List<T> = foldLeft(list, emptyList(), ::prepend)

// Exercise 4.8
// Define the reverse function using only the append version of + without resorting to concatenation
fun <T> prependWithoutListOf(list: List<T>, elem: T): List<T> = foldLeft(list, listOf(elem)) {acc, elm -> acc + elm}
fun <T> reverseWithoutListOf(list: List<T>) = foldLeft(list, emptyList(), ::prependWithoutListOf)

// Exercise 4.9
// Write a loop-based implementation of a function that produces a list using a starting value, a limit, and the
// function x -> x + 1. You’ll call this function range, and it’ll have the following signature:
//    fun range(start: Int, end: Int): List<Int>
fun range(start: Int, end: Int): List<Int> {
    val result: MutableList<Int> = mutableListOf()
    var index = start
    while (index < end) {
        result.add(index)
        index++
    }
    return result
}

// Exercise 4.10
// Write a generic version of a range-like function that works for any type and any condition. As the notion of
// range works only for numbers, let’s call this function unfold and give it the following signature:
//    fun <T> unfold(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T>
fun <T> unfold(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T> {
    val result: MutableList<T> = mutableListOf()
    var elem = seed
    while (p(elem)) {
        result.add(elem)
        elem = f(elem)
    }
    return result
}

// Exercise 4.11
// Implement the range function in terms of unfold
fun rangeUsingUnfold(start: Int, end: Int): List<Int> =
    unfold(start, { it + 1 }, { it < end })