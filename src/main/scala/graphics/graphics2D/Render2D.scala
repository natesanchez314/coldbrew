package com.natesanchez.coldbrew
package graphics.graphics2D

2D

class Render2D(val width: Int, val height: Int) {
  var pixels: Array[Int] = new Array[Int](width * height)

  def draw(render: Render2D, xOffset: Int, yOffset: Int): Unit = {
    for (y <- 0 until render.height) {
      var yPix: Int = y + yOffset
      for (x <- 0 until render.width) {
        var xPix: Int = x + xOffset
        try {
          var alpha: Int = render.pixels(x + y * render.width)
          if (alpha > 0) {
            pixels(xPix + yPix * width) = render.pixels(x + y * render.width)
          }
        } catch {
          case e: Exception => {}
        }
      }
    }
  }
}
