package com.tpg.taxonomy

case class NodeBuilder(tagQueryService: TagQueryService, line: String) {
//  val rootNode = BranchNode(Tag("", Seq()), "")
//
//  val nodes: Seq[Node] = buildNodes(line.split(","), rootNode, Seq())
//
//  private def buildNodes(tokens: Array[String], tree: TaxonomyTree, parentNode: BranchNode, nodes: Seq[Option[Node]]): Seq[Node] = {
//    if (tokens.isEmpty) {
//      nodes flatten
//    }
//    else {
//      val nodeId = tokens(0)
//      val tagKey = tokens(1)
//
//      val tag = tagQueryService.find(tagKey)
//      val existingNode = tree.get(nodeId)
//
//      for {
//        a <- tag
//        b <- existingNode
//      } yield {
//        BranchNode(a, nodeId)
//      }
//      val newNode = tag map { t => {
//          val node = BranchNode(t, nodeId)
//
//          parentNode.addNode(node)
//
//          node
//        }
//      }
//
//      buildNodes(tokens.drop(2), tree, parentNode, nodes ++ Seq(newNode))
//    }
//  }
}
