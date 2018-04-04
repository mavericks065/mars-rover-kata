package au.com.kata.marsrover

import org.slf4j.LoggerFactory

import scala.util.Try

case class Rover(initialPosition: Position, plateau: Plateau) {

  private val logger = LoggerFactory.getLogger(Rover.getClass)
  private var currentPosition = initialPosition

  /**
    * @return the currentPosition of the rover : x, y, heading direction
    */
  def getPosition = currentPosition

  /**
    * execute chain of commands send to the rover
    *
    * @return the position of the rover
    */
  def executeCommands: (String) => Either[RoverException, String] = commands => {

    logger.info(s"Commands to execute : $commands")
    commands.split("").foreach {
      case "L" => currentPosition = currentPosition.turnLeft
      case "R" => currentPosition = currentPosition.turnRight
      case "M" => currentPosition = currentPosition.move
      case "" => currentPosition // do nothing
      case _ => Left(RoverCommandException())
    }
    if (currentPosition.isWithinPlateau(plateau)) Right(getPosition.toString) else Left(RoverPlateauException())
  }
}

/**
  * exception handling case classes in case commands are not acceptable and rover not in plateau anymore
  */
trait RoverException extends Exception
case class RoverCommandException() extends RoverException
case class RoverPlateauException() extends RoverException
