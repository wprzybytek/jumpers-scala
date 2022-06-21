package jumper.gui

import javafx.scene.layout.{Background, BackgroundFill}
import javafx.scene.paint.Color
import javafx.geometry.Insets
import javafx.scene.input.{Dragboard, TransferMode}
import javafx.scene.layout.CornerRadii
import scalafx.geometry.Pos
import scalafx.scene.layout.VBox

class BoardElement(val row: Int, val column: Int,
                   checkMove: (Integer, Integer, Integer, Integer) => Unit)
  extends VBox{

  alignment = Pos.Center

  this.setBackground(new Background(new BackgroundFill(if ((row+column)%2 == 0) Color.WHITE else Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(1,1,1,1))))


  onDragOver = (event) => {
    event.acceptTransferModes(TransferMode.MOVE)
  }

  onDragDropped = (event) => {
    val dragBoard: Dragboard = event.getDragboard
    if (dragBoard.hasString) {
      val x = dragBoard.getString.charAt(0) - '0'
      val y = dragBoard.getString.charAt(2) - '0'
      checkMove(x, y, row, column)
    }
    else {
      println("nie dziala")
    }
  }



}
