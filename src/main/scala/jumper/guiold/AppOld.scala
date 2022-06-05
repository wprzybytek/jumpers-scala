//package jumper.guiold
//
//import javafx.application.Application
//import javafx.geometry.HPos
//import javafx.geometry.Insets
//import javafx.geometry.Pos
//import javafx.scene.Node
//import javafx.scene.Scene
//import javafx.scene.image.Image
//import javafx.scene.layout._
//import javafx.scene.paint.Color
//import javafx.scene.control.Button
//import javafx.scene.control.Label
//import javafx.stage.Stage
//import jumper.map.BattleField
//import jumper.map.Pawn
//import jumper.map.Vector2d
//import java.awt._
//import java.io.FileInputStream
//import java.io.FileNotFoundException
//
//
//class App extends Application {
//  private[guiold] var window = null
//  private[guiold] var scene = null
//  private[guiold] val gridpane = new GridPane
//  private[guiold] val button = new Button("Make a move")
//  private[guiold] val label = new Label("Player White begins...")
//  private[guiold] var whitePawn = null
//  private[guiold] var blackPawn = null
//
//  def setLabel(text: String): Unit = {
//    this.label.setText(text)
//  }
//
//  def mapVisual(battleField: BattleField, pane: GridPane): Unit = {
//    pane.setGridLinesVisible(false)
//    pane.getColumnConstraints.clear()
//    pane.getRowConstraints.clear()
//    pane.getChildren.clear()
//    pane.setGridLinesVisible(true)
//    pane.setPadding(new Insets(10, 10, 10, 10))
//    for (i <- 0 until battleField.mapWeight) {
//      val columnConstraints = new ColumnConstraints(60)
//      pane.getColumnConstraints.add(columnConstraints)
//    }
//    for (i <- 0 until battleField.mapHeight) {
//      val rowConstraints = new RowConstraints(60)
//      pane.getRowConstraints.add(rowConstraints)
//    }
//    for (y <- 0 until battleField.mapHeight) {
//      for (x <- 0 until battleField.mapWeight) {
//        if (battleField.getPawnsOfP1.get(new Vector2d(x, y)) != null) {
//          val Box = new GuiElementBoxOld(whitePawn)
//          var box = null
//          try box = Box.MakeBox
//          catch {
//            case e: FileNotFoundException =>
//              e.printStackTrace()
//          }
//          GridPane.setConstraints(box, x, y)
//          GridPane.setHalignment(box, HPos.CENTER)
//          pane.add(box, x, y)
//        }
//        if (battleField.getPawnsOfP2.get(new Vector2d(x, y)) != null) {
//          val Box = new GuiElementBoxOld(blackPawn)
//          var box = null
//          try box = Box.MakeBox
//          catch {
//            case e: FileNotFoundException =>
//              e.printStackTrace()
//          }
//          GridPane.setConstraints(box, x, y)
//          GridPane.setHalignment(box, HPos.CENTER)
//          pane.add(box, x, y)
//        }
//      }
//    }
//  }
//
//  def moveVisual(battleField: BattleField, pane: GridPane, which: Int): Unit = {
//    pane.setGridLinesVisible(false)
//    pane.getColumnConstraints.clear()
//    pane.getRowConstraints.clear()
//    pane.getChildren.clear()
//    pane.setGridLinesVisible(true)
//    pane.setPadding(new Insets(10, 10, 10, 10))
//    for (i <- 0 until battleField.mapWeight) {
//      val columnConstraints = new ColumnConstraints(60)
//      pane.getColumnConstraints.add(columnConstraints)
//    }
//    for (i <- 0 until battleField.mapHeight) {
//      val rowConstraints = new RowConstraints(60)
//      pane.getRowConstraints.add(rowConstraints)
//    }
//    for (y <- 0 until battleField.mapHeight) {
//      for (x <- 0 until battleField.mapWeight) {
//        if (battleField.getPawnsOfP1.get(new Vector2d(x, y)) != null) {
//          val Box = new GuiElementBoxOld(whitePawn)
//          var box = null
//          try box = Box.MakeBox
//          catch {
//            case e: FileNotFoundException =>
//              e.printStackTrace()
//          }
//          GridPane.setConstraints(box, x, y)
//          GridPane.setHalignment(box, HPos.CENTER)
//          pane.add(box, x, y)
//          if (which == 1) {
//            assert(box != null)
//            val finalBox = box
//            val finalX = x
//            val finalY = y
//            box.setOnMouseClicked((event: MouseEvent) => {
//              def foo(event: MouseEvent) = {
//                finalBox.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)))
//                battleField.setWhichPawn(new Vector2d(finalX, finalY))
//                battleField.setInMove(true)
//              }
//
//              foo(event)
//            })
//          }
//        }
//        if (battleField.getPawnsOfP2.get(new Vector2d(x, y)) != null) {
//          val Box = new GuiElementBoxOld(blackPawn)
//          var box = null
//          try box = Box.MakeBox
//          catch {
//            case e: FileNotFoundException =>
//              e.printStackTrace()
//          }
//          GridPane.setConstraints(box, x, y)
//          GridPane.setHalignment(box, HPos.CENTER)
//          pane.add(box, x, y)
//          if (which == 2) {
//            assert(box != null)
//            val finalBox = box
//            val finalX = x
//            val finalY = y
//            box.setOnMouseClicked((event: MouseEvent) => {
//              def foo(event: MouseEvent) = {
//                finalBox.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)))
//                battleField.setWhichPawn(new Vector2d(finalX, finalY))
//                battleField.setInMove(true)
//              }
//
//              foo(event)
//            })
//          }
//        }
//      }
//    }
//  }
//
//  def inMoveVisual(battleField: BattleField, pane: GridPane, which: Int, whichPawn: Vector2d): Unit = {
//    pane.setGridLinesVisible(false)
//    pane.getColumnConstraints.clear()
//    pane.getRowConstraints.clear()
//    pane.getChildren.clear()
//    pane.setGridLinesVisible(true)
//    pane.setPadding(new Insets(10, 10, 10, 10))
//    for (i <- 0 until battleField.mapWeight) {
//      val columnConstraints = new ColumnConstraints(60)
//      pane.getColumnConstraints.add(columnConstraints)
//    }
//    for (i <- 0 until battleField.mapHeight) {
//      val rowConstraints = new RowConstraints(60)
//      pane.getRowConstraints.add(rowConstraints)
//    }
//    for (y <- 0 until battleField.mapHeight) {
//      for (x <- 0 until battleField.mapWeight) {
//        if (battleField.getPawnsOfP1.get(new Vector2d(x, y)) != null) {
//          val Box = new GuiElementBoxOld(whitePawn)
//          var box = null
//          try box = Box.MakeBox
//          catch {
//            case e: FileNotFoundException =>
//              e.printStackTrace()
//          }
//          GridPane.setConstraints(box, x, y)
//          GridPane.setHalignment(box, HPos.CENTER)
//          pane.add(box, x, y)
//          if (x == whichPawn.x && y == whichPawn.y) box.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)))
//        }
//        else if (battleField.getPawnsOfP2.get(new Vector2d(x, y)) != null) {
//          val Box = new GuiElementBoxOld(blackPawn)
//          var box = null
//          try box = Box.MakeBox
//          catch {
//            case e: FileNotFoundException =>
//              e.printStackTrace()
//          }
//          GridPane.setConstraints(box, x, y)
//          GridPane.setHalignment(box, HPos.CENTER)
//          pane.add(box, x, y)
//          if (x == whichPawn.x && y == whichPawn.y) box.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)))
//        }
//        else {
//          val box = new VBox
//          GridPane.setConstraints(box, x, y)
//          GridPane.setHalignment(box, HPos.CENTER)
//          pane.add(box, x, y)
//          if (which == 1) {
//            assert(box != null)
//            val finalBox = box
//            val finalX = x
//            val finalY = y
//            box.setOnMouseClicked((event: MouseEvent) => {
//              def foo(event: MouseEvent) = {
//                if (battleField.checkField(new Vector2d(finalX, finalY))) finalBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)))
//                else this.setLabel("Choose another field...")
//              }
//
//              foo(event)
//            })
//          }
//          if (which == 2) {
//            assert(box != null)
//            val finalBox = box
//            val finalX = x
//            val finalY = y
//            box.setOnMouseClicked((event: MouseEvent) => {
//              def foo(event: MouseEvent) = {
//                if (battleField.checkField(new Vector2d(finalX, finalY))) finalBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)))
//                else this.setLabel("Choose another field...")
//              }
//
//              foo(event)
//            })
//          }
//        }
//      }
//    }
//  }
//
//  def winningVisual(battleField: BattleField, pane: GridPane, which: Int, winner: Int): Unit = {
//    pane.setGridLinesVisible(false)
//    pane.getColumnConstraints.clear()
//    pane.getRowConstraints.clear()
//    pane.getChildren.clear()
//    pane.setGridLinesVisible(true)
//    pane.setPadding(new Insets(10, 10, 10, 10))
//    for (i <- 0 until battleField.mapWeight) {
//      val columnConstraints = new ColumnConstraints(60)
//      pane.getColumnConstraints.add(columnConstraints)
//    }
//    for (i <- 0 until battleField.mapHeight) {
//      val rowConstraints = new RowConstraints(60)
//      pane.getRowConstraints.add(rowConstraints)
//    }
//    for (y <- 0 until battleField.mapHeight) {
//      for (x <- 0 until battleField.mapWeight) {
//        if (x + y <= which || (x + y) % 2 == (which / 2) % 2) {
//          var Box = new GuiElementBoxOld(blackPawn)
//          if (winner == 1) Box = new GuiElementBoxOld(whitePawn)
//          var box = null
//          try box = Box.MakeBox
//          catch {
//            case e: FileNotFoundException =>
//              e.printStackTrace()
//          }
//          GridPane.setConstraints(box, x, y)
//          GridPane.setHalignment(box, HPos.CENTER)
//          pane.add(box, x, y)
//          box.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)))
//        }
//      }
//    }
//  }
//
//  @throws[Exception]
//  override def start(primaryStage: Stage): Unit = {
//    window = primaryStage
//    val battleField = new BattleField(gridpane, this)
//    mapVisual(battleField, gridpane)
//    val thread = new Thread(battleField)
//    thread.start()
//    val layout = new VBox(label, gridpane, button)
//    layout.setAlignment(Pos.CENTER)
//    button.setOnAction((event: ActionEvent) => {
//      def foo(event: ActionEvent) = if (battleField.getPositions.size == 0) this.setLabel("No moves has been assigned...")
//      else battleField.setInMove(false)
//
//      foo(event)
//    })
//    scene = new Scene(layout, 500, 550)
//    primaryStage.setTitle("Jumpers")
//    window.setScene(scene)
//    window.show()
//  }
//}
