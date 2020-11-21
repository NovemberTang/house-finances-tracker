package financetracker

object BillSplitter {
  private def calcFraction(top: Double, bottom: Double): Double = top/bottom

  def calculatePercentageOfMoney(money: Double, percentage: Double): Double = money * calcFraction(percentage, 100)

  def calculateProportion(money: Double, share: Int, totalProportion:Int): Double = money* calcFraction(share, totalProportion)

  def splitEqually(money: Double, participants: Int): Double = money * calcFraction(1, participants)
}
