package com.tpg.taxonomy

trait TagQueryService {
  def find(key: String): Option[Tag]
}


object Tags extends TagQueryService {
  private val tags: Map[String, Tag] = Map(
    "chinese" -> Tag("chinese", Seq(en_GB("Chinese"), fr_FR("Chinois"), it_IT("Cinese"))),
    "category" -> Tag("category", Seq(en_GB("Category"), fr_FR("Catégorie"), it_IT("Categoria"))),
    "show" -> Tag("show", Seq(en_GB("Show"), fr_FR("Mostrare"), it_IT("Spectacle"))),
    "restaurant" -> Tag("restaurant", Seq(en_GB("Restaurant"), fr_FR("Restaurant"), it_IT("Ristorante"))),
    "film" -> Tag("film", Seq(en_GB("Film"), fr_FR("Film"), it_IT("Film"))),
    "music" -> Tag("music", Seq(en_GB("Music"), fr_FR("La musique"), it_IT("Musica")))
  )

  override def find(key: String): Option[Tag] = tags.get(key)
}