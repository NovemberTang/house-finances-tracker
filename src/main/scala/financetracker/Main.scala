package financetracker

import BillSplitter.splitTheMoney
import SplitMethod._
import converters.IO

import scala.io.Source

object Main extends App with IO {
  val people = readFileToPersonList(Source.fromFile("input.json"))
  val updatedPeople: List[Person] = splitTheMoney("Alice", 5000, people, Evenly)
  writePeopleToFile(updatedPeople)
}
