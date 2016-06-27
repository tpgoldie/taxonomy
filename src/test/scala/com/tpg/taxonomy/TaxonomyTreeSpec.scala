package com.tpg.taxonomy

import com.tpg.taxonomy.Tags.find
import org.scalatest.{Matchers, GivenWhenThen, FeatureSpec}

class TaxonomyTreeSpec extends FeatureSpec with GivenWhenThen with Matchers {
  feature("retrieving nodes by id") {
    info("It is assumed nodes have unique ids. This is not explicitly specified")

    scenario("retrieve node by id") {
      Given("a taxonomy tree")
      val rootNode = Node()

      val categoriesNode = Node(rootNode, find("category").get, "categories")

      val showsNode = Node(categoriesNode, find("show").get, "shows")

      val filmsNode = Node(showsNode, find("film").get, "films")

      val chineseNode = Node(filmsNode, find("chinese").get, "chinese")

      val restaurantsNode = Node(categoriesNode, find("restaurant").get, "restaurants")

      val chinese2Node = Node(restaurantsNode, find("chinese").get, "chinese")

      val tree = TaxonomyTree(Option(rootNode))

      When("I search for a node with id chinese")
      val id = chineseNode.id.value
      val result = tree.findById(id)

      Then("It will find 1 chinese node matching given id")
      result should be(Some(chineseNode))
    }
  }

  feature("retrieving all the descendants of a node") {
    scenario("retrieve descendant nodes") {
      Given("a taxonomy tree")
      val rootNode = Node()

      val categoriesNode = Node(rootNode, find("category").get, "categories")

      val showsNode = Node(categoriesNode, find("show").get, "shows")

      val musicNode = Node(categoriesNode, find("music").get, "music")

      val filmsNode = Node(showsNode, find("film").get, "films")

      val chineseNode = Node(filmsNode, find("chinese").get, "chinese")

      val restaurantsNode = Node(categoriesNode, find("restaurant").get, "restaurants")

      val chinese2Node = Node(restaurantsNode, find("chinese").get, "chinese")

      When("I ask for the descendants of a node")
      val result = showsNode descendants

      Then("It will find all the node's descendants")
      result should be(Seq(filmsNode, chineseNode))
    }
  }

  feature("retrieving nodes by tag") {
    scenario("retrieve descendant nodes") {
      Given("a taxonomy tree")
      val rootNode = Node(None, Tag("", Seq()), "", "")

      val categoriesNode = Node(rootNode, find("category").get, "categories")

      val showsNode = Node(categoriesNode, find("show").get, "shows")

      val musicNode = Node(categoriesNode, find("music").get, "music")

      val filmsNode = Node(showsNode, find("film").get, "films")

      val chineseNode = Node(filmsNode, find("chinese").get, "chinese")

      val restaurantsNode = Node(categoriesNode, find("restaurant").get, "restaurants")

      val chinese2Node = Node(restaurantsNode, find("chinese").get, "chinese")

      val tree = TaxonomyTree(Option(rootNode))

      When("I search for nodes by tag")
      val result = tree findByTag find("chinese").get

      Then("It will find all the nodes for the given tag")
      result should be(Seq(chineseNode, chinese2Node))
    }
  }
}