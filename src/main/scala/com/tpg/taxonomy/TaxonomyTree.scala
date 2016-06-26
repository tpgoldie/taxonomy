package com.tpg.taxonomy


import scala.collection.mutable.ListBuffer

case class TaxonomyTree(rootNode: Option[Node]) {
  def findById(id: String): Seq[Node] = {
    val found = ListBuffer[Node]()

    rootNode map { node => findById(node.children, id, found) }

    found
  }

  private def findById(nodes: Seq[Node], id: Id, found: ListBuffer[Node]): Unit = {
    nodes foreach { node =>
      if (node.id == id) { found += node }

      findById(node.children, id, found)
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