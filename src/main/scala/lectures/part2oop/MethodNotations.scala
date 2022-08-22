package lectures.part2oop
import scala.language.postfixOps

// SYNTAX SUGAR
object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_! : String = s"$name, what the heck?!" // I put the space between the name of the method and the : for the return type so that the compile is not confused
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
    def learns(thing: String) = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception")) // true
  println(mary likes "Inception") // equivalent
  // INFIX notation = operator notation (syntactic sugar) -→ similar to natural language!!

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom) // mary is hanging out with tom
  println(mary.+(tom)) // mary is hanging out with tom -→ operator "." yields a String.
  // Scala has an strongly permissive method naming

  println(1 + 2)
  println(1.+(2)) // is the same as above

  // ALL OPERATORS ARE METHODS. !!!! even mathematical operators, like +!!!! This is why you can call your methods AS YOU WANT!
  // Akka actors have ! ?

  // THIS IS CALLED SYNTACTIC SUGAR -→ THE WAY TO WRITE METHODS IN A SIMILAR WAY AS NATURAL LANGUAGE

  // prefix notation
  val x = -1  // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary) // -→ equivalent to mary.unary_!
  println(mary.unary_!) // exactly the same!!

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive) // POSTFIX NOTATION -→ only available with the scala.language.postfixOps import - discouraged - CAN introduce ambiguities!!

  // apply
  println(mary.apply()) // Hi my name is Mary and I like inception
  println(mary()) // equivalent -→ BECAUSE THE COMPILER LOOKS FOR THE DEFINITION OF APPLY!!!! INSIDE A CLASS.
  // THIS BREAKS THE BARRIER BETWEEN OOP AND FUNCTIONAL PROGRAMMING

  /*
    1.  Overload the + operator
        mary + "the rockstar" => new person "Mary (the rockstar)"

        // def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

    2.  Add an age to the Person class
        Add a unary + operator => new person with the age + 1
        +mary => mary with the age incrementer

        // def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    3.  Add a "learns" method in the Person class => "Mary learns Scala"
        Add a learnsScala method, calls learns method with "Scala".
        Use it in postfix notation.

        // def learns(thing: String) = s"$name is learning $thing"
        // def learnsScala = this learns "Scala"

    4.  Overload the apply method
        mary.apply(2) => "Mary watched Inception 2 times"

        // def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
   */

  println((mary + "the Rockstar").apply())
  println((+mary).age)
  println(mary learnsScala) // Mary is learning Scala
  println(mary(10))

}
