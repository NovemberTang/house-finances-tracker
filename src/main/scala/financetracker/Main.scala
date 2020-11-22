package financetracker

import BillSplitter.splitTheMoney
import SplitMethod._
import financetracker.io.IO

import scala.io.Source

object Main extends App with IO {
  val file: Source = Source.fromFile("input.json")
  val people = readFileToPersonList(file)

  val updatedPeople: List[Person] = splitTheMoney("Alice", 5000, people, evenly)

  writePeopleToFile(updatedPeople)

}
