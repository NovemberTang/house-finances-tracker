package financetracker.converters

import financetracker.BillSplitter.splitTheMoney
import java.io.{BufferedWriter, File, FileWriter}
import financetracker.{Fraction, Person}

import scala.io.{BufferedSource, Source}

trait IO extends JsonConverters {

  def readFileToPersonListAndFractions(file: Source): (List[Person], Option[List[Fraction]]) = {
    val jsonString = file.getLines().mkString
    file.close()
    val peopleList = jsonToPeople(jsonString).getOrElse(throw new IllegalArgumentException("Input JSON improperly formatted"))
    val fractionList = jsonToFractions(jsonString).getOrElse(None)
    (peopleList, fractionList)
  }

  def calculateFinancesAndWriteJson(source: BufferedSource): Unit = {
    val (people, fractions) = readFileToPersonListAndFractions(source)
    val updatedPeople: List[Person] = splitTheMoney("Alice", 5000, people, fractions)
    writePeopleToFile(updatedPeople)
  }

  private def writePeopleToFile(people: List[Person]): Unit = {
    val output = new File("output.json")
    val bw = new BufferedWriter(new FileWriter(output))
    bw.write(peopleToJson(people))
    bw.close()
  }

}
