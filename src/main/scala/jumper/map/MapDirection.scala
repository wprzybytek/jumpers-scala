package jumper.map

object MapDirection extends Enumeration {
  type MapDirection = Value
  val NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST = Value

  def next: MapDirection = this match {
    case NORTHEAST =>
      SOUTHEAST
    case SOUTHEAST =>
      SOUTHWEST
    case SOUTHWEST =>
      NORTHWEST
    case NORTHWEST =>
      NORTHEAST
    case _ =>
      throw new IllegalStateException("Unexpected value")
  }

  def toUnitVector(that: MapDirection): Vector2d = that match {
    case NORTHEAST =>
      Vector2d(-1, 1)
    case SOUTHEAST =>
      Vector2d(1, 1)
    case SOUTHWEST =>
      Vector2d(1, -1)
    case NORTHWEST =>
      Vector2d(-1, -1)
    case _ =>
      Vector2d(0, 0)
  }

  def intToMapDirection(that: Int): MapDirection = that match {
    case 0 =>
      NORTHEAST
    case 1 =>
      SOUTHEAST
    case 2 =>
      SOUTHWEST
    case 3 =>
      NORTHWEST
    case _ =>
      throw new IllegalStateException("Unexpected value: " + that)
  }
}
