package financetracker

import BillSplitter._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BillSplitterSpec extends AnyFlatSpec with Matchers{

  private val bob = new Person("Bob", 0)
  private val alice = new Person("Alice", 0)
  private val cat = new Person("Cat", 0)

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

  "A percentage split" should "correctly calculate the share of money owed" in {
    val result = calculatePercentageOfMoney(1500, 25)
    result shouldEqual(375)
  }
  it should "cut decimals correctly" in {
    val result = calculatePercentageOfMoney(150, 25)
    result shouldEqual(37.5)
  }

  "A split by proportion" should "calculate correctly" in {
    calculateProportion(200, 4, 8) shouldEqual 100
    calculateProportion(200, 3,8) shouldEqual 75
  }

  "Splitting equally" should "work correctly" in {
    val actualResult = splitTheMoney("Alice", 5000, List(alice, bob, cat), SplitMethod.evenly)
    actualResult.map(_.toString) shouldEqual List("Alice is owed £33.33", "Bob owes £16.67", "Cat owes £16.67")
  }

}
