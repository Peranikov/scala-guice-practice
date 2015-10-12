name := "guice-practice"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "com.google.inject" % "guice" % "4.0"
libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.6.4" % "test")