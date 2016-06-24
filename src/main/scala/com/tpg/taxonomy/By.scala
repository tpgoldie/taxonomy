package com.tpg.taxonomy

import scala.collection.mutable.ListBuffer

case class By(rootNode: Node) {
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

    find(id, rootNode.children, foundNodes)

    foundNodes
  }
}
