package financetracker

import scala.annotation.tailrec

object Transactions {

  def updatePerson(person: Person, pence: Double): Person = {
    val newBalance = person.balanceInFractionalPence + pence
    new Person(person.name, newBalance)
  }

  @tailrec
  def updatePeople(people: List[Person], balances: List[Double], newPeople: List[Person] = List.empty): List[Person] = {
    people match {
      case Nil => newPeople
      case _ =>
        val updatedPerson = updatePerson(people.head, balances.head)
        updatePeople(people.tail, balances.tail, updatedPerson :: newPeople)
    }
  }

}
