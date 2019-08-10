// Exercise 3.1
// Write the compose function (declared with 'fun') allowing to compose functions from Int to Int
fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = { x -> f(g(x)) }

// Exercise 3.2
// Make the compose function polymorphic by using type parameters
fun <A, B, C> composeUsingGenerics(f: (B) -> C, g: (A) -> B): (A) -> C = { x -> f(g(x)) }

// Exercise 3.3
// Write a function to add two Int values
fun add(): (Int) -> (Int) -> Int = { a -> {b -> a + b} }

// Exercise 3.4
// Write a value function to compose two functions
val composeAsValue: ((Int) -> Int) -> ((Int) -> Int) -> (Int) -> Int = {f -> { g -> {x -> f(g(x)) } } }

// Exercise 3.5
// Write a polymorphic version of the compose function
fun <A, B, C> higherCompose(): ((B) -> C) -> ((A) -> B) -> (A) -> C = {f -> { g -> {x -> f(g(x)) } } }

// Exercise 3.6
// Write the higherAndThen function that composes the functions the other way around, which means that higherCompose(f, g)
// is equivalent to higherAndThen(g, f)
fun <A, B, C> higherAndThen() =
    {f: (A) -> B ->
        {g: (B) -> C ->
            {x: A -> g(f(x))}
        }
    }

// Exercise 3.7
// Write a fun function to partially apply a curried function of two arguments to its first argument
fun <A, B, C> partialA(f: (A) -> (B) -> C, x: A): (B) -> C = f(x)

// Exercise 3.8
// Write a fun function to partially apply a curried function of two arguments to its second argument
fun <A, B, C> partialB(f: (A) -> (B) -> C, x: B): (A) -> C = { y -> f(y)(x) }

// Exercise 3.9
// Convert the following function into a curried function
// fun <A, B, C, D> func(a: A, b: B, c: C, d: D): String = "$a, $b, $c, $d"
fun <A, B, C, D> curried() =
    { a: A ->
        { b: B ->
            { c: C ->
                { d: D ->
                    "$a, $b, $c, $d"
                }
            }
        }
    }

// Exercise 3.10
// Write a function to curry a function of a (A, B) to C
fun <A, B, C> curry(f: (A, B) -> C) =
    { a: A ->
        { b: B ->
            f(a, b)
        }
    }

// Exercise 3.11
// Write a 'fun' function to swap the arguments of a curried function
fun <A, B, C> swapArgs(f: (A) -> (B) -> C): (B) -> (A) -> C =
    { b: B ->
        { a: A ->
            f(a)(b)
        }
    }