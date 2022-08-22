package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA) -- syntactic sugar would be val doubler = (x: Int) ⇒ x * 2
  val doubler: Int => Int = (x: Int) => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // prints the function itself (this is the style to call METHODS! not lambdas, be careful!
  println(justDoSomething()) // actual call -→ prints 3

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  // compile has to be 100% sure of the function definitions. We cannot put: val niceAdder = _ + _ without a type definition.

  /*
    1.  MyList: replace all FunctionX calls with lambdas
    2.  Rewrite the "special" adder as an anonymous function
   */

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4)) // prints number 7
}
