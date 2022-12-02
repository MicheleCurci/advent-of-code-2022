package utils

import scala.io.Source

object ReadFile {

  def lineByLine(filename: String): List[String] = {
    val source = Source.fromFile(filename)
    source.getLines().toList
  }

}