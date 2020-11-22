package financetracker.converters

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class IOSpec extends AnyFlatSpec with Matchers with IO {

  "A file" should "be correctly converted into a list of people" in {
    val file = Source.fromResource("input.json")
    val names = readFileToPersonList(file).map(_.name)
    names shouldEqual List("Alice", "Bob", "Cat")
  }

  "A file" should "fail if incorrectly formatted" in {
    val file = Source.fromResource("wrongInput.json")
    intercept[IllegalArgumentException](readFileToPersonList(file))
  }

}
