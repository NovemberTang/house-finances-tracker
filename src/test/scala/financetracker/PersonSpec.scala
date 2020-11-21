package financetracker

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PersonSpec extends AnyFlatSpec with Matchers{

  "A Person" should "be able to print out the correct currency string" in {
    val owingPerson = new Person("Alice", 8765.43)
    val owedPerson = new Person("Bob", -100.43)
    owingPerson.toString shouldEqual("Alice owes £87.65")
    owedPerson.toString shouldEqual("Bob is owed £1.00")
  }

}
