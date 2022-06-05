package jumper.gui

import scalafx.geometry.Pos
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox

class PawnElement(val isWhite: Boolean, val row: Int, val column: Int) extends VBox {
  alignment = Pos.Center
  children = createImage()
  onMouseClicked = (event) => {
    println("Clicked %s pawn on row = %d, column = %d".format(if (isWhite) "white" else "black", row, column))
  }

  def createImage(): ImageView = {
    val srcImg: String = if (isWhite) "white.png" else "black.png"
    val image = new ImageView(srcImg)
    image.setFitWidth(50)
    image.setFitHeight(50)
    image
  }
}
