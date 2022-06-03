package jumper.map

import javafx.application.Platform
import javafx.scene.layout.GridPane
import jumper.gui.App

import java.util
import jumper.map.MapDirection.intToMapDirection

import java.util.{ArrayList, LinkedHashMap, Map}


class BattleField(var gridPane: GridPane, var observer: App) extends Runnable {

  this.whichPlayer = 1
  for (i <- 0 until 8) {
    val position1 = new Vector2d(0, i)
    pawnsOfP1.put(position1, new Pawn(true, position1))
    val position2 = new Vector2d(7, i)
    pawnsOfP2.put(position2, new Pawn(true, position2))
  }
  //mechanizmy ruchów
  //sprawdzanie czy ruch jest legalny tutaj - try cathc
  var mapHeight = 8
  var mapWeight = 8
  var someoneWon = false
  protected var pawnsOfP1 = new util.LinkedHashMap[Vector2d, Pawn]
  protected var pawnsOfP2 = new util.LinkedHashMap[Vector2d, Pawn]
  private var whichPlayer = 0
  private var whichPawn = null
  private var positions = new util.ArrayList[Vector2d]
  private var inMove = false
  private var inMoveType1 = false

  override def run(): Unit = {
    var winner = ""
    while ( {
      !someoneWon
    }) {
      whichPlayer match {
        case 1 =>
          Platform.runLater(() => {
            def foo() = {
              observer.moveVisual(this, gridPane, 1)
            }

            foo()
          })
          while ( {
            !inMove
          }) try Thread.sleep(100)
          catch {
            case e: InterruptedException =>
              e.printStackTrace()
          }
          this.setInMoveType1(false)
          positions.clear()
          Platform.runLater(() => {
            def foo() = {
              observer.inMoveVisual(this, gridPane, 1, whichPawn)
            }

            foo()
          })
          while ( {
            inMove
          }) try Thread.sleep(100)
          catch {
            case e: InterruptedException =>
              e.printStackTrace()
          }
          import scala.collection.JavaConversions._
          for (i <- positions) {
            this.pawnsOfP1.remove(whichPawn)
            this.pawnsOfP1.put(i, new Pawn(true, i))
            this.whichPawn = i
            Platform.runLater(() => {
              def foo() = {
                observer.mapVisual(this, gridPane)
              }

              foo()
            })
            try Thread.sleep(300)
            catch {
              case e: InterruptedException =>
                e.printStackTrace()
            }
          }
          Platform.runLater(() => {
            def foo() = {
              observer.setLabel("Black's move...")
            }

            foo()
          })
          whichPlayer = 2

        case _ =>
          Platform.runLater(() => {
            def foo() = {
              observer.moveVisual(this, gridPane, 2)
            }

            foo()
          })
          while ( {
            !inMove
          }) try Thread.sleep(100)
          catch {
            case e: InterruptedException =>
              e.printStackTrace()
          }
          this.setInMoveType1(false)
          positions.clear()
          Platform.runLater(() => {
            def foo() = {
              observer.inMoveVisual(this, gridPane, 2, whichPawn)
            }

            foo()
          })
          while ( {
            inMove
          }) try Thread.sleep(100)
          catch {
            case e: InterruptedException =>
              e.printStackTrace()
          }
          import scala.collection.JavaConversions._
          for (i <- positions) {
            this.pawnsOfP2.remove(whichPawn)
            this.pawnsOfP2.put(i, new Pawn(true, i))
            this.whichPawn = i
            Platform.runLater(() => {
              def foo() = {
                observer.mapVisual(this, gridPane)
              }

              foo()
            })
            try Thread.sleep(300)
            catch {
              case e: InterruptedException =>
                e.printStackTrace()
            }
          }
          Platform.runLater(() => {
            def foo() = {
              observer.setLabel("White's move...")
            }

            foo()
          })
          whichPlayer = 1

      }
      winner = checkIfSomeoneWon
    }
    val finalWinner = winner
    Platform.runLater(() => {
      def foo() = {
        observer.setLabel("The winner is " + finalWinner)
      }

      foo()
    })
    var winnerInt = 1
    if (winner == "black") winnerInt = 2
    this.winningAnimation(winnerInt)
  }

