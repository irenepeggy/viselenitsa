Viselenitsa (Hangman): brief description
==============================

This is a pretty plain console implementation of game [**Viselinitsa (Hangman)**](https://en.wikipedia.org/wiki/Hangman_(game)).
All the tests are written with [ScalaTest](https://www.scalatest.org/). Static code analysis performed with [Scalastyle](http://www.scalastyle.org/). 
###Usage

0. You should have sbt installed on your machine
1. Run game with command:
`sbt compile && sbt run`
2. The program will puzzle a word for you. 
3. Try to figure out what the word is by guessing its letters one by one. Notice, the number of mistakes is limited by the word length!

###Test

To run tests use console command `sbt test`. Source code of the tests is in *viselenitsa/src/test/scala/viselenitsa/ViselenitcaSpec.scala* file.

###Codestyle

Codestyle could be checked via console command `sbt scalastyle`.

