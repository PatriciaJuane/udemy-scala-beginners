package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // problem: we come from an object oriented programming world.

  val doubler = new MyFunction[Int, Int] { // instance of the MyFunction class!
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) // we can call it as if it were a function!!! (doubler)

  // interesting thing: scala covers this function types out of the box

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4) // 7 in the console

  // FUNCTION TRAITS: can have up to 22 parameters!!

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] { // this is a proper function!!! syntactic sugar
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R -→ syntactic sugar!!!!!

  // ALL SCALA FUNCTIONS ARE OBJECTS !!!! → or instances of classes deriving from Function1, Function2 etc
  // JVM was designed with OO in mind!!!

  /*
    1.  a function which takes 2 strings and concatenates them
    2.  transform the MyPredicate and MyTransformer into function types
    3.  define a function which takes an int and returns another function which takes an int and returns an int
        - what's the type of this function
        - how to do it
   */

  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }
  println(concatenator("Hello ", "Scala"))

  // Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4)) // return 7
  println(superAdder(3)(4)) // curried function -→ returns 7. CURRIED FUNCTION! they can be called with multiple parameter list!!!!

}

trait MyFunction[A, B] {
  def apply(element: A): B
}
