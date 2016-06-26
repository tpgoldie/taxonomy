package com.tpg.taxonomy.io

import com.tpg.taxonomy.{Node, TaxonomyTree}

import scala.collection.mutable.ListBuffer

case class CsvSerializer(tree: TaxonomyTree) extends Serialize {
  override def serialize: Seq[String] = {
    val lines = ListBuffer[String]()

    tree.rootNode foreach { node => node.children foreach { child => serializeNode(child, lines) } }

    lines
  }

  private def serializeNode(node: Node, lines: ListBuffer[String]): Unit = {
    val line = node.parent map { parent => serializeNode(parent) } getOrElse("")

    lines += s"${line},${serializeNode(node)}"

    node.children foreach { child => serializeNode(child, lines) }
  }

  def serializeNode(node: Node): String = s"${node.tag.name},${node.label}"
}
