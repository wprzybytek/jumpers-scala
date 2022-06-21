package jumper.gui

import jumper.map.Pawn
import scalafx.geometry.Pos
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox

class PawnElement(val row: Int, val column: Int, val pawn: Pawn) extends VBox {

  alignment = Pos.Center
  children = createImage()

  onMouseClicked = (event) => {
    println("Clicked %s pawn on row = %d, column = %d".format(if (pawn.isWhite) "white" else "black", row, column))
//    if (!pawn.battleField.move.inMove) {
//      pawn.battleField.startMove(pawn)
//    }
  }

  def createImage(): ImageView = {
    val srcImg: String = if (pawn.isWhite) "white.png" else "black.png"
    val image = new ImageView(srcImg)
    image.setFitWidth(50)
    image.setFitHeight(50)
    image
  }
}
