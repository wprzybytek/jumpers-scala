//package jumper.gui
//
//import scalafx.geometry.Pos
//import scalafx.scene.image.ImageView
//import scalafx.scene.layout.VBox
//
//class BoardElement(val row: Int, val column: Int) extends VBox{
//
//  alignment = Pos.Center
//  children = createImage()
//
//  def createImage(): ImageView = {
//    val srcImg: String = if (pawn.isWhite) "white.png" else "black.png"
//    val image = new ImageView(srcImg)
//    image.setFitWidth(50)
//    image.setFitHeight(50)
//    image
//  }
//
//
//}
