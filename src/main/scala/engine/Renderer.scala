package com.natesanchez.coldbrew
package engine

import engine.graphics.{Font, Sprite, SpriteSheet}

import java.awt.image.DataBufferInt

class Renderer(gc: GameContainer) {

  private val pW: Int = gc.width
  private val pH: Int = gc.height
  private val pixels: Array[Int] = gc.window.image().getRaster.getDataBuffer.asInstanceOf[DataBufferInt].getData()
  private val font: Font = new Font("res/fonts/standard.png")

  def clear(): Unit = {
    // Reset our pixels
    for (i <- pixels.indices) {
      pixels(i) = 0
    }
  }

  def setPixel(x: Int, y: Int, value: Int): Unit = {
    // Set our pixels to the proper color
    if ((x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) {
      return
    }
    pixels(x + y * pW) = value
  }

  def drawImage(sprite: SpriteSheet, offX: Int, offY: Int): Unit = {
    // Don't render
    if (offX < -sprite.width || offX >= pW) return
    if (offY < -sprite.height || offY > pH) return
    var newX = 0
    var newY = 0
    var newWidth = sprite.width
    var newHeight = sprite.height
    // Clipping
    if (offX < 0) newX -= offX
    if (offY < 0) newY -= offY
    if (newWidth + offX > pW) newWidth -= newWidth + offX - pW
    if (newHeight + offY > pH) newHeight -= newHeight + offY - pH
    // Render to the screen
    for (y <- newY until newHeight) {
      for (x <- newX until newWidth) {
        setPixel(x + offX, y + offY, sprite.pixels(x + y * sprite.width))
      }
    }
  }

  def drawText(text: String, offX: Int, offY: Int, color: Int): Unit = {
    var offset = 0
    for (i <- 0 until text.length) {
      val unicode: Int = text.codePointAt(i) - 32
      for (y <- 0 until font.fontImage().height) {
        for (x <- 0 until font.widths()(unicode)) {
          if (font.fontImage().pixels((x + font.offsets()(unicode)) + y * font.fontImage().width) == 0xffffffff) {
            setPixel(x + offX + offset, y + offY, color)
          }
        }
      }
      offset += font.widths()(unicode)
    }
  }

  def drawSprite(sprite: Sprite, offX: Int, offY: Int, tileX: Int, tileY: Int): Unit = {
    if (offX < -sprite.tileWidth || offX >= pW) return
    if (offY < -sprite.tileHeight || offY > pH) return
    var newX = 0
    var newY = 0
    var newWidth = sprite.tileWidth
    var newHeight = sprite.tileHeight
    // Clipping
    if (offX < 0) newX -= offX
    if (offY < 0) newY -= offY
    if (newWidth + offX > pW) newWidth -= newWidth + offX - pW
    if (newHeight + offY > pH) newHeight -= newHeight + offY - pH
    // Render to the screen
    for (y <- newY until newHeight) {
      for (x <- newX until newWidth) {
        setPixel(x + offX, y + offY, sprite.pixels((x + tileX * sprite.tileWidth) + (y + tileY * sprite.tileHeight) * sprite.width))
      }
    }
  }
}
