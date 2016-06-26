package com.tpg.taxonomy

import scala.collection.mutable.ListBuffer

case class Id(value: String) {
  override def toString = value
}

object Id {
  implicit def toId(value: String): Id = Id(value)
}

case class Tag(name: String, translations: Seq[Translation])

case class Node(parent: Option[Node] = None, tag: Tag = Tag("", Seq.empty), id: Id = IdGenerator.generate, label: String = "") {
  private val childNodes: ListBuffer[Node] = ListBuffer()

  val children: Seq[Node] = childNodes

  def addNode(node: Node) = { childNodes += node }

  def descendants: Seq[Node] = {
    def descendants(nodes: Seq[Node], foundNodes: ListBuffer[Node]): Unit = {
      if (nodes.nonEmpty) {
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

  override def toString = s"${id.value}:${label}"
}


object Node {
  def apply(parentNode: Node, tag: Tag, label: String): Node = {
    val node = Node(Option(parentNode), tag, IdGenerator.generate, label)

    parentNode.addNode(node)

    node
  }
}