package com.natesanchez.coldbrew
package engine

import java.awt.event.{KeyEvent, KeyListener, MouseEvent, MouseListener, MouseMotionListener, MouseWheelEvent, MouseWheelListener}

class Input(private val _gc: GameContainer) extends KeyListener with MouseListener with MouseMotionListener with MouseWheelListener {

  private val _NUM_KEYS = 256
  private val _NUM_BUTTONS = 5

  private val _keys: Array[Boolean] = new Array[Boolean](_NUM_KEYS)
  private val _keysLast: Array[Boolean] = new Array[Boolean](_NUM_KEYS)

  private val _buttons: Array[Boolean] = new Array[Boolean](_NUM_BUTTONS)
  private val _buttonsLast: Array[Boolean] = new Array[Boolean](_NUM_BUTTONS)

  private var _mouseX: Int = 0
  private var _mouseY: Int = 0
  private var _scroll: Int = 0

  _gc.window.canvas().addKeyListener(this)
  _gc.window.canvas().addMouseMotionListener(this)
  _gc.window.canvas().addMouseWheelListener(this)
  _gc.window.canvas().addMouseListener(this)

  def update(): Unit = {
    _scroll = 0
    for (i <- 0 until _NUM_KEYS) {
      _keysLast(i) = _keys(i)
    }
    for (i <- 0 until _NUM_BUTTONS) {
      _buttonsLast(i) = _buttons(i)
    }
  }

  def isKey(keyCode: Int): Boolean = _keys(keyCode)
  def isKeyUp(keyCode: Int): Boolean = !_keys(keyCode) && _keysLast(keyCode)
  def isKeyDown(keyCode: Int): Boolean = _keys(keyCode) && !_keysLast(keyCode)

  def isButton(button: Int): Boolean = _buttons(button)
  def isButtonUp(button: Int): Boolean = !_buttons(button) && _buttonsLast(button)
  def isButtonDown(button: Int): Boolean = _buttons(button) && !_buttonsLast(button)


  override def keyTyped(e: KeyEvent): Unit = {}

  override def keyPressed(e: KeyEvent): Unit = _keys(e.getKeyCode) = true

  override def keyReleased(e: KeyEvent): Unit = _keys(e.getKeyCode) = false

  override def mouseClicked(e: MouseEvent): Unit = {}

  override def mousePressed(e: MouseEvent): Unit = _buttons(e.getButton) = true

  override def mouseReleased(e: MouseEvent): Unit = _buttons(e.getButton) = false

  override def mouseEntered(e: MouseEvent): Unit = {}

  override def mouseExited(e: MouseEvent): Unit = {}

  override def mouseDragged(e: MouseEvent): Unit = {
    _mouseX = (e.getX / _gc.scale).asInstanceOf[Int]
    _mouseY = (e.getY / _gc.scale).asInstanceOf[Int]
  }

  override def mouseMoved(e: MouseEvent): Unit = {
    _mouseX = (e.getX / _gc.scale).asInstanceOf[Int]
    _mouseY = (e.getY / _gc.scale).asInstanceOf[Int]
  }

  override def mouseWheelMoved(e: MouseWheelEvent): Unit = _scroll = e.getWheelRotation

  def mouseX: Int = _mouseX
  def mouseY: Int = _mouseY
  def scroll: Int = _scroll
}
