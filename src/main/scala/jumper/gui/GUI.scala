package jumper.gui

import jumper.map.{BattleField, Pawn, Vector2d}
import scalafx.geometry.Pos
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox

import scala.collection.mutable

class GUI() extends VBox {
  alignment = Pos.Center
  prefWidth = 800
  prefHeight = 600
  spacing = 20

  val battleField = new BattleField()
  battleField.initialize()
  var pawns: mutable.LinkedHashMap[Vector2d, Pawn] = battleField.pawns

  var infoText = "Some info text"
  val info = new Label(infoText)
  val board: Board= new Board(pawns, checkMove: (Integer, Integer, Integer, Integer) => Unit)

  def checkMove(pawnX: Integer, pawnY: Integer, boardX: Integer, boardY: Integer): Unit = {
    var pawn: Pawn = battleField.pawns.getOrElse(new Vector2d(pawnX,pawnY),null)
    if (pawn == null){
      println("No pawn here")
      return
    }

    if (battleField.move.isWhite != pawn.isWhite){
      println("Wrong player")
      return
    }

    battleField.startMove(pawns.getOrElse(new Vector2d(pawnX,pawnY),null))
    if (!battleField.checkMove(new Vector2d(boardX,boardY))){
      println("Wrong move")
    }

    board.drawBoard()

    if (battleField.checkIfSomeoneWon != ""){
      println(battleField.checkIfSomeoneWon + "won!")
    }
  }

  children = Array(info, board)
}
