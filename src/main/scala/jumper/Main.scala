package jumper

import jumper.gui.GUI
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color.White


//object Main {
//  def main(args: Array[String]): Unit = {
//    var battleField: BattleField = new BattleField()
//    battleField.initialize()
//    battleField.visualize()
//    var test: Move = new Move(new Vector2d(0,0),true)
//    println(test.moves.size)
//    println(battleField.checkMove(new Vector2d(0,1),test))
//    println(test.moves.size)
//    battleField.executeStep(test,0,1)
//    battleField.visualize()
//
//
//
//  }
//}

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
