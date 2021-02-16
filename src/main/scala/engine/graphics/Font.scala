package com.natesanchez.coldbrew
package engine.graphics

class Font(path: String) {

  //val STANDARD: Font = new Font("fonts/standard.png")
  private val _fontImage: SpriteSheet = new SpriteSheet(path)
  private val _offsets: Array[Int] = new Array[Int](59)
  private val _widths: Array[Int] = new Array[Int](59)

  var unicode = 0
  for (i <- 0 until _fontImage.width) {
    if (_fontImage.pixels(i) == 0xff0000ff) {
      _offsets(unicode) = i
    }
    if (_fontImage.pixels(i) == 0xffffff00) {
      _widths(unicode) = i - _offsets(unicode)
      unicode += 1
    }
  }

  def fontImage(): SpriteSheet = _fontImage
  def widths(): Array[Int] = _widths
  def offsets(): Array[Int] = _offsets
}
