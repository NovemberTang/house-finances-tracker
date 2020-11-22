package financetracker

import financetracker.Transactions.updatePerson
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


}
