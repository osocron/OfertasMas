package controllers

import javax.inject.Inject

import io.circe.Encoder
import io.circe.generic.semiauto._
import model.entities.Tables.{RCategoriaOferta, RCategoriaOfertaRow}
import model.repositories.RCategoriaOfertaRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

/**
  * Created by osocron on 20/11/16.
  */
class RCategoriaOfertaController @Inject()(rCategoriaOfertaRepo: RCategoriaOfertaRepo)
  extends ControllerActions[RCategoriaOferta, RCategoriaOfertaRow] with Circe {

  override implicit val encoder: Encoder[RCategoriaOfertaRow] = deriveEncoder[RCategoriaOfertaRow]

  def rCategoriaOfertas = Action.async { request =>
    getAction(rCategoriaOfertaRepo)
  }

  def rCategoriaOferta(idRCategoriaOferta: Int) = Action.async { request =>
    queryAction(rCategoriaOfertaRepo, "Relacion no encontrada")(_.idRCategoriaOferta === idRCategoriaOferta)
  }

}
