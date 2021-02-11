package com.natesanchez.coldbrew

import engine.{AbstractGame, GameContainer, Renderer}

import com.natesanchez.coldbrew.game.GameManager

object ColdBrew extends AbstractGame {

  def main(args: Array[String]) {
    //val game = new GameContainer(new GameManager)
    //game.start()
  }
  override def update(gc: GameContainer, delta: Float): Unit = ???

  override def render(gc: GameContainer, r: Renderer): Unit = ???
}
