package com.tpg.taxonomy

import scala.collection.mutable.ListBuffer

case class By(tree: TaxonomyTree) {
  def id(id: Id): Seq[Node] = {
    def find(id: Id, nodes: Seq[Node], foundNodes: ListBuffer[Node]): Unit = {
      nodes foreach { node =>
        if (node.children.isEmpty) {
          if (node.id == id) { foundNodes += node }
        }
        else {
          find(id, node.children, foundNodes)
        }
      }
    }

    val foundNodes = ListBuffer[Node]()

    tree.rootNode foreach { node => find(id, node.children, foundNodes)}

    foundNodes
  }
}
