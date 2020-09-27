package viselenitsa

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.PrivateMethodTester._
import org.scalatest.matchers.should.Matchers

class ViselenitsaSpec extends AnyFlatSpec with Matchers {

  "getNewGameState" should "work like this" in {
    val game = new ViselenitsaGame("example")
    val getNewGameState = PrivateMethod[(String, Int)]('getNewGameState)

    game invokePrivate getNewGameState('a') shouldBe ViselenitsaGameState("**a****", 0)
    game invokePrivate getNewGameState('e') shouldBe ViselenitsaGameState("e*****e", 0)
    game invokePrivate getNewGameState('o') shouldBe ViselenitsaGameState("*******", 1)

  }

  "makeMove" should "update game state when new letter was guessed" in {
    val game = new ViselenitsaGame("instance")
    val makeMove = PrivateMethod[Unit]('makeMove)

    assert(game.state === ViselenitsaGameState("********", 0))
    game invokePrivate makeMove('n')
    assert(game.state === ViselenitsaGameState("*n***n**", 0) )
  }

  "makeMove" should "update game state when a mistake was done" in {
    val game = new ViselenitsaGame("instance")
    val makeMove = PrivateMethod[Unit]('makeMove)

    assert(game.state === ViselenitsaGameState("********", 0))
    game invokePrivate makeMove('o')
    assert(game.state === ViselenitsaGameState("********", 1) )
  }

  "makeMove" should "leave previous state if it hasn't been changed" in {
    val game = new ViselenitsaGame("instance")
    val makeMove = PrivateMethod[Unit]('makeMove)

    game invokePrivate makeMove('i')
    val previosState = game.state
    game invokePrivate makeMove('i')
    assert(game.state === previosState )
  }

}
