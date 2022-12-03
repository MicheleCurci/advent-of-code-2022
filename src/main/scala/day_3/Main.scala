package day_3

import utils.{Output, ReadFile}

object Main extends App {

  val day = 3
  val input_filepath = s"src/main/scala/input_files/day_$day.txt"

  val lines: Seq[String] = ReadFile.lineByLine(input_filepath)

  // part 1
  val firstSolution = lines
    .map(splitLineInTwoParts)
    .map(getUniqueCommonCharFromLines)
    .map(getPriority)
    .sum

  Output.printSolution(firstSolution, day, 1)

  // part 2
  val secondSolution = lines
    .grouped(3)
    .map(getUniqueCommonCharFromLines)
    .map(getPriority)
    .sum

  Output.printSolution(secondSolution, day, 2)

  def splitLineInTwoParts(line: String): Seq[String] = {
    val halfLength = line.length / 2
    Seq(line.take(halfLength), line.takeRight(halfLength))
  }
  
  def getUniqueCommonCharFromLines(lines: Seq[String]): Char = {
    lines
      .map(_.toSet)
      .reduce((s1, s2) => s1.intersect(s2))
      .head
  }

  def getPriority(c: Char): Int = {
    if (c.isLower) c.toInt - 96 else c.toInt - 38
  }
}