package financetracker.converters

import java.io.{BufferedWriter, File, FileWriter}
import financetracker.Person
import scala.io.Source

trait IO extends JsonConverters{

  def readFileToPersonList(file: Source): List[Person] = {
    val jsonString = file.getLines().mkString
    file.close()
    jsonToPeople(jsonString).getOrElse(throw new IllegalArgumentException("Input JSON improperly formatted"))
  }

  def writePeopleToFile(people: List[Person]): Unit = {
    val output = new File("output.json")
    val bw = new BufferedWriter(new FileWriter(output))
    bw.write(peopleToJson(people))
    bw.close()
  }

}
