package com.tpg.taxonomy

import com.tpg.taxonomy.Id._
import com.tpg.taxonomy.Node._

case class Tag(name: String, translations: Seq[Translation])

case class TaxonomyTree(rootNode: Option[Node]) {
  def findById(value: String): Seq[Node] = {
    val found = rootNode map { node => find(node) by toId(value) }
    found.getOrElse(Seq())
  }
}
