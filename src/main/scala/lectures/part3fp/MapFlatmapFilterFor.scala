package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list.head) // 1
  println(list.tail) // List(2,3)

  // map
  println(list.map(_ + 1)) // List(2,3,4)
  println(list.map(_ + " is a number")) // List (1 is a number, 2 is a number, 3 is a number)

  // filter
  println(list.filter(_ % 2 == 0)) // List(2)

  // flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair)) // List(1,2,2,3,3,4)

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")

  // List("a1", "a2"... "d4")

  // "iterating"
  val combinations = numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations)

  // 2 lists would be: chars.flatMap(c => colors.map(color => "" + c + n + "-" + color))


  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations) // this is exactly the same as the combinations expression above!!
  // but this is much more readable!!!!

  for {
    n <- numbers
  } println(n)

  // syntax overload!!
  list.map { x =>
    x * 2
  }

  /*
    1.  Check if MyList supports for comprehensions?
        map(f: A => B) => MyList[B]
        filter(p: A => Boolean) => MyList[A]
        flatMap(f: A => MyList[B]) => MyList[B]
        Yes!!
    2.  Implement a small collection of at most ONE element - Maybe[+T]
        - map, flatMap, filter

        (MAYBE CLASS)
   */
}
