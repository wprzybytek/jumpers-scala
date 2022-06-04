package jumper.map

class Move(currPosition: Vector2d, val isWhite: Boolean) {
  var moves: List[Vector2d] = List(currPosition)
  var finished: Boolean = false
}
