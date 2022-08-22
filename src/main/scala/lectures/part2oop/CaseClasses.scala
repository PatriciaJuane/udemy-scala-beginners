package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int) // case keyword -→ characteristics:

  // 1. class parameters are fields
  val jim = new Person("Jim", 34) // all parameters are fields!
  println(jim.name)

  // 2. sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim) // toString representation in case classes

  // 3. equals and hashCode implemented OOTB (out of the box) -→ makes case classes specially important to use in Collections!!!
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // true (without the case, this would be FALSE)

  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3) // person age 45!!

  // 5. CCs have companion objects -→ automatically created!!
  val thePerson = Person
  val mary = Person("Mary", 23) // Factory methods!! I've just constructed a method! "apply" method!!

  // 6. CCs are serializable -→ specially useful for distributed systems!! Akka framework
  // Akka

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING!!! One of the most poweful scala features!

  case object UnitedKingdom { // Case applies also to OBJECTS! Case objects
    def name: String = "The UK of GB and NI"
  } // Case objects have the same properties as case classes, except they dont have companion objects (they are their own companion objects)

  /*
    Expand MyList - use case classes and case objects
   */
}
