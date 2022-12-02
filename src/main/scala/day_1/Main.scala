package day_1

import utils.{Output, ReadFile}

object Main extends App {

  val day = 1
  val input_filepath = s"src/main/scala/input_files/day_$day.txt"

  /**
   * 'lines' contains strings (representing integers) and newlines
   */
  val lines = ReadFile.lineByLine(input_filepath)

  val listOfGroupedSums = lines.foldRight[List[Int]](List(0)){
    (value, acc) => value match {
      case "" => 0 :: acc
      case value => (value.toInt + acc.head) :: acc.tail
    }
  }

  val firstSolution = listOfGroupedSums.max
  val secondSolution = listOfGroupedSums.sorted.reverse.take(3).sum

  Output.printSolution(firstSolution, day, 1)
  Output.printSolution(secondSolution, day, 2)
}
