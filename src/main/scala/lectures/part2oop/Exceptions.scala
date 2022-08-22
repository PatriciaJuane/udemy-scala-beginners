package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //  println(x.length)
  //  this ^^ will crash with a NPE (no pointer exception)

  // 1. throwing exceptions
  // Like everything else in Scala, throwing an exception is an expression!!!
  //  val aWeirdValue: String = throw new NullPointerException // also crashes
  // if you hover over aWeirdValue, it says is Nothing!
  // exceptions are actually instances of classes, nothing more!!!

  // throwable classes extend the Throwable class!!!
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try { // try, catch & finally
    // code that might throw
    getInt(false)
  } catch {
    case e: RuntimeException => 43 // java.lang.RuntimeException --? they come from JVM Java language!! They are Java specific
      // make sure to catch all the possible exceptions, otherwise it will crash your program!!!
  } finally { // finally is also a keyword!

    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  println(potentialFail) // you see the value 43 → but if you call getInt(false), you see teh value 42

  // finally DOES NOT influence the return type of the expression!!! IS ONLY FOR SIDE EFFECTS!!!!

  // 3. how to define your own exceptions
  class MyException extends Exception // exceptions: are instances of special classes that derive from Exception or Error (usually Exception)
  val exception = new MyException

  // they are classes, so they can have fields, methods, etc!!! But you rarely need them!!

  //  throw exception

  /*
    1.  Crash your program with an OutOfMemoryError
    2.  Crash with SOError (stack overflow error)
    3.  PocketCalculator
        - add(x,y)
        - subtract(x,y)
        - multiply(x,y)
        - divide(x,y)

        Throw
          - OverflowException if add(x,y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
          - MathCalculationException for division by 0
   */
  //  OOM
  //  val array = Array.ofDim(Int.MaxValue) -→ Out of Memory Error (Allocate an Array with as many elements as I want)

  //  SO (Stack Overflow)
  //  def infinite: Int = 1 + infinite
  //  val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException // divided by 0
      else x / y
    }

  }

  println(PocketCalculator.add(Int.MaxValue, 10)) // Overflow Exception
  println(PocketCalculator.divide(2, 0)) // MathCalculationException
}
