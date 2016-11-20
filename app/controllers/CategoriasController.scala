package controllers

import javax.inject.Inject

import model.entities.Tables.{Categorias, CategoriasRow}
import model.repositories.CategoriasRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

class CategoriasController @Inject()(categoriasRepo: CategoriasRepo)
  extends ControllerActions[Categorias, CategoriasRow] with Circe {

  case class Message(error: Boolean = false, message: String)

  def categorias = Action.async { request =>
    getAction(categoriasRepo)
  }

  def categoria(idCategoria: Int) = Action.async { request =>
    queryAction(categoriasRepo,
      "Categoría no encontrada")(_.idCategoria === idCategoria)
  }

}
