package jumper.gui

import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox
import scalafx.stage.{Modality, Stage}

class VictoryBox(val winner: String, restartGame: () => Unit) {
  def display(): Unit = {
    val window = new Stage()
    window.initModality(Modality.ApplicationModal)
    window.width = 300
    window.height = 200

    window.onCloseRequest = _ => {
      System.exit(0)
    }

    val label = new Label(s"$winner won!")
    val button = new Button("Play again")
    button.onAction = _ => {
      restartGame()
      window.close()
    }

    val layout = new VBox(10)
    layout.children = Array(label, button)
    layout.setAlignment(Pos.Center)

    val scene = new Scene(layout)
    window.scene = scene
    window.showAndWait()
  }
}
