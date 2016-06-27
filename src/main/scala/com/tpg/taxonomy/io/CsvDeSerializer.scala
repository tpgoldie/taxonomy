package com.tpg.taxonomy.io

import com.tpg.taxonomy.Tags.find
import com.tpg.taxonomy.io.CsvDeSerializer.RecordLength
import com.tpg.taxonomy.{Node, TaxonomyTree}

case class CsvDeSerializer(lines: Seq[String]) extends DeSerialize {
  override def deSerialize: TaxonomyTree = {
    val rootNode = Node(id = lines(0).split(",")(0))

    val tree = TaxonomyTree(Option(rootNode))

    lines foreach { line => buildLevel(tree, line) }

    tree
  }

  private def buildLevel(tree: TaxonomyTree, line: String) = {
    val tokens = line.split(",")

    val range = 0 until (tokens.length / RecordLength)

    println(line)

    range foreach { i => buildNode(tokens, i, tree) }
  }

  private def buildNode(tokens: Array[String], count: Int, tree: TaxonomyTree): Unit = {
    val index = count * RecordLength

    val parentId = tokens(index)
    val childId = tokens(index+1)
    val tag = find(tokens(index+2))
    val label = tokens(index+3)

    println(s"${parentId},${childId},${tokens(index+2)},${label}")

    val parent = tree.findById(parentId)

    parent foreach { p =>
      tag foreach { tag => {
          val child = Node(parent, tag, childId, label)
          p.addNode(child)

          println(s"added: ${child.parent.map(p => p.id).get},${child.id},${child.tag},${child.label}")
        }
      }
    }
  }
}

object CsvDeSerializer {
  val RecordLength = 4
}