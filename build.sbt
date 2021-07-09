name := """project1"""
organization := "project1"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies ++= Seq(
  jdbc,
  "org.postgresql" % "postgresql" % "42.1.1"
)

//libraryDependencies += "com.typesafe.play" %% "play-slick" % "4.0.0"
/*
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "4.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "4.0.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.3.2"
)
*/
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "project1.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "project1.binders._"
