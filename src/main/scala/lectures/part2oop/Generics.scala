package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A -→ generic type A -→ reusable for any type (String, Int...) -→ GENERIC
    // we can create generic classes and traits
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Dog = Animal
     */
  }

  class MyMap[Key, Value] // -→ generic types (again)

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods too
  object MyList { // objects cannot be type parametrized
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  // (don't stress about it)
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A] // you use it in the same style as you can use cat for animals
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => we return a list of Animals

  // 2. NO = INVARIANCE -→ Whenever lists of cats and lists of animals are 2 separate things.
  // Invariance classes-→ you cannot subsitute one with another type.
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // you NEED to put animal.

  // 3. Hell, no! CONTRAVARIANCE (very counterintuitive)
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] // how can you replace a list of cats with a list of Animals?? not intuitive
  // a trainer of animals can of course train a cat!!

  // bounded types
  class Cage[A <: Animal](animal: A) // lowerbounded type sign -→ you can also use > for supertypes.
  val cage = new Cage(new Dog) // Dog is an acceptable type for the Cage!

  class Car
  // generic type needs proper bounded type
  //  val newCage = new Cage(new Car) // you wouldn't be able to run this!!!

  // BOUNDED TYPES solve a very annoying problem:
  // Example:     def add[B >: A](element: B): MyList[B] = ???
  // -→ a list of cats by adding a new dog to it, would change it to a list of Animals
  // so WE RETURN A LIST OF ANIMALS!!


  // expand MyList to be generic (exercises class)

}
