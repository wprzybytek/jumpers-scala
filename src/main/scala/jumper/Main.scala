package jumper

//import jumper.gui.App
//import javafx.application.Application
import javafx.scene.shape.MoveTo
import jumper.map.MapDirection.{intToMapDirection, toUnitVector}
import jumper.map.{BattleField, Move, Vector2d}


object Main {
  def main(args: Array[String]): Unit = {
    var battleField: BattleField = new BattleField()
    battleField.initialize()
    battleField.visualize()
    var test: Move = new Move(new Vector2d(0,0),true)
    println(test.moves.size)
    println(battleField.checkMove(new Vector2d(0,1),test))
    println(test.moves.size)
    battleField.executeStep(test,0,1)
    battleField.visualize()



  }
}

