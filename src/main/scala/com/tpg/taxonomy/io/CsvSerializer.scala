package com.tpg.taxonomy.io

import com.tpg.taxonomy.{Node, TaxonomyTree}

import scala.collection.mutable.ListBuffer

case class CsvSerializer(tree: TaxonomyTree) extends Serialize {
  override def serialize: Seq[String] = {
    val lines = ListBuffer[String]()

    tree.rootNode foreach { node =>
      serializeChildren(node.children, lines)
    }

    lines
  }

  private def serializeChildren(children: Seq[Node], lines: ListBuffer[String]): Unit = {
    if (children.nonEmpty) {
      lines += children map { child => child.serialize } mkString ","

      val nextLevelChildren = new ListBuffer[Node]

      children foreach { node => node.children foreach { child => nextLevelChildren += child } }

      serializeChildren(nextLevelChildren, lines)
    }
  }
}
