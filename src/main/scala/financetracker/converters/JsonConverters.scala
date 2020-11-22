package financetracker.converters

import financetracker.{Fraction, Person}
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

trait JsonConverters{
  def jsonToPerson(jsonString: String): Either[Error, Person] = decode[Person](jsonString)
  def jsonToPeople(jsonString: String): Either[Error, List[Person]] = decode[List[Person]](jsonString)

  def jsonToFractions(jsonString: String): Either[Error, Option[List[Fraction]]] = decode[Option[List[Fraction]]](jsonString)

  def personToJson(person: Person): String = person.asJson.toString()
  def peopleToJson(people: List[Person]): String = people.asJson.toString()
}
