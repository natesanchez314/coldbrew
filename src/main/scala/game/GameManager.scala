package com.natesanchez.coldbrew
package game

import engine.{AbstractGame, GameContainer, Renderer}

import com.natesanchez.coldbrew.engine.graphics.Sprite

import java.awt.event.KeyEvent

object GameManager extends AbstractGame{

  var image: Sprite = new Sprite("res/characters_set1shadow.png")

  override def update(gc: GameContainer, delta: Float): Unit = {
    if (gc.input.isKeyDown(KeyEvent.VK_W)) {
      println("W")
    }
  }

  override def render(gc: GameContainer, r: Renderer): Unit = {
    r.drawImage(image, gc.input.mouseX, gc.input.mouseY)
  }

  def main(args: Array[String]): Unit = {
    val gc = new GameContainer(this)
    gc.start()
  }
}
