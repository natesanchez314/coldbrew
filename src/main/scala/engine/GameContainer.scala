package com.natesanchez.coldbrew
package engine

import java.awt.event.KeyEvent

class GameContainer(game: AbstractGame) extends Runnable {

  private var _thread: Thread = null
  private var _window: Window = null
  private var _renderer: Renderer = null
  private var _running: Boolean = false
  private val _UPDATE_CAP: Double = 1.0 / 60.0
  private var _input: Input = null

  var width: Int = 800
  var height: Int = 600
  var scale: Float = 2f
  var title: String = "ColdBrew Engine v1,0"

  def start(): Unit = {
    _window = new Window(this)
    _renderer = new Renderer(this)
    _input = new Input(this)

    _thread = new Thread(this)
    _thread.run()
  }

  def stop(): Unit = {

  }

  def run(): Unit = {
    _running = true

    var render: Boolean = false
    var firstTime: Double = 0
    var lastTime: Double = System.nanoTime() / 1000000000.0
    var passedTime: Double = 0
    var unprocessedTime: Double = 0

    var frameTime: Double = 0
    var frames: Int = 0
    var fps: Int = 0

    while (_running) {
      render = false
      firstTime = System.nanoTime() / 1000000000.0
      passedTime = firstTime - lastTime
      lastTime = firstTime

      unprocessedTime += passedTime
      frameTime += passedTime

      while (unprocessedTime >= _UPDATE_CAP) {
        unprocessedTime -= _UPDATE_CAP
        render = true

        game.update(this, _UPDATE_CAP.asInstanceOf[Float])

        if (frameTime >= 1.0) {
          frameTime = 0
          fps = frames
          frames = 0
          println("FPS: " + fps)
        }
      }

      if (render) {
        _renderer.clear()
        game.render(this, _renderer)
        _window.update()
        frames += 1
      } else {
        try {
          Thread.sleep(1)
        } catch {
          case e: InterruptedException => print("Exception" + e)
        }
      }
    }
    dispose()
  }

  private def dispose(): Unit = {
    //todo
  }

  def window: Window = _window
  def input: Input =_input
}
