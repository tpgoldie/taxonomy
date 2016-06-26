package com.tpg.taxonomy

import com.tpg.taxonomy.Tags.find
import org.scalatest.{Matchers, GivenWhenThen, FeatureSpec}

class TaxonomyTreeSpec extends FeatureSpec with GivenWhenThen with Matchers {
  feature("retrieving nodes by id") {
    info("It is assumed nodes have unique ids. This is not explicitly specified")

    scenario("retrieve node by id") {
      Given("a taxonomy tree")
      val rootNode = Node(Tag("", Seq()), "", "")

      val categoriesNode = Node(find("category").get, IdGenerator.generate, "categories")
      rootNode.addNode(categoriesNode)

      val showsNode = Node(find("show").get, IdGenerator.generate, "shows")
      categoriesNode.addNode(showsNode)

      val filmsNode = Node(find("film").get, IdGenerator.generate, "films")
      showsNode.addNode(filmsNode)

      val chineseNode = Node(find("chinese").get, IdGenerator.generate, "chinese")
      filmsNode.addNode(chineseNode)

      val restaurantsNode = Node(find("restaurant").get, IdGenerator.generate, "restaurants")
      categoriesNode.addNode(restaurantsNode)

      val chinese2Node = Node(find("chinese").get, IdGenerator.generate, "chinese")
      restaurantsNode.addNode(chinese2Node)

      val tree = TaxonomyTree(Option(rootNode))

      When("I search for a node with id chinese")
      val id = chineseNode.id.value
      val result = tree.findById(id)

      Then("It will find 1 chinese node matching given id")
      result.size should be(1)
      result(0) should be(chineseNode)
    }
  }

  feature("retrieving all the descendants of a node") {
    scenario("retrieve descendant nodes") {
      Given("a taxonomy tree")
      val rootNode = Node(Tag("", Seq()), "", "")

      val categoriesNode = Node(find("category").get, IdGenerator.generate, "categories")
      rootNode.addNode(categoriesNode)

      val showsNode = Node(find("show").get, IdGenerator.generate, "shows")
      categoriesNode.addNode(showsNode)

      val musicNode = Node(find("music").get, IdGenerator.generate, "music")
      categoriesNode.addNode(musicNode)

      val filmsNode = Node(find("film").get, IdGenerator.generate, "films")
      showsNode.addNode(filmsNode)

      val chineseNode = Node(find("chinese").get, IdGenerator.generate, "chinese")
      filmsNode.addNode(chineseNode)

      val restaurantsNode = Node(find("restaurant").get, IdGenerator.generate, "restaurants")
      categoriesNode.addNode(restaurantsNode)

      val chinese2Node = Node(find("chinese").get, IdGenerator.generate, "chinese")
      restaurantsNode.addNode(chinese2Node)

      When("I ask for the descendants of a node")
      val result = showsNode descendants

      Then("It will find all the node's descendants")
      result should be(Seq(filmsNode, chineseNode))
    }
  }

  feature("retrieving nodes by tag") {
    scenario("retrieve descendant nodes") {
      Given("a taxonomy tree")
      val rootNode = Node(Tag("", Seq()), "", "")

      val categoriesNode = Node(find("category").get, IdGenerator.generate, "categories")
      rootNode.addNode(categoriesNode)

      val showsNode = Node(find("show").get, IdGenerator.generate, "shows")
      categoriesNode.addNode(showsNode)

      val musicNode = Node(find("music").get, IdGenerator.generate, "music")
      categoriesNode.addNode(musicNode)

      val filmsNode = Node(find("film").get, IdGenerator.generate, "films")
      showsNode.addNode(filmsNode)

      val chineseNode = Node(find("chinese").get, IdGenerator.generate, "chinese")
      filmsNode.addNode(chineseNode)

      val restaurantsNode = Node(find("restaurant").get, IdGenerator.generate, "restaurants")
      categoriesNode.addNode(restaurantsNode)

      val chinese2Node = Node(find("chinese").get, IdGenerator.generate, "chinese")
      restaurantsNode.addNode(chinese2Node)

      val tree = TaxonomyTree(Option(rootNode))

      When("I search for nodes by tag")
      val result = tree findByTag find("chinese").get

      Then("It will find all the nodes for the given tag")
      result should be(Seq(chineseNode, chinese2Node))
    }
  }
}