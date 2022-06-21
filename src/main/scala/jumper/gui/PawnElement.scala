package jumper.gui

import javafx.scene.input.TransferMode
import jumper.map.Pawn
import scalafx.scene.image.ImageView
import scalafx.scene.input.{ClipboardContent, Dragboard}

class PawnElement(row: Int, column: Int, val pawn: Pawn, checkMove: (Integer, Integer, Integer, Integer) => Unit)
  extends BoardElement(row, column, checkMove: (Integer, Integer, Integer, Integer) => Unit) {

  children = createImage()

  def createImage(): ImageView = {
    val srcImg: String = if (pawn.isWhite) "white.png" else "black.png"
    val image = new ImageView(srcImg)
    image.setFitWidth(50)
    image.setFitHeight(50)
    image
  }

  onDragDetected = _ => {
    val dragBoard: Dragboard = this.startDragAndDrop(TransferMode.MOVE)
    val content: ClipboardContent = new ClipboardContent()
    content.putString(s"$row $column")
    dragBoard.setContent(content)
  }
}
