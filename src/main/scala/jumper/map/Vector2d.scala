package jumper.map

class Vector2d(val x: Int, val y: Int) {

  def precedes(other: Vector2d): Boolean = {
    if (x < other.x && y < other.y) return true
    false
  }

  def follows(other: Vector2d): Boolean = {
    if (x > other.x && y > other.y) return true
    false
  }

  def add(other: Vector2d): Vector2d = {
    val result = new Vector2d(x + other.x, y + other.y)
    result
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Vector2d]

  override def equals(other: Any): Boolean = other match {
    case that: Vector2d =>
      (that canEqual this) &&
        x == that.x &&
        y == that.y
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(x, y)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString: String = "(" + x + "," + y + ")"
}
