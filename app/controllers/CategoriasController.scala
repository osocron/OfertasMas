package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import model.entities.Tables.{Categorias, CategoriasRow}
import model.repositories.CategoriasRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

class CategoriasController @Inject()(categoriasRepo: CategoriasRepo)
  extends ControllerActions[Categorias, CategoriasRow] with Circe {

  override implicit val encoder: Encoder[CategoriasRow] = deriveEncoder[CategoriasRow]

  override implicit val decoder: Decoder[CategoriasRow] = deriveDecoder[CategoriasRow]

  def categorias = Action.async { request =>
    getAction(categoriasRepo)
  }

  def categoria(idCategoria: Int) = Action.async { request =>
    queryAction(categoriasRepo, "Categoria no encontrada")(_.idCategoria === idCategoria)
  }

}
