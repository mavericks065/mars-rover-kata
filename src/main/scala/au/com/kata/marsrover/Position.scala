package au.com.kata.marsrover

case class Position(x: Int, y: Int, heading: Direction) {
  def isWithinPlateau : (Plateau) => Boolean = plateau =>
    if (x >= 0 && x <= plateau.x && y >= 0 && y <= plateau.y) true else false

  def turnRight = heading.cardinal match {
    case "N" => Position(x, y, Direction("E"))
    case "S" => Position(x, y, Direction("W"))
    case "W" => Position(x, y, Direction("N"))
    case "E" => Position(x, y, Direction("S"))

  }

  def turnLeft = heading.cardinal match {
    case "N" => Position(x, y, Direction("W"))
    case "S" => Position(x, y, Direction("E"))
    case "W" => Position(x, y, Direction("S"))
    case "E" => Position(x, y, Direction("N"))
  }

  def move = heading.cardinal match {
    case "N" => Position(x, y + 1, heading)
    case "S" => Position(x, y - 1, heading)
    case "W" => Position(x - 1, y, heading)
    case "E" => Position(x + 1, y, heading)
  }

  override def toString = s"$x $y ${heading.cardinal}"
}


case class Direction(cardinal: String)
