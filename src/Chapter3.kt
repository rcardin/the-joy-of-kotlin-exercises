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
