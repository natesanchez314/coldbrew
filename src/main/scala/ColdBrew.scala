package com.natesanchez.coldbrew

import com.natesanchez.coldbrew.graphics.Screen

import java.awt.{Canvas, Dimension, Graphics}
import java.awt.image.{BufferStrategy, BufferedImage, DataBuffer, DataBufferInt}
import javax.swing
import javax.swing.{JFrame, WindowConstants}

object ColdBrew extends App with Runnable{
  val WIDTH: Int = 1280
  val HEIGHT: Int = 700
  val canvas: Canvas = new Canvas()
  val frame = new JFrame("Cold Brew")

  var size: Dimension = new Dimension(WIDTH, HEIGHT)
  canvas.setPreferredSize(size)
  canvas.setMaximumSize(size)
  canvas.setMinimumSize(size)
  var screen: Screen = new Screen(WIDTH, HEIGHT)
  var game: Game = new Game()
  var img: BufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB)
  var running: Boolean = false
  var thread: Thread = new Thread()
  var pixels: Array[Int] = img.getRaster.getDataBuffer.asInstanceOf[DataBufferInt].getData()

  frame.add("Center", canvas)
  frame.pack()
  frame.setTitle("Cold Brew")
  frame.setSize(WIDTH, HEIGHT)
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.setLocationRelativeTo(null)
  frame.setResizable(false)
  frame.setVisible(true)
  start()

  def start(): Unit = {
    if (running) return
    running = true
    thread = new Thread(this)
    thread.start()
  }

  def stop(): Unit = {
    if (!running) return
    running = false
    try {
      thread.join()
    } catch {
      case e: Exception => println("Exception " + e)
    }
  }

  override def run() = {
    var frames: Int = 0
    var unprocessedSeconds: Double = 0
    var previousTime: Long = 0
    var secondsPerUpdate: Double = 1 / 60.0
    var updateCount = 0
    var updated: Boolean = false
    while (running) {
      var currentTime: Long = System.nanoTime()
      var passedTime: Long = currentTime - previousTime
      previousTime = currentTime
      unprocessedSeconds += passedTime / 1000000000.0
      while (unprocessedSeconds > secondsPerUpdate) {
        update()
        unprocessedSeconds -= secondsPerUpdate
        updated = true
        updateCount += 1
        if (updateCount % 60 == 0) {
          println(frames + " FPS")
          previousTime += 1000
          frames = 0
        }
      }
      if (updated) {
        render()
        frames += 1
      }
      render()
      frames += 1
    }
  }

  def update(): Unit = {
    game.update()
  }

  def render(): Unit = {
    val bs: BufferStrategy = canvas.getBufferStrategy()
    if (bs == null) {
      canvas.createBufferStrategy(3)
      return
    }
    screen.render(game)
    for (i <- 0 until WIDTH * HEIGHT) {
      pixels(i) = screen.pixels(i)
    }

    var g: Graphics = bs.getDrawGraphics
    g.drawImage(img, 0, 0, WIDTH, HEIGHT, null)
    g.dispose()
    bs.show()
  }
}
