package jumper.gui

import jumper.map.{Pawn, Vector2d}
import scalafx.geometry.Pos
import scalafx.scene.layout.{ColumnConstraints, GridPane, RowConstraints}

import scala.collection.mutable

class Board(var pawns: mutable.LinkedHashMap[Vector2d, Pawn]) extends GridPane {
  val rows = 8
  val columns = 8
  val fieldSize = 55

  alignment = Pos.Center

  this.drawBoard()

  def drawBoard(): Unit = {
    this.gridLinesVisible = false
    this.columnConstraints = for (i <- 0 until rows) yield new ColumnConstraints(fieldSize)
    this.rowConstraints = for (i <- 0 until columns) yield new RowConstraints(fieldSize)
    this.children = Array()
    this.gridLinesVisible = true

    addPawnsToBoard()
  }

  def addPawnsToBoard(): Unit = {
    for ((position, pawn) <- this.pawns) {
      this.add(new PawnElement(position.x, position.y,pawn), position.y, position.x)
    }
  }
}