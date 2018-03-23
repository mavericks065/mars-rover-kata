package au.com.kata.marsrover

case class Position(x: Int, y: Int, heading: Direction) {

  private val directions: List[Direction] = List(North, South, East, West)

  def isWithinPlateau : (Plateau) => Boolean = plateau =>
    if (x >= 0 && x <= plateau.x && y >= 0 && y <= plateau.y) true else false

  def turnRight = Position(x, y, newDirectionFor(heading.rightCardinal))

  def turnLeft = Position(x, y, newDirectionFor(heading.leftCardinal))

  def move = heading match {
    case North => Position(x, y + 1, heading)
    case South => Position(x, y - 1, heading)
    case West => Position(x - 1, y, heading)
    case East => Position(x + 1, y, heading)
  }

  override def toString = s"$x $y ${heading.currentCardinal}"

  private def newDirectionFor(cardinal: String) = directions.find(d => d.currentCardinal == cardinal).get
}


sealed class Direction(val currentCardinal: String, val rightCardinal: String, val leftCardinal: String)

case object North extends Direction("N", "E", "W")
case object South extends Direction("S", "W", "E")
case object East extends Direction("E", "S", "N")
case object West extends Direction("W", "N", "S")

object Directions {
  def apply: (String) => Direction = {
    case "N" => North
    case "S" => South
    case "E" => East
    case "W" => West
  }

}