package jumper.map

import scala.collection.mutable
import MapDirection._

class BattleField() {
  val pawns: mutable.LinkedHashMap[Vector2d, Pawn] = mutable.LinkedHashMap()
  var move: Move = new Move(Vector2d(0, 0), true)
  move.inMove = false

  def initialize(): Unit = {
    for (i <- 0 until 8) {
      val position1 = Vector2d(i, i % 2)
      pawns.put(position1, Pawn(isWhite = true, position1, this))
      val position2 = Vector2d(i, 6 + i % 2)
      pawns.put(position2, Pawn(isWhite = false, position2, this))
    }
  }

  def startMove(pawn: Pawn): Boolean = {
    if (move.inMove) false
    else {
      move.currPosition = pawn.currentPosition
      true
    }
  }

  def checkMove(newPosition: Vector2d): Boolean = {
    if (newPosition.precedes(Vector2d(0, 0)) || newPosition.follows(Vector2d(7, 7))) return false

    val currPosition: Vector2d = move.currPosition
    val upperForward: Vector2d = if (move.isWhite) toUnitVector(SOUTHEAST) else toUnitVector(SOUTHWEST)
    val lowerForward: Vector2d = if (move.isWhite) toUnitVector(NORTHEAST) else toUnitVector(NORTHWEST)

    if (pawns.getOrElse(newPosition, null) != null) return false
    if (currPosition.add(lowerForward) == newPosition || currPosition.add(upperForward) == newPosition) {
      move.to = newPosition
      this.executeStep()
      this.finishMove()
      return true
    }
    else {
      for (i <- 0 until 4) {
        val moveDirection: Vector2d = toUnitVector(intToMapDirection(i))
        if (currPosition.add(moveDirection).add(moveDirection) == newPosition) {
          if (pawns.getOrElse(currPosition.add(moveDirection), null) == null) return false
          if (pawns.getOrElse(currPosition.add(moveDirection), null).isWhite == move.isWhite) return false
          move.to = newPosition
          this.executeStep()
          pawns.remove(currPosition.add(moveDirection))
          move.inMove = true
          if (!this.areMovesPossible()) this.finishMove()
          return true
        }
      }
    }
    false
  }

  def areMovesPossible(): Boolean = {
    for (i <- 0 until 4) {
      val moveDirection: Vector2d = toUnitVector(intToMapDirection(i))

      val opponent: Pawn = pawns.getOrElse(move.to.add(moveDirection), null)
      if (opponent != null) {
        if (opponent.isWhite != move.isWhite) {
          if (pawns.getOrElse(move.to.add(moveDirection).add(moveDirection), null) == null) {
            if (!move.to.add(moveDirection).add(moveDirection).precedes(Vector2d(0, 0))
              && !move.to.add(moveDirection).add(moveDirection).follows(Vector2d(7, 7))) {
              return true
            }
          }
        }
      }

    }
    false
  }

  def finishMove(): Unit = {
    move.currPosition = null
    move.isWhite = !move.isWhite
    move.to = null
    move.inMove = false
  }

  def executeStep(): Unit = {
    val pawn: Pawn = pawns(move.currPosition)
    pawn.currentPosition = move.to
    pawns.remove(move.currPosition)
    pawns.addOne(move.to: Vector2d, pawn)
    move.currPosition = move.to
  }

  def checkIfSomeoneWon(): String = {

    var playerWhite: Int = 0
    var playerBlack: Int = 0

    for ((_, pawn) <- this.pawns) {
      if (pawn.isWhite) playerWhite = playerBlack + 1
      else if (!pawn.isWhite) playerBlack = playerBlack + 1
    }

    if (playerBlack == 0) "White"
    else if (playerWhite == 0) "Black"
    else ""
  }
}