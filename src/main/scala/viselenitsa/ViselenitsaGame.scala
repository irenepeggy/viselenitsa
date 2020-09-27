package viselenitsa

import java.time.LocalDateTime
import scala.io.Source
import scala.util.Random
import scala.io.StdIn

class ViselenitsaGame(puzzledWord: String) {
  def this() = this(ViselenitsaGame.puzzleWord)

  val possibleMistakesNum = puzzledWord.length
  var state = ViselenitsaGameState(mistakesNum = 0, guessedWord = "*" * puzzledWord.length)


  final def play(): Unit = {
    println("******HANGMAN*******\n")

    while (state.mistakesNum != possibleMistakesNum && state.guessedWord != puzzledWord) {
      println(s"The word: ${state.guessedWord} \nGuess a letter: ")
      val userGuess = StdIn.readChar()
      makeMove(userGuess)
    }

    if (state.mistakesNum == possibleMistakesNum) {
      println("You lost!")
    } else if (state.guessedWord == puzzledWord) {
      println("You won!")
    }
  }

  private def makeMove(userGuess: Char): Unit = {
    val newState = getNewGameState(userGuess)
    if (newState.mistakesNum > state.mistakesNum) {
      println(s"Missed, mistake ${newState.mistakesNum} out of $possibleMistakesNum.\n")
    } else {
      println("Hit!\n")
    }
    state = newState
  }

  private def getNewGameState(userGuess: Char): ViselenitsaGameState = {
    val guessedIndices: Set[Int] = puzzledWord.zipWithIndex.collect {
      case (c, index) if c == userGuess => index
    }.toSet

    if (guessedIndices.isEmpty) {
      ViselenitsaGameState(state.guessedWord, state.mistakesNum + 1)
    } else {
      val newWordState = state.guessedWord.zipWithIndex.map {
        case (c, index) => if (guessedIndices.contains(index)) userGuess else c
      }.mkString("")
      ViselenitsaGameState(newWordState, state.mistakesNum)
    }
  }
}

object ViselenitsaGame {
  def puzzleWord(): String = {
    val words: List[String] = Source.fromResource("dict.txt").getLines.toList
    val random = new Random(LocalDateTime.now().getNano)
    words(random.nextInt(words.length))
  }
}
case class ViselenitsaGameState (
                                guessedWord: String,
                                mistakesNum: Int
                                )
