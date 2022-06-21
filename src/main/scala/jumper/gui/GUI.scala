package jumper.gui

import jumper.map.{BattleField, Pawn, Vector2d}
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
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
  val button = new Button("Click me")
  button.onAction = onButtonClick

  def onButtonClick(event: ActionEvent): Unit = {
    println("Clicked a button")
  }

  def checkMove(pawnX: Integer, pawnY: Integer, boardX: Integer, boardY: Integer): Unit = {
    println(s"pawn row: $pawnX, pawn column: $pawnY, board row: $boardX, board column: $boardY")

    var pawn: Pawn = battleField.pawns.getOrElse(new Vector2d(pawnX,pawnY),null)
    if (pawn == null){
      println("wrong")
      return
    }

    if (battleField.move.isWhite != pawn.isWhite){
      println("wrong player")
      return
    }

    battleField.startMove(pawns.getOrElse(new Vector2d(pawnX,pawnY),null))
    println(battleField.checkMove(new Vector2d(boardX,boardY)))

    board.drawBoard()
  }

  children = Array(info, board, button)
}
