package jumper.gui

import jumper.map.Pawn
import scalafx.scene.image.ImageView

class PawnElement(row: Int, column: Int, val pawn: Pawn) extends BoardElement(row, column) {

  children = createImage()

  onMouseClicked = (event) => {
    println("Clicked %s pawn on row = %d, column = %d".format(if (pawn.isWhite) "white" else "black", row, column))
  }

  def createImage(): ImageView = {
    val srcImg: String = if (pawn.isWhite) "white.png" else "black.png"
    val image = new ImageView(srcImg)
    image.setFitWidth(50)
    image.setFitHeight(50)
    image
  }
}
