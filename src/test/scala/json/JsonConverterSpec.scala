package json

import financetracker.Person
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class JsonConverterSpec extends AnyFlatSpec with Matchers with JsonConverters {

  val alice = new Person("Bob", 678)
  val bob = new Person("Alice", -9856.45)

  "Valid person JSON" should "convert correctly into a Person object" in {
    val bobJson =
      """{
        |  "name" : "Bob",
        |  "balance" : 678.0
        |}""".stripMargin

    val aliceJson =
      """{
        |  "name" : "Alice",
        |  "balance" : -9856.45
        |}""".stripMargin


    personToJson(alice) shouldEqual bobJson
    personToJson(bob) shouldEqual aliceJson

  }

  "A list of people" should "correctly convert into JSON" in{
    val people = List(alice, bob)
    val expectedResult: String ="""[
                                   |  {
                                   |    "name" : "Bob",
                                   |    "balance" : 678.0
                                   |  },
                                   |  {
                                   |    "name" : "Alice",
                                   |    "balance" : -9856.45
                                   |  }
                                   |]""".stripMargin

    peopleToJson(people) shouldEqual expectedResult

  }

}
