package com.natesanchez.coldbrew
package engine

import java.awt.{BorderLayout, Canvas, Dimension, Graphics}
import java.awt.image.{BufferStrategy, BufferedImage}
import javax.swing.{JFrame, WindowConstants}

class Window(gc: GameContainer) {
  private val _image: BufferedImage = new BufferedImage(gc.width, gc.height, BufferedImage.TYPE_INT_RGB)
  private val _canvas: Canvas = new Canvas()
  private val _size: Dimension = new Dimension((gc.width * gc.scale).asInstanceOf[Int], (gc.height * gc.scale).asInstanceOf[Int])
  _canvas.setPreferredSize(_size)
  _canvas.setMaximumSize(_size)
  _canvas.setMinimumSize(_size)

  private val _frame: JFrame = new JFrame(gc.title)
  _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  _frame.setLayout(new BorderLayout())
  _frame.add(_canvas, BorderLayout.CENTER)
  _frame.pack()
  _frame.setLocationRelativeTo(null)
  _frame.setResizable(false)
  _frame.setVisible(true)

  _canvas.createBufferStrategy(2)
  private val bs: BufferStrategy = _canvas.getBufferStrategy
  private val g: Graphics = bs.getDrawGraphics

  def update(): Unit = {
    g.drawImage(_image, 0, 0, _canvas.getWidth, _canvas.getHeight, null)
    bs.show()
  }

  def canvas(): Canvas = _canvas
  def image(): BufferedImage = _image
  def frame(): JFrame = _frame

}
