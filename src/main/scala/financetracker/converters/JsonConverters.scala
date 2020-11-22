package financetracker.converters

import financetracker.SplitMethod.SplitMethod
import financetracker.{Person, SplitMethod, Updater}
import io.circe.Decoder.decodeEnumeration
import io.circe.Encoder.encodeEnumeration
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

trait JsonConverters{

  implicit val splitMethodDecoder: Decoder[SplitMethod] = decodeEnumeration(SplitMethod)
  implicit val splitMethodEncoder: Encoder[SplitMethod] = encodeEnumeration(SplitMethod)

  def jsonToPerson(jsonString: String): Either[Error, Person] = decode[Person](jsonString)
  def jsonToPeople(jsonString: String): Either[Error, List[Person]] = decode[List[Person]](jsonString)

  def personToJson(person: Person): String = person.asJson.toString()
  def peopleToJson(people: List[Person]): String = people.asJson.toString()

  def updaterToString(updater: Updater): String = updater.asJson.toString()
  def updatersToString(updaters: List[Updater]): String = updaters.asJson.toString()
}
