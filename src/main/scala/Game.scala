package com.natesanchez.coldbrew

class Game(var time: Int = 0) {
  def update(): Unit = {
    time += 1
  }
}
