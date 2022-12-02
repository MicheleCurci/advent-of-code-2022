package utils

object Output {

  def printSolution(solution: Int, day: Int, part: Int): Unit = {
    println(
      s"""# DAY $day [Part $part]
         |Solution: $solution
         |""".stripMargin)
  }

}
