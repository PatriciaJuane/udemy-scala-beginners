package lectures.part2oop

import scala.annotation.tailrec

object OOBasics extends App {

  // constructor
  class Person(name: String, val age: Int = 0) {
    // body
    val x = 2

    println(1 + 3) // it will be executed → be careful, it can have side effects!

    // method
    def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

    // overloading
    def greet(): Unit = println(s"Hi, I am $name")

    // multiple constructors
    // Multiple / overloading constructors
    def this(name: String) = this(name, 0) // auxiliary constructor
    def this() = this("John Doe")
  }

  val person = new Person("John", 26)
  println(person.x)
  person.greet("Daniel") // John says: Hi, Daniel!
  person.greet() // Hi, I am John (this is implied)

  // CONCEPT OF OVERLOADING: DEFINING METHODS WITH THE SAME NAME BUT DIFFERENT SIGNATURES

  // PART 2. EXERCISES

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(imposter)) // will return false (equality in OOP)

  val counter = new Counter
  counter.inc.print // it prints 1 (and the inc method will print incrementing)
  counter.inc.inc.inc.print // this prints 3
  counter.inc(10000).print //
}

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
  - authorAge
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel
 */
class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = firstName + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge = year - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */
class Counter(val count: Int = 0) { // 0 as a default value
  def inc = {
    println("incrementing")
    new Counter(count + 1)  // immutability!!! very important in Scala
  }

  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this // because if doesnt need any incrementing
    else inc.inc(n-1) //
  }

  def dec(n: Int): Counter =
    if (n <= 0) this
    else dec.dec(n-1)

  def print = println(count)
}

// class parameters are NOT FIELDS
