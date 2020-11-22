package financetracker.io

import financetracker.Person
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class JsonConverterSpec extends AnyFlatSpec with Matchers with IO {

  private val bob = new Person("Bob", 678)
  private val alice = new Person("Alice", -9856.45)

  private val bobJson =
    """{
      |  "name" : "Bob",
      |  "balance" : 678.0
      |}""".stripMargin

  private val aliceJson =
    """{
      |  "name" : "Alice",
      |  "balance" : -9856.45
      |}""".stripMargin

  private val aliceBobJson =
    """[
      |  {
      |    "name" : "Alice",
      |    "balance" : -9856.45
      |  },
      |  {
      |    "name" : "Bob",
      |    "balance" : 678.0
      |  }
      |]""".stripMargin

  "A Person object" should "convert correctly into a Person object" in {
    personToJson(alice) shouldEqual aliceJson
    personToJson(bob)   shouldEqual bobJson
  }

  "A list of Person objects" should "correctly convert into JSON" in {
    peopleToJson(List(alice, bob)) shouldEqual aliceBobJson
  }

  "A valid person json" should "correctly convert into a Person object" in {
    val actualPerson: Person = jsonToPerson(aliceJson).getOrElse(throw new Exception)
    actualPerson.balance shouldEqual alice.balance
    actualPerson.name    shouldEqual alice.name
  }

  "A valid json containing more than one person" should "correctly convert into a List of Persons" in {
    val personList: List[Person] = jsonToPeople(aliceBobJson).getOrElse(throw new Exception)
    personList.head.balance shouldEqual alice.balance
    personList.head.name    shouldEqual alice.name

    personList.last.balance shouldEqual bob.balance
    personList.last.name    shouldEqual bob.name
  }

}
