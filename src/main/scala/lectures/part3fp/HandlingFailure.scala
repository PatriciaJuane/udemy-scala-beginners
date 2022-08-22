package lectures.part3fp

import scala.util.{Random, Try, Failure, Success}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess) // Success(3)
  println(aFailure) // Runtime Exception with that message

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure) // it throws the RuntimeException BUT THE PROGRAM DOESNT CRASH!!!

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess) // false -→ we have an exception inside!

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry) // a valid result

  // IF you design the API, and you know your code might throw an exception:
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException) // wrap the exception with Failure
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_ * 2)) // Success (6)
  println(aSuccess.flatMap(x => Success(x * 10))) // Success(30)
  println(aSuccess.filter(_ > 10)) // we turn a Success into a Failure
  // => for-comprehensions

  /*
    Exercise
   */
  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime()) // we instantiate Random
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url)) // safe
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
    // by wrapping with a Try we are containing the exceptions safely
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML) // we get an html

  // shorthand version -- WE CHAIN ALL THESE CALLS!!!!
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for-comprehension version
  /*
  for {
    connection = HttpService.getSafeConnection(host, port)
    html ← connection.getSafe("/home")
  } renderHTML(html)
   */

  // this is how it would look like with Java! not readable at all, nested try catch!!!
  /*
    try {
      connection = HttpService.getConnection(host, port)
      try {
        page = connection.get("/home")
        renderHTML(page)
      } catch (some other exception) {

      }
    } catch (exception) {

    }
   */
}
