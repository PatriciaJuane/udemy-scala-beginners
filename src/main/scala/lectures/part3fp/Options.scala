package lectures.part3fp

import java.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption) // Some(4)

  // Options were invented to deal with unsafe APIs
  def unsafeMethod(): String = null
  //  val result = Some(null) // WRONG -→ you might be getting null inside Some!! Never do this!
  val result = Option(unsafeMethod()) // Some or None -- depending on wether this value is null or not!
  println(result) // well get NONE

  // chained methods
  def backupMethod(): String = "A valid result"
  // this is HOW you work with unsafe APIs!!
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // If you are on the other end of programming and you DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get)  // UNSAFE - this will break - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2)) // Some(8)
  println(myFirstOption.filter(x => x > 10)) // None
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  /*
    Exercise.
   */
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection) // simulate a connection given a host and a port
      else None
  }
  // BUT we dont have the certainty that the host and the port have values in real life! someone else might have wrote it

  // try to establish a connection, if so - print the connect method
  val host = config.get("host") // Option(String)
  val port = config.get("port") // Option(String)
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)

    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else print (Some(connectionstatus.get))
  println(connectionStatus) // None or Some(connected)
  /*
    if (status != null)
      println(status)
   */
  connectionStatus.foreach(println)

  // chained calls -→ chained solution to the problem!! this is most likely how you'll do this in practice!!!
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  // much more readable!!!
  val forConnectionStatus = for { // assuming host, port and connection are not null → connect. Otherwise, return None!!
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)
}
