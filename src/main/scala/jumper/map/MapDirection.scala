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
      new Vector2d(-1,1)
    case SOUTHEAST =>
      new Vector2d(1, 1)
    case SOUTHWEST =>
      new Vector2d(1,-1)
    case NORTHWEST =>
      new Vector2d(-1, -1)
    case _ =>
      new Vector2d(0, 0)
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
