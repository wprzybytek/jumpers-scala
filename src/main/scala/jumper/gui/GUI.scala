package jumper.gui

import javafx.scene.paint.Color
import jumper.map.{BattleField, Pawn, Vector2d}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

import scala.collection.mutable

class GUI() extends VBox {
  alignment = Pos.TopCenter
  prefWidth = 800
  prefHeight = 600

  var battleField = new BattleField()
  battleField.initialize()
  var pawns: mutable.LinkedHashMap[Vector2d, Pawn] = battleField.pawns

  val info = new Label(text = "White pawns move...")
  info.setFont(Font.font("verdana", 20))
  info.margin = Insets(20)

  val error = new Label()
  error.setFont(Font.font("verdana", 20))
  error.margin = Insets(20)
  error.setTextFill(Color.RED)

  var board: Board = new Board(pawns, checkMove: (Integer, Integer, Integer, Integer) => Unit)

  def checkMove(pawnX: Integer, pawnY: Integer, boardX: Integer, boardY: Integer): Unit = {
    val pawn: Pawn = battleField.pawns.getOrElse(new Vector2d(pawnX, pawnY), null)
    if (pawn == null) {
      error.setText("No pawn here")
      return
    }

    if (battleField.move.isWhite != pawn.isWhite) {
      error.setText("Wrong player")
      return
    }

    battleField.startMove(pawns.getOrElse(new Vector2d(pawnX, pawnY), null))
    if (!battleField.checkMove(new Vector2d(boardX, boardY))) {
      error.setText("Wrong move")
      return
    }

    board.drawBoard()
    info.setText(if (battleField.move.isWhite) "White pawns move..." else "Black pawns move...")
    error.setText("")

    if (battleField.checkIfSomeoneWon != "") {
      new VictoryBox(battleField.checkIfSomeoneWon, restartGame).display()
    }
  }

  def restartGame(): Unit = {
    battleField = new BattleField()
    battleField.initialize()
    pawns = battleField.pawns
    board = new Board(pawns, checkMove: (Integer, Integer, Integer, Integer) => Unit)
    info.setText("White pawns move...")
    children = Array(info, board, error)
    board.drawBoard()
  }

  children = Array(info, board, error)
}
