package com.tpg.taxonomy

import com.tpg.taxonomy.Id._
import com.tpg.taxonomy.TaxonomyTree._

case class Tag(name: String, translations: Seq[Translation])

case class TaxonomyTree(rootNode: Option[Node]) {
  def findById(value: String): Seq[Node] = {
    val found = rootNode map { node => find(this) by toId(value) }
    found.getOrElse(Seq())
  }
}

object TaxonomyTree {
  implicit class by(value: Id) {
    def id: Id = value
  }

  implicit class find(tree: TaxonomyTree) {
    def by(value: by): Seq[Node] = {
      By(tree).id(value.id)
    }
  }
}