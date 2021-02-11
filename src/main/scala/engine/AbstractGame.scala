package com.natesanchez.coldbrew
package engine

abstract class AbstractGame {
  def update(gc: GameContainer, delta: Float)
  def render(gc: GameContainer, r: Renderer)
}
