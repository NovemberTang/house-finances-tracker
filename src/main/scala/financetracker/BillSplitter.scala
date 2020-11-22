package financetracker

import Transactions.updatePerson

object BillSplitter {

  def splitTheMoney(lenderName: String, amount: Int, people: List[Person], fractions: Option[List[Fraction]] = None): List[Person] = {
    fractions match {
      case None =>
        splitEvenly(lenderName, amount, people)
      case Some(fractionList) =>
        splitUnevenly(lenderName, amount, people, fractionList)
    }
  }

  private def splitEvenly(lenderName: String, amount: Int, people: List[Person]): List[Person] = {
    val numberOfPeople = people.length
    val numberOfBorrowers = numberOfPeople - 1

    val lender: Person = people.filter(_.name == lenderName).head
    val lenderIndex = people.indexWhere(p => p.name == lenderName)

    val borrowerList = removeNthElement(people, lenderIndex)
    val borrowerShare = splitEqually(amount, numberOfPeople)
    val updatedBorrowers = borrowerList.map(updatePerson(_, borrowerShare))

    val updatedLender = updatePerson(lender, -borrowerShare * numberOfBorrowers)

    updatedLender :: updatedBorrowers
  }

  private def splitUnevenly(lenderName: String, amount: Int, people: List[Person], fracList: List[Fraction]) = {
    val lender: Person = people.filter(_.name == lenderName).head
    val lenderIndex = people.indexWhere(p => p.name == lenderName)

    val borrowerList = removeNthElement(people, lenderIndex)
    val borrowerFractionList = removeNthElement(fracList, lenderIndex)
    val borrowerAmountOwedList = borrowerFractionList.map(_.fraction * amount)
    val updatedBorrowers: List[Person] = borrowerList
      .zip(borrowerAmountOwedList).map { tup =>
      val person = tup._1
      val moneyOwed = tup._2
      updatePerson(person, moneyOwed)
    }

    val updatedLender = updatePerson(lender, -borrowerFractionList.map(_.fraction).sum * amount)

    updatedLender :: updatedBorrowers
  }

  private def removeNthElement[T](list: List[T], elem: Int): List[T] = {
    val (left, right) = list.splitAt(elem)
    left ++ right.drop(1)
  }

  def calculateProportion(money: Double, fraction: Fraction): Double = money * fraction.fraction

  private def splitEqually(money: Double, participants: Int): Double = money / participants

}
