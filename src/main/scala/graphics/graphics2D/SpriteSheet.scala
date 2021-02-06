package com.natesanchez.coldbrew
package graphics.graphics2D

import java.io.{File, IOException}
import javax.imageio.ImageIO

class SpriteSheet(var path: String, var size: Int) {
  var pixels: Array[Int] = new Array[Int](size * size)
  load()

  def load(): Unit = {
    try {
      var img = ImageIO.read(new File(path))
      var w = img.getWidth()
      var h = img.getHeight()
      img.getRGB(0, 0, w, h, pixels, 0, w)
    } catch {
      case e: IOException => println("Exception: " + e)
    }
  }
}
