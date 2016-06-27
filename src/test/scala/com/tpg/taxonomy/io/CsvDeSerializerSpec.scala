package com.tpg.taxonomy.io

import com.tpg.taxonomy.Tags._
import com.tpg.taxonomy.{TaxonomyTree, Node, TaxonomySpec}

class CsvDeSerializerSpec extends TaxonomySpec {
  describe("csv de-serializer") {
    it("de-serializes a tree from csv format") {
      Given("a taxonomy tree")
      val rootNode = Node()

      val categoriesNode = Node(rootNode, find("category").get, "categories")

      val showsNode = Node(categoriesNode, find("show").get, "shows")

      val musicNode = Node(categoriesNode, find("music").get, "music")

      val filmsNode = Node(showsNode, find("film").get, "films")

      val chineseNode = Node(filmsNode, find("chinese").get, "chinese")

      val restaurantsNode = Node(categoriesNode, find("restaurant").get, "restaurants")

      val chinese2Node = Node(restaurantsNode, find("chinese").get, "chinese")

      val tree = TaxonomyTree(Option(rootNode))

      When("I want to de-serialize the tree from csv format")
      val lines = CsvSerializer(tree).serialize

      val actual = CsvDeSerializer(lines).deSerialize

      Then("tree is de-serialized from csv format")
      assertNodeInTree(tree, categoriesNode)
      assertNodeInTree(tree, showsNode)
      assertNodeInTree(tree, musicNode)
      assertNodeInTree(tree, filmsNode)
      assertNodeInTree(tree, chineseNode)
      assertNodeInTree(tree, restaurantsNode)
      assertNodeInTree(tree, chinese2Node)

    }
  }

  def assertNodeInTree(tree: TaxonomyTree, node: Node) = {
    tree.findById(node.id.value) should be(Option(node))
  }
}
