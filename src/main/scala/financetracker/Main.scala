package financetracker

import java.io.{BufferedWriter, File, FileWriter}
import financetracker.json.JsonConverters
import scala.io.Source
import BillSplitter.splitTheMoney
import SplitMethod._

object Main extends App with JsonConverters {

  val input = Source.fromFile("input.json")
  val jsonString = input.getLines().mkString
  val people = jsonToPeople(jsonString).getOrElse(List(new Person("", 0)))
  input.close()

  val updatedPurchase: List[Person] = splitTheMoney("Alice", 5000, people, evenly)

  val output = new File("output.json")
  val bw = new BufferedWriter(new FileWriter(output))
  bw.write(peopleToJson(updatedPurchase))
  bw.close()

}
