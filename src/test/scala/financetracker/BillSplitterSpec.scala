package financetracker

import BillSplitter._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BillSplitterSpec extends AnyFlatSpec with Matchers{

  private val bob = new Person("Bob", 0)
  private val alice = new Person("Alice", 0)
  private val cat = new Person("Cat", 0)

  "Splitting equally" should "work correctly" in {
    val actualResult = splitTheMoney("Alice", 5000, List(alice, bob, cat))
    actualResult.map(_.toString) shouldEqual List("Alice is owed £33.33", "Bob owes £16.67", "Cat owes £16.67")
  }

  "An uneven split" should "work correctly" in {
    val quarter = new Fraction(0.25)
    val half = new Fraction(0.5)
    val actualResult = splitTheMoney("Alice", 100, List(alice, bob, cat), Some(List(half, quarter, quarter)))
    actualResult.map(_.toString) shouldEqual List("Alice is owed £0.50", "Bob owes £0.25", "Cat owes £0.25")

  }

}
