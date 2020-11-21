package financetracker

class Person(val name: String, val balanceInFractionalPence: Double) {

  private val roundedPence = round(balanceInFractionalPence)


  private val poundString = createPoundString(roundedPence)

  override def toString: String = {
    if(balanceInFractionalPence > 0) s"$name owes £$poundString"
    else if(balanceInFractionalPence == 0) s"$name is settled up"
    else s"$name is owed £$poundString"}

  private def createPoundString(wholePennies: Int): String = {
    val poundsAndPence = math.abs(wholePennies.toDouble/100)
    val initialString = poundsAndPence.toString
    val notEnoughDecimalPlaces: Boolean = initialString.length == 3
    if (notEnoughDecimalPlaces) initialString + "0"
    else initialString
  }

  private def round(fractionalPennies: Double): Int = {
    val roundedDown = fractionalPennies.toInt
    val remainder = fractionalPennies - roundedDown
    if (remainder < 0.5) roundedDown
    else roundedDown + 1
  }
}
