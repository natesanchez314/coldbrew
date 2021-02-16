package com.natesanchez.coldbrew
package game

import engine.graphics._
import engine.{AbstractGame, GameContainer, Renderer}

import java.awt.event.KeyEvent

object GameManager extends AbstractGame{

  var image: Sprite = new Sprite("res/characters_set1shadow.png", 60, 60)

  override def update(gc: GameContainer, delta: Float): Unit = {
    if (gc.input.isKeyDown(KeyEvent.VK_W)) {
      println("W")
    }
  }

  override def render(gc: GameContainer, r: Renderer): Unit = {
    r.drawSprite(image, gc.input.mouseX, gc.input.mouseY, 5, 5)
  }

  def main(args: Array[String]): Unit = {
    val gc = new GameContainer(this)
    gc.start()
  }
}
