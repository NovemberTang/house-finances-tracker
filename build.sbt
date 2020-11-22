name := "house-finances-tracker"

scalaVersion := "2.13.4"

val circeVersion = "0.13.0"
val scalaTestVersion = "3.2.2"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test
)

coverageExcludedPackages := "financetracker/Main"