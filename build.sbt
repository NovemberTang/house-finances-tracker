name := "house-finances-tracker"

scalaVersion := "2.13.4"

val circeVersion = "0.13.0"
val scalaTestVersion = "3.2.2"
val catsEffectVersion = "3.1.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "org.typelevel" %% "cats-effect" % catsEffectVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test
)
