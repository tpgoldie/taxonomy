package com.tpg.taxonomy.io

import com.tpg.taxonomy.{TaxonomySpec, TaxonomyTree, Node}
import com.tpg.taxonomy.Tags._

class CsvSerializerSpec extends TaxonomySpec {
  describe("csv serializer") {
    it("serializes a tree to csv format") {
      Given("a taxonomy tree")
      val rootNode = Node()

      val categoriesNode = Node(rootNode, find("category").get, "categories")

      val showsNode = Node(categoriesNode, find("show").get, "shows")

      val musicNode = Node(categoriesNode, find("music").get, "music")

      val restaurantsNode = Node(categoriesNode, find("restaurant").get, "restaurants")

      val filmsNode = Node(showsNode, find("film").get, "films")

      val chineseNode = Node(filmsNode, find("chinese").get, "chinese")

      val chinese2Node = Node(restaurantsNode, find("chinese").get, "chinese")

      val tree = TaxonomyTree(Option(rootNode))

      When("I want to serialize the tree to csv format")
      val result = CsvSerializer(tree).serialize

      Then("serialize the tree to csv format")
      val expectedLine1 = generateLine(rootNode, Seq(categoriesNode))

      val expectedLine2 = generateLine(categoriesNode, Seq(showsNode, musicNode, restaurantsNode))

      val expectedLine3 = Seq(generateLine(showsNode, Seq(filmsNode)), generateLine(restaurantsNode, Seq(chinese2Node))) mkString ","

      val expectedLine4 = generateLine(filmsNode, Seq(chineseNode))

      result should be(Seq(expectedLine1, expectedLine2, expectedLine3, expectedLine4))
    }
  }

  private def generateLine(parent: Node, nodes: Seq[Node]): String = {
    nodes map { node =>
      parent.id.value -> node
    } map { pair =>
      s"${pair._1},${pair._2.id.toString},${pair._2.tag.name},${pair._2.label}"
    } mkString ","
  }
}
// ",category,categories",
// "categories,show,shows,categories,music,music,categories,restaurant,restaurants",
// "shows,film,films,restaurants,chinese,chinese",
// "films,chinese,chinese"
// ",category,categories",
// "categories,show,shows,categories,music,music,categories,restaurant,restaurants",
// "shows,film,films",
// "films,chinese,chinese",
// "restaurants,chinese,chinese")
