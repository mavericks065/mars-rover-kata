package au.com.kata

import java.io.PrintWriter
import java.nio.charset.Charset
import java.nio.file.{Files, Paths}
import java.util.stream

import au.com.kata.marsrover._

import scala.util.Try

object Main {

  def main(args: Array[String]): Unit = {
    val inputLines: stream.Stream[String] = getInput
    val printWriter = new PrintWriter("./roverResult")

    var i = 0
    var roverPosition: Position = null
    var rover: Rover = null
    var plateau: Plateau = null

    inputLines.forEach(line => {
      i match {
        case 0 =>
          val plateauLine = line.split(" ")
          plateau = Plateau(plateauLine(0).toInt, plateauLine(1).toInt)
        case value if value % 2 == 0 =>
          val result = rover.executeCommands(line)
          printWriter.println(result)
        case _ =>
          val roverPositionLine = line.split(" ")
          roverPosition = Position(roverPositionLine(0).toInt, roverPositionLine(1).toInt, Directions.apply(roverPositionLine(2)))
          rover = Rover(roverPosition, plateau)
      }
      i += 1
    })

    printWriter.close()
  }

  private def getInput = {
    val inputFilePath = Paths.get(getClass.getClassLoader.getResource("./input.txt").toURI)
    val inputLines = Try(Files.lines(inputFilePath, Charset.defaultCharset())).get
    inputLines
  }
}
