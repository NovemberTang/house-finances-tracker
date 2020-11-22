package financetracker

object Transactions {

  def updatePerson(person: Person, pence: Double): Person = {
    val newBalance = person.balance + pence
    new Person(person.name, newBalance)
  }
}
