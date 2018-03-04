package au.com.kata

import java.io.PrintWriter
import java.nio.charset.Charset
import java.nio.file.{Files, Paths}
import java.util.stream

import au.com.kata.marsrover.{Direction, Plateau, Position, Rover}

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
      if (i > 2) i = 1
      if (i == 0) {
        val plateauLine = line.split(" ")
        plateau = Plateau(plateauLine(0).toInt, plateauLine(1).toInt)
      }
      if (i == 1) {
        val roverPositionLine = line.split(" ")
        roverPosition = Position(roverPositionLine(0).toInt, roverPositionLine(1).toInt, Direction(roverPositionLine(2)))
        rover = Rover(roverPosition, plateau)
      }
      if (i == 2) {
        val result = rover.executeCommands(line)
        printWriter.println(result)
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
