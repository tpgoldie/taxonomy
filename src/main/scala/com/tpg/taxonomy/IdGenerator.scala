package com.tpg.taxonomy

object IdGenerator {
  var value = 1

  def generate: String = {
    value += 1

    value.toString
  }
}
