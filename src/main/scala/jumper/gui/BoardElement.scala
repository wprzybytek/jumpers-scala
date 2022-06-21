package jumper.gui

import javafx.scene.layout.{Background, BackgroundFill}
import javafx.scene.paint.Color
import javafx.geometry.Insets
import javafx.scene.layout.CornerRadii
import scalafx.geometry.Pos
import scalafx.scene.layout.VBox

class BoardElement(val row: Int, val column: Int) extends VBox{

  alignment = Pos.Center

  this.setBackground(new Background(new BackgroundFill(if ((row+column)%2 == 0) Color.WHITE else Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(1,1,1,1))))

  onMouseClicked = (event) => {
    println("Clicked elem on row = %d, column = %d".format( row, column))
  }


}
