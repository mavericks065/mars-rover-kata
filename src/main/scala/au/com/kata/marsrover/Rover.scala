package au.com.kata.marsrover

import org.slf4j.LoggerFactory

import scala.util.{Failure, Success, Try}

case class Rover(position: Position, plateau: Plateau) {

  private val logger = LoggerFactory.getLogger(Rover.getClass)

  /**
    * execute chain of commands send to the rover
    *
    * @return the position of the rover
    */
  def executeCommands: (String) => Try[Rover] = commands => {

    logger.info(s"Commands to execute : $commands")

    var currentPosition = Try(position)

    commands.split("").foreach { cmd =>
      (cmd, currentPosition) match {
        case ("L", Success(p)) => currentPosition = Try(currentPosition.get.turnLeft)
        case ("R", Success(p)) => currentPosition = Try(currentPosition.get.turnRight)
        case ("M", Success(p)) => currentPosition = Try(currentPosition.get.move)
        case ("", Success(p)) => Try(currentPosition.get) // do nothing
        case (_, Failure(p)) => currentPosition = Failure(RoverCommandException())
        case (_, _) => currentPosition = Failure(RoverCommandException())
      }
    }
    if (currentPosition.isSuccess && currentPosition.get.isWithinPlateau(plateau)) Success(Rover(currentPosition.get, plateau))
    else if (currentPosition.isFailure) Failure(currentPosition.failed.get)
    else Failure(RoverPlateauException())
  }
}

/**
  * exception handling case classes in case commands are not acceptable and rover not in plateau anymore
  */
trait RoverException extends Exception

case class RoverCommandException() extends RoverException

case class RoverPlateauException() extends RoverException
