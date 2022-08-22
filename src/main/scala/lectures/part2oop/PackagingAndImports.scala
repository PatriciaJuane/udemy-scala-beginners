package lectures.part2oop

import playground.{PrinceCharming, Cinderella => Princess}
// this way, Im calling Cinderella a Princess (useful if you need to import multiple classes with the same name)

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package (for external packages)
  val princess = new Princess  // or, instead: playground.Cinderella = fully qualified class name

  // packages are in hierarchy
  // matching folder structure.

  // package object -→ can only be one per package, with the same name , and in the package called: package.scala
  sayHello // i can call this just like that, because the package object makes it visible in the entire package.
  // (not commonly used, but just to let you know they exist)
  println(SPEED_OF_LIGHT)

  // imports -→ you could put playground._ for a generic import!! like * in java
  val prince = new PrinceCharming

  // 1. use FQ names (you could use java.sql.Date) option 1
  val date = new Date
  val sqlDate = new SqlDate(2018, 5, 4) // Option number 2: ALIASING -→ SqlDate
  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ??? expression

}
