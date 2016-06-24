package com.tpg.taxonomy

import scala.collection.mutable.ListBuffer

case class Id(value: String)

object Id {
  implicit def toId(value: String) = Id(value)
}

case class Node(tag: Tag, val id: Id) {
  private val childNodes: ListBuffer[Node] = ListBuffer()

  val children: Seq[Node] = childNodes

  def addNode(node: Node) = { childNodes += node }

  def descendants: Seq[Node] = {
    def descendants(nodes: Seq[Node], foundNodes: ListBuffer[Node]): Unit = {
      if (!nodes.isEmpty) {
        nodes foreach { node => {
            foundNodes += node
            descendants(node.children, foundNodes)
          }
        }
      }
    }

    val existingNodes = ListBuffer[Node]()
    descendants(children, existingNodes)
    existingNodes
  }

  override def toString = s"${id.value}"
}

object Node {
  implicit class by(value: Id) {
    def id: Id = value
  }

  implicit class find(node: Node) {
    def by(value: by): Seq[Node] = {
      By(node).id(value.id)
    }
  }
}
