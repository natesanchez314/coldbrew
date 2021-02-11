package com.natesanchez.coldbrew
package engine

import com.natesanchez.coldbrew.engine.graphics.Sprite

import java.awt.image.DataBufferInt

class Renderer(gc: GameContainer) {

  private val pW: Int = gc.width
  private val pH: Int = gc.height
  private val pixels: Array[Int] = gc.window.image().getRaster.getDataBuffer.asInstanceOf[DataBufferInt].getData()

  def clear(): Unit = {
    for (i <- pixels.indices) {
      pixels(i) = 0
    }
  }

  def setPixel(x: Int, y: Int, value: Int): Unit = {
    if ((x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) {
      return
    }

    pixels(x + y * pW) = value
  }

  def drawImage(sprite: Sprite, offX: Int, offY: Int): Unit = {
    for (y <- 0 until sprite.height) {
      for (x <- 0 until sprite.width) {
        setPixel(x + offX, y + offY, sprite.pixels(x + y * sprite.width))
      }
    }
  }
}
