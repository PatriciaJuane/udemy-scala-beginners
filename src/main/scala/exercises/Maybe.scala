package exercises

/**
  * Created by Daniel.
  */
abstract class Maybe[+T] { // purpose of this class → denote the possible absence of a value

  def map[B](f: T => B): Maybe[B]
  def flatMap[B](f: T => Maybe[B]): Maybe[B]
  def filter(p: T => Boolean): Maybe[T]
}

case object MaybeNot extends Maybe[Nothing] { //covariant
  def map[B](f: Nothing => B): Maybe[B] = MaybeNot
  def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot
  def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}

case class Just[+T](value: T) extends Maybe[T] {

  def map[B](f: T => B): Maybe[B] = Just(f(value))
  def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)
  def filter(p: T => Boolean): Maybe[T] =
    if (p(value)) this
    else MaybeNot

}

object MaybeTest extends App {
  val just3 = Just(3)
  println(just3) // just(3)
  println(just3.map(_ * 2)) // just(6)
  println(just3.flatMap(x => Just(x % 2 == 0))) // Just(false)
  println(just3.filter(_ % 2 == 0)) // MaybeNot

}
