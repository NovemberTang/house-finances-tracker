package financetracker.converters

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class IOSpec extends AnyFlatSpec with Matchers with IO {

  "A file" should "be correctly converted into a list of people" in {
    val file = Source.fromResource("input.json")
    val (people, fractions) = readFileToPersonListAndFractions(file)
    val names = people.map(_.name)
    fractions shouldEqual None
    names shouldEqual List("Alice", "Bob", "Cat")
  }

  "A file" should "fail if incorrectly formatted" in {
    val file = Source.fromResource("wrongInput.json")
    intercept[IllegalArgumentException](readFileToPersonListAndFractions(file))
  }

  "A file containing fractions" should "correctly convert the fractions" in {
    val file = Source.fromResource("fractionInput.json")
    val (people, fractions) = readFileToPersonListAndFractions(file)
    val names = people.map(_.name)
    names shouldEqual List("Alice", "Bob", "Cat")
    fractions.get.map(_.fraction) shouldEqual List(0.5, 0.25, 0.25)
  }
}
