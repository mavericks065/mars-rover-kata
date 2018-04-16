package au.com.kata.marsrover

import org.slf4j.LoggerFactory

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

case class Rover(position: Position, plateau: Plateau) {

  private val logger = LoggerFactory.getLogger(Rover.getClass)

  /**
    * execute chain of commands send to the rover
    *
    * @return the position of the rover
    */
  def executeCommands: (String) => Try[Rover] = commands => {

    @tailrec
    def loopOnCommands(commands: List[String], position: Try[Position]): Try[Position] = {
      if (position.isFailure) position
      else if (commands.tail.isEmpty) position
      else {
        val currentPosition = (commands.head, position) match {
          case ("L", Success(p)) => Try(position.get.turnLeft)
          case ("R", Success(p)) => Try(position.get.turnRight)
          case ("M", Success(p)) => Try(position.get.move)
          case ("", Success(p)) => Success(p) // do nothing
          case (_, _) => Failure(RoverCommandException())
        }
        loopOnCommands(commands.tail, currentPosition)
      }
    }

    logger.info(s"Commands to execute : $commands")

    val currentPosition = loopOnCommands(commands.split(" ").toList, Try(position))

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
