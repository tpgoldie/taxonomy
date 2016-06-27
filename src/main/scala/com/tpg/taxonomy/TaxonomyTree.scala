package com.tpg.taxonomy


import scala.collection.mutable.ListBuffer

case class TaxonomyTree(rootNode: Option[Node]) {
  def findById(id: String): Option[Node] = {
    val found = ListBuffer[Node]()

    rootNode foreach { node => if (node.id == Id(id)) { found += node } else { findById(node.children, id, found) } }

    found.headOption
  }

  private def findById(nodes: Seq[Node], id: Id, found: ListBuffer[Node]): Unit = {
    nodes foreach { node =>
      if (node.id == id) { found += node }

      if (found.isEmpty) {
        findById(node.children, id, found)
      }
    }
  }

  def findByTag(tag: Tag): Seq[Node] = {
    val found = ListBuffer[Node]()

    rootNode map { node => findByTag(node.children, tag, found) }

    found
  }

  private def findByTag(nodes: Seq[Node], tag: Tag, found: ListBuffer[Node]): Unit = {
    nodes foreach { node =>
      if (node.tag == tag) { found += node }

      findByTag(node.children, tag, found)
    }
  }
}