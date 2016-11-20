package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import model.entities.Tables.{Ofertas, OfertasRow}
import model.repositories.OfertasRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._
/**
  * Created by osocron on 20/11/16.
  */
class OfertasController @Inject()(ofertasRepo: OfertasRepo)
  extends ControllerActions[Ofertas, OfertasRow] with Circe {

  override implicit val encoder: Encoder[OfertasRow] = deriveEncoder[OfertasRow]

  override implicit val decoder: Decoder[OfertasRow] = deriveDecoder[OfertasRow]

  def ofertas = Action.async { request =>
    getAction(ofertasRepo)
  }

  def oferta(idOferta: Int) = Action.async { request =>
    queryAction(ofertasRepo, "Oferta no encontrada")(_.idOferta === idOferta)
  }

}
