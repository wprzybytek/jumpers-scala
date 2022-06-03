package jumper.map

object MapDirection extends Enumeration {
  type MapDirection = Value
  val NORTH, EAST, SOUTH, WEST = Value

  def next: MapDirection = this match {
    case NORTH =>
      EAST
    case EAST =>
      SOUTH
    case SOUTH =>
      WEST
    case WEST =>
      NORTH
    case _ =>
      throw new IllegalStateException("Unexpected value")
  }

  def toUnitVector: Vector2d = this match {
    case EAST =>
      new Vector2d(1, 0)
    case WEST =>
      new Vector2d(-1, 0)
    case NORTH =>
      new Vector2d(0, 1)
    case SOUTH =>
      new Vector2d(0, -1)
    case _ =>
      new Vector2d(0, 0)
  }

  def intToMapDirection(that: Int): MapDirection = that match {
    case 0 =>
      NORTH
    case 1 =>
      EAST
    case 2 =>
      SOUTH
    case 3 =>
      WEST
    case _ =>
      throw new IllegalStateException("Unexpected value: " + that)
  }
}
