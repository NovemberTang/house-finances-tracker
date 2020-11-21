package financetracker

import BillSplitter._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BillSplitterSpec extends AnyFlatSpec with Matchers{

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
    splitEqually(99, 3) shouldEqual 33
  }

}
