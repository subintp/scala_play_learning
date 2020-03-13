name := """twitter"""
organization := "truecaller"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "3.8.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "truecaller.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "truecaller.binders._"
