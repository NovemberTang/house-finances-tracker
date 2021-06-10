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

    val lender: Person = getPerson(lenderName, people)
    val lenderIndex = getLenderIndex(lenderName, people)

    val borrowerList = removeNthElement(people, lenderIndex)
    val borrowerShare = splitEqually(amount, numberOfPeople)
    val updatedBorrowers = borrowerList.map(updatePerson(_, borrowerShare))

    val updatedLender = updatePerson(lender, -borrowerShare * numberOfBorrowers)

    updatedLender :: updatedBorrowers
  }

  private def splitUnevenly(lenderName: String, amount: Int, people: List[Person], fracList: List[Fraction]) = {

    val borrowerMap = createUnevenBorrowerMap(lenderName, amount, people, fracList)
    val updatedBorrowers: List[Person] = updateBorrowersUnevenly(borrowerMap)
    val amountBorrowed = borrowerMap.values.sum
    val lender: Person = getPerson(lenderName, people)
    val updatedLender = updatePerson(lender, -amountBorrowed)

    updatedLender :: updatedBorrowers
  }

  private def createUnevenBorrowerMap(lenderName: String, amount: Int, people: List[Person], fracList: List[Fraction]) = {
    val lenderIndex = getLenderIndex(lenderName, people)

    val borrowerList = removeNthElement(people, lenderIndex)
    val borrowerFractionList = removeNthElement(fracList, lenderIndex)
    val borrowerAmountOwedList: List[Double] = borrowerFractionList.map(_.fraction * amount)
    borrowerList.zip(borrowerAmountOwedList).toMap
  }

  private def updateBorrowersUnevenly(borrowerMap: Map[Person, Double]) = {
    borrowerMap.map(x => updatePerson(x._1, x._2)).toList

  }

  private def removeNthElement[T](list: List[T], elem: Int): List[T] = {
    val (left, right) = list.splitAt(elem)
    left ++ right.drop(1)
  }

  private def splitEqually(money: Double, participants: Int): Double = money / participants

  private def getLenderIndex(lenderName: String, people: List[Person]) = people.indexWhere(p => p.name == lenderName)

  private def getPerson(lenderName: String, people: List[Person]) = people.filter(_.name == lenderName).head


}
