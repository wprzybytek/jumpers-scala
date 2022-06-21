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
  val board = new Board(pawns)
  val button = new Button("Click me")
  button.onAction = onButtonClick

  def onButtonClick(event: ActionEvent): Unit = {
    println("Clicked a button")
  }

  children = Array(info, board, button)
}
