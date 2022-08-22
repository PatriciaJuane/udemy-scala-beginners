package lectures.part3fp

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  // The return type is another function
  // The super function takes 2 parameters (an Int and a Function) and returns another function.

  // map, flatMap, filter in MyList (examples of higher order functions)

  // function that applies a function n times over a value x
  // nTimes(f, n, x) -→ (This function will take 3 parameters: Function F, number N and value X)
  // perfect example of a higher order function. Takes a function as a parameter and applies it n times.
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1 // incremental function
  println(nTimes(plusOne, 10, 1)) // returns 11

  // ntb(f,n) = x => f(f(f...(x))) // we return a lambda
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne....(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x // I return the identity function
    else (x: Int) => nTimesBetter(f, n-1)(f(x))  // returns a lambda whose implementation

  val plus10 = nTimesBetter(plusOne, 100000)
  println(plus10(1)) //

  // curried functions -- very useful to define helper functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y // arrow is right associative
  val add3 = superAdder(3)  // y => 3 + y
  println(add3(10)) // 13
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // this is a valid function!
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  // TO COMPILE -→ YOU NEEED TO SPECIFY THE FUNCTION TYPE!!

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    1.  Expand MyList
        - foreach method A => Unit
          [1,2,3].foreach(x => println(x))

        - sort function ((A, A) => Int) => MyList
          [1,2,3].sort((x, y) => y - x) => [3,2,1]

        - zipWith (list, (A, A) => B) => MyList[B]
          [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4,10,18]

        - fold(start)(function) => a value
          [1,2,3].fold(0)(x + y) = 6 // returns the sum of all those numbers.

    2.  toCurry(f: (Int, Int) => Int) => (Int => Int => Int) // takes a function and transforms it into a Curry function
        fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int // opposite!

    3.  compose(f,g) => x => f(g(x)) // apply one function and then another!!
        andThen(f,g) => x => g(f(x))
   */

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  // FunctionX
  def compose[A,B,T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A,B,C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,17)) // 21

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4)) // 14
  println(ordered(4)) // 18
}
