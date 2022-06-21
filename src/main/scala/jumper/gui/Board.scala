package jumper.gui

import jumper.map.{Pawn, Vector2d}
import scalafx.geometry.Pos
import scalafx.scene.layout.{ColumnConstraints, GridPane, RowConstraints}

import scala.collection.mutable

class Board(var pawns: mutable.LinkedHashMap[Vector2d, Pawn],
            checkMove: (Integer, Integer, Integer, Integer) => Unit)
  extends GridPane {

  val rows = 8
  val columns = 8
  val fieldSize = 55

  alignment = Pos.Center

  this.drawBoard()

  def drawBoard(): Unit = {
    this.gridLinesVisible = false
    this.columnConstraints = for (_ <- 0 until rows) yield new ColumnConstraints(fieldSize)
    this.rowConstraints = for (_ <- 0 until columns) yield new RowConstraints(fieldSize)
    this.children = Array()
    this.gridLinesVisible = true

    addPawnsToBoard()
  }

  def addPawnsToBoard(): Unit = {
    for (y <- 0 until rows)
      for (x <- 0 until columns) {
        val pawn: Pawn = pawns.getOrElse(new Vector2d(x, y), null)
        pawn match {
          case _: Pawn => this.add(new PawnElement(x, y, pawn,
            checkMove: (Integer, Integer, Integer, Integer) => Unit), y, x)
          case _ => this.add(new BoardElement(x, y,
            checkMove: (Integer, Integer, Integer, Integer) => Unit), y, x)
        }
      }
  }
}