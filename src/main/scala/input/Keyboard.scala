package com.natesanchez.coldbrew
package input

import java.awt.event.{KeyEvent, KeyListener}

class Keyboard(
                var keys: Array[Boolean] = new Array[Boolean](120),
                var up: Boolean,
                var down: Boolean
              )
  extends KeyListener{

  def update(): Unit = {
    //up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]
  }

  override def keyTyped(e: KeyEvent): Unit = ???

  override def keyPressed(e: KeyEvent): Unit = ???

  override def keyReleased(e: KeyEvent): Unit = ???
}
