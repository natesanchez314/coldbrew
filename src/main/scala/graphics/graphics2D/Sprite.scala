package com.natesanchez.coldbrew
package graphics.graphics2D

class Sprite(
              val SIZE: Int,
              var x: Int,
              var y: Int,
              var spritSheet: SpriteSheet
            ) {
  var pixels = new Array[Int](SIZE * SIZE)
  x *= SIZE
  y *= SIZE
  load()

  def load(): Unit ={
    for (yy <- 0 until SIZE) {
      for (xx <- 0 until SIZE) {
        pixels(x + y * SIZE) = spritSheet.pixels((x + xx) + (y + yy) * spritSheet.size)
      }
    }
  }
}
