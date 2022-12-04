package day_4

import utils.{Output, ReadFile}

case class RangePair(first: IntRange, second: IntRange)
case class IntRange(start: Int, last: Int)

object Main extends App {

  val day = 4
  val input_filepath = s"src/main/scala/input_files/day_$day.txt"

  val lines: Seq[String] = ReadFile.lineByLine(input_filepath)

  // part 1
  val firstSolution = lines
    .map(buildRanges)
    .count(includes)

  Output.printSolution(firstSolution, day, 1)

  // part 2
  val secondSolution = lines
    .map(buildRanges)
    .count(overlaps)

  Output.printSolution(secondSolution, day, 2)

  def buildRanges(line: String): RangePair = {
    val ranges = line.split(',')
    val r1 = ranges(0).split('-')
    val r2 = ranges(1).split('-')

    RangePair(
      IntRange(r1(0).toInt, r1(1).toInt),
      IntRange(r2(0).toInt, r2(1).toInt)
    )
  }

  def includes(rangePair: RangePair): Boolean = {
    val r1 = rangePair.first
    val r2 = rangePair.second

    (r1.start <= r2.start && r1.last >= r2.last) || (r1.start >= r2.start && r1.last <= r2.last)
  }

  def overlaps(ranges: RangePair): Boolean = {
    val r1 = ranges.first
    val r2 = ranges.second

    r1.last >= r2.start && r1.start <= r2.last
  }

}