  def checkField(newPosition: Vector2d): Boolean = {
    if (newPosition.precedes(new Vector2d(0, 0)) || newPosition.follows(new Vector2d(7, 7))) {
      Platform.runLater(() => {
        def foo() = {
          observer.setLabel("Invalid position...")
        }

        foo()
      })
      return false
    }
    if (positions.size == 0) {
      for (i <- 0 until 4) {
        if (whichPawn.add(intToMapDirection(i).toUnitVector) == newPosition) {
          if (pawnsOfP1.get(newPosition) != null || pawnsOfP2.get(newPosition) != null) {
            Platform.runLater(() => {
              def foo() = {
                observer.setLabel("Invalid position...")
              }

              foo()
            })
            return false
          }
          Platform.runLater(() => {
            def foo() = {
              observer.setLabel("In move...")
            }

            foo()
          })
          this.addPosition(newPosition)
          this.setInMoveType1(true)
          return true
        }
      }
      for (i <- 0 until 4) {
        if (pawnsOfP1.get(whichPawn.add(intToMapDirection(i).toUnitVector)) != null || pawnsOfP2.get(whichPawn.add(intToMapDirection(i).toUnitVector)) != null) if (whichPawn.add(intToMapDirection(i).toUnitVector).add(intToMapDirection(i).toUnitVector) == newPosition) {
          this.addPosition(newPosition)
          Platform.runLater(() => {
            def foo() = {
              observer.setLabel("In move...")
            }

            foo()
          })
          return true
        }
      }
    }
    else if (!inMoveType1) for (i <- 0 until 4) {
      if (pawnsOfP1.get(positions.get(positions.size - 1).add(intToMapDirection(i).toUnitVector)) != null || pawnsOfP2.get(positions.get(positions.size - 1).add(intToMapDirection(i).toUnitVector)) != null) for (j <- 0 until 4) {
        if (positions.get(positions.size - 1).add(intToMapDirection(j).toUnitVector).add(intToMapDirection(j).toUnitVector) == newPosition) {
          this.addPosition(newPosition)
          Platform.runLater(() => {
            def foo() = {
              observer.setLabel("In move...")
            }

            foo()
          })
          return true
        }
      }
    }
    Platform.runLater(() => {
      def foo() = {
        observer.setLabel("Invalid position...")
      }

      foo()
    })
    false
  }

  def setWhichPawn(whichPawn: Vector2d): Unit = {
    this.whichPawn = whichPawn
  }

  def setPositions(positions: util.ArrayList[Vector2d]): Unit = {
    this.positions = positions
  }

  def getPositions: util.ArrayList[Vector2d] = positions

  def addPosition(pos: Vector2d): Unit = {
    this.positions.add(pos)
  }

  def setInMove(inMove: Boolean): Unit = {
    this.inMove = inMove
  }

  def setSomeoneWon(): Unit = {
    this.someoneWon = true
  }

  def getPawnsOfP1: util.Map[Vector2d, Pawn] = pawnsOfP1

  def getPawnsOfP2: util.Map[Vector2d, Pawn] = pawnsOfP2

  def setInMoveType1(x: Boolean): Unit = {
    this.inMoveType1 = x
  }

  def checkIfSomeoneWon: String = {
    var winner = "white"
    for (i <- 0 until 8) {
      if (pawnsOfP1.get(new Vector2d(7, i)) == null) winner = ""
    }
    if (!(winner == "")) {
      this.someoneWon = true
      return winner
    }
    winner = "black"
    for (i <- 0 until 8) {
      if (pawnsOfP2.get(new Vector2d(0, i)) == null) winner = ""
    }
    if (!(winner == "")) {
      this.someoneWon = true
      return winner
    }
    ""
  }

  def winningAnimation(winner: Int): Unit = {
    var i = 0
    while ( {
      i < 16
    }) {
      val finalI = i
      Platform.runLater(() => {
        def foo() = {
          observer.winningVisual(this, gridPane, finalI, winner)
        }

        foo()
      })
      try Thread.sleep(150)
      catch {
        case e: InterruptedException =>
          e.printStackTrace()
      }

      i = i + 2
    }
  }
}
