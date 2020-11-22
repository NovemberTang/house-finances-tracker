package financetracker

import BillSplitter._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BillSplitterSpec extends AnyFlatSpec with Matchers{

  private val bob = new Person("Bob", 0)
  private val alice = new Person("Alice", 0)
  private val cat = new Person("Cat", 0)

  "A split by proportion" should "calculate correctly" in {
    calculateProportion(200, new Fraction(0.5)) shouldEqual 100
    calculateProportion(200, new Fraction(3.0/8)) shouldEqual 75
  }

  "Splitting equally" should "work correctly" in {
    val actualResult = splitTheMoney("Alice", 5000, List(alice, bob, cat))
    actualResult.map(_.toString) shouldEqual List("Alice is owed £33.33", "Bob owes £16.67", "Cat owes £16.67")
  }

}
