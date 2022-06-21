package jumper.map

case class Vector2d(x: Int, y: Int) {

  def precedes(other: Vector2d): Boolean = {
    if (x < other.x || y < other.y) return true
    false
  }

  def follows(other: Vector2d): Boolean = {
    if (x > other.x || y > other.y) return true
    false
  }

  def add(other: Vector2d): Vector2d = {
    val result = new Vector2d(x + other.x, y + other.y)
    result
  }
}
