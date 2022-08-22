package lectures.part2oop

object Objects extends App {

  // OBJECTS IN SCALA ARE A DEDICATED CONCEPT.

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static") -→ LIKE JAVA. FUNCTIONALITY DOES NOT DEPEND TO AN INSTANCE OF A CLASS.
  // (go to Javaplayground and see the code)

  object Person { // Object is: type + its only instance!!! (Singleton)
    // "static"/"class" - level functionality

    val N_EYES = 2 // this is how Scala defines Class level functionality (like static in Java)

    // an object can have values or vars, and also method definitions!!!!
    // BUT OBJECTS DO NOT RECEIVE PARAMETERS, AS FUNCTIONS DO!!!!!!!!!!!!!!!!!!
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  // so, conclusion: in Scala we use OBJECTS to use Class Level Definitions
  // Objects are a SINGLETON in Scala.

  class Person(val name: String) { // we can write objects in classes with the same name!!!! to separate instance-level functionality to class/static level functionality.
    // instance-level functionality
  }
  // COMPANIONS -→ pattern where you can call class and objects with the same name, and you can use the scope you need!!!!
  // so this means that SCALA is ACTUALLY an object oriented language, even more than Java!!!!

    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE by definition!!
    val mary = new Person("Mary") // instanciate the class Person
    val john = new Person("John") // different instance of the class PErson? NO
    println(mary == john) // THEY ARE THE SAME INSTANCE OF THE CLASS!!!! ONLY ONE INSTANCE -→ SINGLETON

    val person1 = Person // example: person 1
    val person2 = Person // person 2
    println(person1 == person2)

    val bobbie = Person(mary, john) // FACTORY METHOD -→ THEY ARE CALLED "Apply" in Scala. Looks like a constructor but is a APPLY method in real loife!!
  // very useful feature.

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit      // (similar to java main method -→ Java Virtual Machine applications!!)

  // shorter way -→ at the beginning, just put --> extends App (we already do)
}
