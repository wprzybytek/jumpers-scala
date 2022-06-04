package jumper.map

import javafx.application.Platform
import javafx.scene.layout.GridPane
import MapDirection.{MapDirection, NORTH, intToMapDirection, toUnitVector}

import scala.collection.mutable
import scala.collection.mutable.LinkedHashMap


class BattleField(){
  val pawns: mutable.LinkedHashMap[Vector2d, Pawn] = mutable.LinkedHashMap()

  def initialize(): Unit = {
    for (i <- 0 until 8) {
      val position1 = new Vector2d(i,0)
      pawns.put(position1, new Pawn(true, position1))
      val position2 = new Vector2d(i,7)
      pawns.put(position2, new Pawn(false, position2))
    }
  }

  def checkMove(newPosition: Vector2d, move: Move): Boolean = {
    if (newPosition.precedes(new Vector2d(0, 0)) || newPosition.follows(new Vector2d(7, 7))) return false

    val currPosition: Vector2d = move.moves.last

    if (move.moves.size == 1) {
      for (i <- 0 until 4) {
        if (currPosition.add(toUnitVector(intToMapDirection(i))) == newPosition) {
          if (pawns.getOrElse(newPosition, null) != null) return false

          move.moves = move.moves :+ newPosition
          move.finished = true
          return true
        }
      }



      for (i <- 0 until 4) {
        if (pawns.getOrElse(currPosition.add(toUnitVector(intToMapDirection(i))),null) == null) {
          if (currPosition.add(toUnitVector(intToMapDirection(i))).add(toUnitVector(intToMapDirection(i))) == newPosition) {
            if (pawns.getOrElse(newPosition, null) != null) return false

            move.moves = move.moves :+ newPosition
            return true
          }
        }
      }
    }

    else if (!move.finished){
      for (i <- 0 until 4) {
        if (currPosition.add(toUnitVector(intToMapDirection(i))).add(toUnitVector(intToMapDirection(i))) == newPosition) {
          if (pawns.getOrElse(newPosition, null) != null) return false

          move.moves = move.moves :+ newPosition
          return true
        }
      }
    }


    return false
  }

  def executeStep(move: Move, from: Int, to: Int): Unit ={
    if (pawns.getOrElse(move.moves(from), null) == null){
      println("Error - wrong starting position - no pawn here")
      return
    }
    var pawn: Pawn = pawns(move.moves(from))
    pawns.remove(move.moves(from))
//    pawns.addOne(move.moves(to): Vector2d,new Pawn(move.isWhite,move.moves(to)))
    pawns.addOne(move.moves(to): Vector2d,pawn)
  }


  def visualize(): Unit = {
    val out: String = ""
    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
//        val some:Option[String] = Some(" _ ")
        if (pawns.getOrElse(new Vector2d(i,j), null) == null) {print(" _ ")}
        else {
          if (pawns(new Vector2d(i,j)).isWhite) print(" W ")
          else print(" B ")
        }
      }
      println()
    }
  }

  def checkIfSomeoneWon: String = {
    var winner: String = "white"
    for (i <- 0 until 8) {
      if (pawns.getOrElse(new Vector2d(i,7), null) != null) {
        if (!pawns(new Vector2d(i,7)).isWhite) {
          winner = ""
        }
      }
      else winner = ""
    }

    if (winner == "white") return winner

    winner = "black"
    for (i <- 0 until 8) {
      if (pawns.getOrElse(new Vector2d(i,0), null) != null) {
        if (pawns(new Vector2d(i,0)).isWhite) {winner = ""}
      }
      else winner = ""
    }

    return winner
  }
}
