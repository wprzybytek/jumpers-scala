package jumper.map

class Move(var currPosition: Vector2d, var isWhite: Boolean) {
//  var moves: List[Vector2d] = List(currPosition)
  var to: Vector2d = _
  var inMove = false
}
