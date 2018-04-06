package au.com.kata.marsrover

import org.specs2.mutable.Specification

class RoverTest extends Specification {

  "A Rover" can {
    "be just instantiated without commands send and" should {
      "belong to a plateau" in {
        // GIVEN
        val expectedPlateau = Plateau(5,5)
        val initialPosition = Position(1,1, Directions.apply("N"))

        // WHEN
        val rover = Rover(initialPosition, expectedPlateau)

        // THEN
        rover.plateau.x must_== expectedPlateau.x
        rover.plateau.y must_== expectedPlateau.y
      }
      "have the same position than the instantiated positions given" in {
        // GIVEN
        val plateau = Plateau(5,5)
        val expectedPosition = Position(1,1, Directions.apply("N"))

        // WHEN
        val rover = Rover(expectedPosition, plateau)

        // THEN
        rover.position must_== expectedPosition
      }
    }
  }

  "Given a Rover with a position heading NORTH" should {

    "move vertically for the command M" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("N"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("M").get

      // THEN
      result.position.toString must_== "1 2 N"
    }
    "turn left for the command L and head WEST" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("N"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("L").get

      // THEN
      result.position.toString must_== "1 1 W"
    }
    "turn right for the command R and head EAST" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("N"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("R").get

      // THEN
      result.position.toString must_== "1 1 E"
    }
    "follow should not receive different Directions.apply than the cardinal points" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("N"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      rover.executeCommands("Q").get must throwA[RoverCommandException]
    }
    "should not return a position outside the plateau" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("N"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      rover.executeCommands("MMMMMMM").get must throwA[RoverPlateauException]
    }
  }

  "Given a Rover with a position heading SOUTH" should {

    "move vertically (but decrease on y axis) for the command M" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("S"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("M").get

      // THEN
      result.position.toString must_== "1 0 S"
    }
    "turn left for the command L and head EAST" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("S"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("L").get

      // THEN
      result.position.toString must_== "1 1 E"
    }
    "turn right for the command R and head WEST" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("S"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("R").get

      // THEN
      result.position.toString must_== "1 1 W"
    }
  }

  "Given a Rover with a position heading WEST" should {

    "move horizontally (but negatively on x axis) for the command M" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("W"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("M").get

      // THEN
      result.position.toString must_== "0 1 W"
    }
    "turn left for the command L and head SOUTH" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("W"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("L").get

      // THEN
      result.position.toString must_== "1 1 S"
    }
    "turn right for the command R and head NORTH" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("W"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("R").get

      // THEN
      result.position.toString must_== "1 1 N"
    }
  }
  "Given a Rover with a position heading EAST" should {

    "move horizontally for the command M" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("E"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("M").get

      // THEN
      result.position.toString must_== "2 1 E"
    }
    "turn left for the command L and head NORTH" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("E"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("L").get

      // THEN
      result.position.toString must_== "1 1 N"
    }
    "turn right for the command R and head SOUTH" in {
      //GIVEN
      val plateau = Plateau(5,5)
      val initialPosition = Position(1,1, Directions.apply("E"))
      val rover = Rover(initialPosition, plateau)

      // WHEN
      val result = rover.executeCommands("R").get

      // THEN
      result.position.toString must_== "1 1 S"
    }
  }
}