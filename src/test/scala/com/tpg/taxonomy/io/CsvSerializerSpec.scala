package com.tpg.taxonomy.io

import com.tpg.taxonomy.{TaxonomyTree, Node}
import com.tpg.taxonomy.Tags._
import org.scalatest.{FunSpec, Matchers, GivenWhenThen}

class CsvSerializerSpec extends FunSpec with GivenWhenThen with Matchers {
  describe("csv serializer") {
    it("persists a tree to csv format") {
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

      When("I want to serialize the tree to csv format")
      val result = CsvSerializer(tree).serialize

      Then("serialize the tree to csv format")
      val expected = Seq(categoriesNode, showsNode, filmsNode, chineseNode, musicNode, restaurantsNode, chinese2Node) map { node =>
        s"${node.parent map { node => s"${node.tag.name},${node.label}"} getOrElse("")},${node.tag.name},${node.label}"
      }

      result should be(expected)
    }
  }
}
