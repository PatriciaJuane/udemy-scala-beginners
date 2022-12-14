package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract -→ classes, fields or methods that we'll leave unimplemented!
  abstract class Animal { // abstract classes cannot be instantiated
    val creatureType: String = "wild"
    def eat: Unit // abstract method!!
  }

  // the override keyword is not mandatory for abstract members!!
  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }

  // traits -→ by default, they have abstract fields and methods, but the difference is that it can be instantiated!!
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}") // I'm eating Canine
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog) // Im a croc and Im eating canine

  // DIFFERENCES: traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be inherited by the same class -→ you can only extend one class but you can extend multiple traits
  // 3 - traits = BEHAVIOR vs. abstract class = a type of "thing"
  // (traits describe what they do)
}
