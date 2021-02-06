package com.natesanchez.coldbrew
package math

class Vector2(var x: Float = 0f, var y: Float = 0f) {

  def normalize() = {
    //todo
  }

  def distanceTo(): Float = {
    //todo
    return 0f
  }

  def +(v: Vector2): Vector2 = {
    return new Vector2(this.x + v.x, this.y + v.y)
  }

  def -(v: Vector2): Vector2 = {
    return new Vector2(this.x - v.x, this.y - v.y)
  }

}
