package financetracker

import SplitMethod.SplitMethod
import Transactions.updatePerson

object BillSplitter {

  def splitTheMoney(lenderName: String, amount: Int, people: List[Person], splitMethod: SplitMethod): List[Person] = {
    val numberOfPeople    = people.length
    val numberOfBorrowers = numberOfPeople - 1
    val lender: Person    = people.filter(_.name == lenderName).head
    val borrowerList      = people.filterNot(_.name == lenderName)

    splitMethod match {
      case SplitMethod.evenly =>
        val borrowerShare = splitEqually(amount, numberOfPeople)
        val updatedLender = updatePerson(lender, -borrowerShare * numberOfBorrowers)
        updatedLender :: borrowerList.map(updatePerson(_, borrowerShare))
      case SplitMethod.byPercentage => ???
      case SplitMethod.byProportion => ???
    }
  }

  def calculatePercentageOfMoney(money: Double, percentage: Double):        Double = money * calcFraction(percentage, 100)
  def calculateProportion(money: Double, share: Int, totalProportion: Int): Double = money * calcFraction(share, totalProportion)
  private def splitEqually(money: Double, participants: Int):               Double = money * calcFraction(1, participants)

  private def calcFraction(top: Double, bottom: Double): Double = top / bottom
}
