package jumper

import jumper.gui.GUI
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color.White

object Main extends JFXApp3 {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Jumpers Game"
      width = 800
      height = 600
      scene = new Scene {
        fill = White
        content = new GUI()
      }
    }
  }
}
