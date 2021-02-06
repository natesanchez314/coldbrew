package com.natesanchez.coldbrew
package graphics.graphics3D

import graphics.graphics2D.Render2D

class Render3D(width: Int, height: Int) extends Render2D(width, height) {
  var time: Double = 0
  def floor(): Unit = {
    for (y <- 0 until height) {
      var ceiling: Double = (y - height / 2.0) / height
      if (ceiling < 0) {
        ceiling = -ceiling
      }
      var z: Double = 8 / ceiling
      time += 0.0005
      for (x <- 0 until width) {
        var depth: Double = (x - width / 2.0) / height
        depth *= z
        var xx = depth
        var yy = z + time
        var xPix = xx.asInstanceOf[Int]
        var yPix = yy.asInstanceOf[Int]
        pixels(x + y * width) = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 8
      }
    }
  }
}
