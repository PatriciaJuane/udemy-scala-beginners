package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal { // superclass of cat
    val creatureType = "wild"
    def eat = println("nomnom") // private would mark this method only accessible within this class ONLY.
  }

  class Cat extends Animal { // subclass of animal
    def crunch = {
      eat // this calls the eat inherited from Animal. -→ protected modifier allows to do this (but is not accessible OUTSIDE the subclass)
      println("crunch crunch")
    }
  }

  // access modifiers: private, protected and public

  val cat = new Cat
  cat.crunch


  // constructors
  class Person(name: String, age: Int) { // defining a class like this also defines its constructor.
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)
  // scala compiler forces you to guarantee theres a parent constructor that matches if you want to extend a class with parameters.

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override val creatureType = "domestic" // can override in the body or directly in the constructor arguments
    override def eat = {
      super.eat // allows to extend the "nomnom" --> we would see nomnom and crunch, crunch
      println("crunch, crunch")
    }
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)


  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING vs overLOADING

  // super

  // preventing overrides
  // 1 - use final on member -→ we cannot override the method!!
  // 2 - use final on the entire class -→ prevents the entire class to be extended!
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files -→ 'sealed' keyword!!!
}
