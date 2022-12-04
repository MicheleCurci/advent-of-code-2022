package day_4

import utils.{Output, ReadFile}

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

  def buildRanges(line: String): Seq[Range.Inclusive] = {
    val ranges = line.split(',')
    val r1 = ranges(0).split('-')
    val r2 = ranges(1).split('-')

    Seq(
      Range.inclusive(r1(0).toInt, r1(1).toInt),
      Range.inclusive(r2(0).toInt, r2(1).toInt)
    )
  }

  def includes(ranges: Seq[Range.Inclusive]): Boolean = {
    val r1 = ranges.head
    val r2 = ranges.last

    (r1.start <= r2.start && r1.last >= r2.last) || (r1.start >= r2.start && r1.last <= r2.last)
  }

  def overlaps(ranges: Seq[Range.Inclusive]): Boolean = {
    val r1 = ranges.head
    val r2 = ranges.last

    !(r1.last < r2.start || r1.start > r2.last)
  }

  Output.printSolution(secondSolution, day, 2)
}
