package financetracker

import financetracker.Transactions.{updatePeople, updatePerson}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TransactionsSpec extends AnyFlatSpec with Matchers{
  val alice = new Person("Alice", 0)
  val bob = new Person("Bob", 0)
  "A Person's balance" should "update with the correct amount of money" in{

    val updatedAlice = updatePerson(alice, 99)
    updatedAlice.name shouldEqual "Alice"
    updatedAlice.balance shouldEqual 99
  }

  "The balances of a list of people" should "update correctly" in {
    val people = List(alice, bob)
    val updatedPeople = updatePeople(people, List(7,8))
    updatedPeople.head.name shouldEqual "Bob"
    updatedPeople.head.balance shouldEqual 8

    updatedPeople.last.name shouldEqual "Alice"
    updatedPeople.last.balance shouldEqual 7

  }

}
