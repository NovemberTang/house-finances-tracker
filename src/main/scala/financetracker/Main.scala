package financetracker

import BillSplitter.splitTheMoney
import converters.IO

import scala.io.Source

object Main extends App with IO {
  val (people, fractions) = readFileToPersonListAndFractions(Source.fromFile("input.json"))
  val updatedPeople: List[Person] = splitTheMoney("Alice", 5000, people, fractions)
  updatedPeople.foreach(println)
  writePeopleToFile(updatedPeople)
}
