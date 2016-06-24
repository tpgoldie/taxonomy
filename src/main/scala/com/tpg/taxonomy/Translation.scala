package com.tpg.taxonomy

sealed abstract class Translation(lang: String, value: String)

case class en_GB(value: String) extends Translation("en_GB", value)

case class fr_FR(value: String) extends Translation("fr_FR", value)

case class it_IT(value: String) extends Translation("it_IT", value)

case class es_ES(value: String) extends Translation("es_ES", value)