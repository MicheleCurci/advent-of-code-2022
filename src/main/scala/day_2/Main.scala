package day_2

import utils.{Output, ReadFile}

object Main extends App {

  val day = 2
  val input_filepath = s"src/main/scala/input_files/day_$day.txt"

  val rounds: Seq[String] = ReadFile.lineByLine(input_filepath)

  /**
   * encoding:
   * 1 = rock
   * 2 = paper
   * 3 = scissors
   */
  val movesMapping = Map("A" -> 1, "B" -> 2, "C" -> 3, "X" -> 1, "Y" -> 2, "Z" -> 3)

  // part 1
  val firstSolution = rounds.map(changeEncodingMoves).map(_.split(' ')).map(calcRoundScore).sum
  Output.printSolution(firstSolution, day, 1)

  /**
   * example: replace "A Y" string with "1 2" 
   */
  def changeEncodingMoves(roundCharsEncoding: String): String = {
    val enemyMove = roundCharsEncoding.split(' ')(0)
    val myMove = roundCharsEncoding.split(' ')(1)

    s"${movesMapping(enemyMove)} ${movesMapping(myMove)}"
  }

  def calcRoundScore(round: Array[String]): Int = {

    val enemyMove = round(0).toInt
    val myMove = round(1).toInt

    /**
     * points:
     * win = 6
     * draw = 3
     * lose = 0
     */
    val roundResult = (enemyMove, myMove) match {
      case (em: Int, mm: Int) if em == mm => 3
      case (em: Int, mm: Int) if em == 1 && mm == 3 => 0
      case (em: Int, mm: Int) if em == 3 && mm == 1 => 6
      case (em: Int, mm: Int) if em < mm => 6
      case _ => 0
    }

    myMove + roundResult
  }

  // part 2
  val secondSolution = rounds.map(changeEncodingMoves).map(_.split(' ')).map(replaceMove).map(calcRoundScore).sum
  Output.printSolution(secondSolution, day, 2)

  def replaceMove(round: Array[String]): Array[String] = {

    val enemyMove = round(0)
    val myMove = round(1)

    myMove match {
      case "1" => setLosingMove(enemyMove)
      case "2" => Array(enemyMove, enemyMove)
      case "3" => setWinningMove(enemyMove)
    }

  }

  def setLosingMove(em: String): Array[String] = {
    em match {
      case "1" => Array(em, "3")
      case "2" => Array(em, "1")
      case "3" => Array(em, "2")
    }
  }

  def setWinningMove(em: String): Array[String] = {
    em match {
      case "1" => Array(em, "2")
      case "2" => Array(em, "3")
      case "3" => Array(em, "1")
    }
  }


}
