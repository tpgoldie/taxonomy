package com.tpg.taxonomy

import com.tpg.taxonomy.Tags.find
import org.scalatest.{Matchers, GivenWhenThen, FeatureSpec}

class TaxonomyTreeSpec extends FeatureSpec with GivenWhenThen with Matchers {
  feature("retrieving nodes by id") {
    info("It is assumed nodes do not have unique ids as per example tree")

    scenario("retrieve a single node by id") {
      Given("a taxonomy tree")

      val rootNode = Node(Tag("", Seq()), "")

      val categoriesNode = Node(find("category").get, "categories")
      rootNode.addNode(categoriesNode)

      val filmsNode = Node(find("film").get, "films")
      categoriesNode.addNode(filmsNode)

      val chineseNode = Node(find("chinese").get, "chinese")
      filmsNode.addNode(chineseNode)

      val tree = TaxonomyTree(Option(rootNode))

      When("I search for a node with id chinese")
      val id = "chinese"
      val result = tree.findById(id)

      Then("It will find 1 chinese node")
      result.size should be(1)
      result foreach { node => node.id should be(Id("chinese")) }
    }

    scenario("retrieve multiple nodes by id") {
      Given("a taxonomy tree")
      val rootNode = Node(Tag("", Seq()), "")

      val categoriesNode = Node(find("category").get, "categories")
      rootNode.addNode(categoriesNode)

      val showsNode = Node(find("show").get, "shows")
      categoriesNode.addNode(showsNode)

      val filmsNode = Node(find("film").get, "films")
      showsNode.addNode(filmsNode)

      val chineseNode = Node(find("chinese").get, "chinese")
      filmsNode.addNode(chineseNode)

      val restaurantsNode = Node(find("restaurant").get, "restaurants")
      categoriesNode.addNode(restaurantsNode)

      val chinese2Node = Node(find("chinese").get, "chinese")
      restaurantsNode.addNode(chinese2Node)

      val tree = TaxonomyTree(Option(rootNode))

      When("I search for a node with id chinese")
      val id = "chinese"
      val result = tree.findById(id)

      Then("It will find 2 chinese nodes")
      result.size should be(2)
      result foreach { node => node.id should be(Id("chinese")) }
    }
  }

  feature("retrieving all the descendants of a node") {
    scenario("retrieve descendant nodes") {
      Given("a taxonomy tree")
      val rootNode = Node(Tag("", Seq()), "")

      val categoriesNode = Node(find("category").get, "categories")
      rootNode.addNode(categoriesNode)

      val showsNode = Node(find("show").get, "shows")
      categoriesNode.addNode(showsNode)

      val musicNode = Node(find("music").get, "music")
      categoriesNode.addNode(musicNode)

      val filmsNode = Node(find("film").get, "films")
      showsNode.addNode(filmsNode)

      val chineseNode = Node(find("chinese").get, "chinese")
      filmsNode.addNode(chineseNode)

      val restaurantsNode = Node(find("restaurant").get, "restaurants")
      categoriesNode.addNode(restaurantsNode)

      val chinese2Node = Node(find("chinese").get, "chinese")
      restaurantsNode.addNode(chinese2Node)

      val tree = TaxonomyTree(Option(rootNode))

      When("I search for all the descendants of a node with id chinese")
      val result = showsNode descendants

      Then("It will find all the node's descendants")
      result should be(Seq(filmsNode, chineseNode))
    }
  }
}