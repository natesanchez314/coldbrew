package com.natesanchez.coldbrew
package graphics

import com.natesanchez.coldbrew.graphics.graphics2D.Render2D
import com.natesanchez.coldbrew.graphics.graphics3D.Render3D

import scala.util.Random

class Screen(
              width: Int,
              height: Int
            ) extends Render2D(width, height) {
  var render = new Render3D(width, height)
  val test: Render2D = new Render2D(256, 256)
  var random: Random = new Random()
  for (i <- 0 until 256 * 256) {
    test.pixels(i) = random.nextInt()
  }

  def clear(): Unit ={
    for (i <- 0 until width * height) {
      pixels(i) = 0
    }
  }

  def render(game: Game): Unit = {
    clear()
    for (i <- 0 until 50) {
      var anim = ((Math.sin(game.time + i * 2) % 1000.0 / 100) * 100).asInstanceOf[Int]
      var anim2 = ((Math.cos(game.time + i * 2) % 1000.0 / 100) * 100).asInstanceOf[Int]
    }
    render.floor()
    draw(render, 0, 0)
  }
}
