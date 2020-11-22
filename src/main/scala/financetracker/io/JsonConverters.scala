package financetracker.io

import financetracker.Person
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

trait JsonConverters{
  def jsonToPerson(jsonString: String): Either[Error, Person] = decode[Person](jsonString)
  def jsonToPeople(jsonString: String): Either[Error, List[Person]] = decode[List[Person]](jsonString)

  def personToJson(person: Person): String = person.asJson.toString()
  def peopleToJson(people: List[Person]): String = people.asJson.toString()
}
