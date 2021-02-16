package com.natesanchez.coldbrew
package engine.graphics

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.{File, IOException}
import javax.imageio.ImageIO

class SpriteSheet(path: String) {

  var image: BufferedImage = null

  try {
    image = ImageIO.read(new File(path))
  } catch {
    case e: IOException => print(e)
  }

  val width: Int = image.getWidth
  val height: Int = image.getHeight
  var pixels: Array[Int] = image.getRGB(0, 0, width, height, null, 0, width)

  image.flush()

}
