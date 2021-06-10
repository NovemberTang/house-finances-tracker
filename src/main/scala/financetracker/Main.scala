package financetracker

import cats.effect.IO
import cats.effect.kernel.Resource
import cats.effect.unsafe.implicits.global
import converters.{IO => FileIO}

import scala.io.{BufferedSource, Source}

object Main extends App with FileIO {

  val input: IO[BufferedSource] = IO { // it touches file system so let's wrap it in IO
    scala.io.Source.fromFile("input.json")
  }
  val ioCalculation: IO[Unit] = {
    Resource.fromAutoCloseable(input).use{
      source => IO(calculateFinancesAndWriteJson(source))
    }
  }

  ioCalculation.unsafeRunSync()
}
