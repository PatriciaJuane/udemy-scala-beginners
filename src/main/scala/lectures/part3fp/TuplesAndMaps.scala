package lectures.part3fp

import scala.annotation.tailrec

/**
  * Created by Daniel.
  */
object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple = (2, "hello, Scala")  // Tuple2[Int, String] = (Int, String)
  // basically, tuples group things together. Parenthesis (Int, String) are syntactic sugar for Tuple2

  println(aTuple._1)  // 2
  println(aTuple.copy(_2 = "goodbye Java")) // (2, "goodbye Java")
  println(aTuple.swap)  // ("hello, Scala", 2)

  // tuples are super easy and handy in practice!

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789, ("JIM", 9000)).withDefaultValue(-1)
  // a -> b is sugar for (a, b)!!!! is syntactic sugar for the tuple (a,b) !!
  println(phonebook)

  // map ops -→ basic operations!
  println(phonebook.contains("Jim"))
  println(phonebook("Mary")) // Returns -1 (default).
  // Apply method for Map!! returns the value associated to this key

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing // + is the add method
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  //  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))
  // I transform this map by lowercasing the keys.

  // filterKeys
  //  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)
  // mapValues
  println(phonebook.view.mapValues(number => "0245-" + number).toMap)

  // conversions to other collections
  println(phonebook.toList)
  //  println(List(("Daniel", 555)).toMap) // prints Map(Daniel -> 555)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
  // will print  Map(J → List(James, Jim),  A → List(Angela), ...)

  println(names.groupBy(name => name.charAt(0) == 'J'))

  /*
    1.  Question: What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900

        !!! careful with mapping keys!! they OVERLAP!!! you might lose some data if you do this.

    2.  Overly simplified social network based on maps
      Each person will have a name (Person = String)
        Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend

        - number of friends of a person
        - person with most friends
        - how many people have NO friends
        - if there is a social connection between two people (direct or not)
   */

  // Our entire social network will be a map from String to List / Set of String.
  // Set guarantees it only contains unique elements in the network.

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set()) // add a new pairing to a person with an empty set

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a) // retrieve friends of person A
    val friendsB = network(b) // retrieve friends of person B

    network + (a -> (friendsA + b)) + (b -> (friendsB + a)) // adds these new pairings to the network.
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    // we need to remove the friendships first, before removing the person itself.
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network) // 2 associations, bob with an empy set, and Mary with an empty set
  println(friend(network, "Bob", "Mary")) // we make them friends. Bob → set(Mary), Mary → Set(Bob)
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim,Bob,Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet)) // Bob (person with most friends)

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet)) // how many people doesnt have friends. (no one in this network)

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim")) // true
  println(socialConnection(network, "Mary", "Bob")) // false

}
