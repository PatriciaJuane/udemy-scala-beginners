package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4) // apply method
  println(aSequence) // List(1,2,3,4) -→ but the type is Sequence of Int!
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6)) // List (1,2,3,4,7,5,6)
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 until 10 // 1 to 10 prints everything, 1 until 10 is non right inclusive
  aRange.foreach(println) // prints all the numbers, 1 to 10

  (1 to 10).foreach(x => println("Hello"))

  // lists
  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89 // +: is append, :+ is prepend
  // :: is syntactic sugar -→ 42 :: aList -→ 42,1,2,3
  println(prepended)

  val apples5 = List.fill(5)("apple") // list of 5 apples.
  println(apples5)
  println(aList.mkString("-|-")) // puts that in between elements.

  // arrays -→ equivalent to simple Java arrays. An array is a sequence!
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println) // prints default values -→ for String, they are initialized to NULL

  // mutation
  numbers(2) = 0  // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" ")) // 1 2 0 4

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers  // implicit conversion
  println(numbersSeq)    // WrappedArray (1,2,0,4)

  // vectors -→ default implementation for immutable sequences
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random  // random number generator in Scala: Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())// operation -→ at a random index, put a random value
      System.nanoTime() - currentTime // we measure the time it takes
    }

    times.sum * 1.0 / maxRuns // we return the average time
  }

  // we initialize a list an a vector to compare which one is faster running this method.

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // Advantage: A list keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList)) // TAKES MUCH LONGER!!

  // Advantage: depth of the tree is small
  // needs to replace an entire 32-element chunk -→ traverses the whole tree.
  println(getWriteTime(numbersVector)) // takes much less!!

}
