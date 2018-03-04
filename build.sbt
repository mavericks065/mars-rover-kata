
name := "mars-rover"

version := "0.1"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.8.6" % "test")
libraryDependencies += "org.specs2" % "specs2-junit_2.12" % "3.8.6" % "test"
libraryDependencies += "org.specs2" % "specs2-mock_2.12" % "3.8.6" % "test"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.25" % "test"

coverageEnabled in Test := true
coverageMinimum := 60
coverageFailOnMinimum := true
parallelExecution in Test := false