package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import model.entities.Tables.{ROfertaCuidad, ROfertaCuidadRow}
import model.repositories.ROfertaCiudadRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

/**
  * Created by osocron on 20/11/16.
  */
class ROfertaCiudadController @Inject()(rOfertaCiudadRepo: ROfertaCiudadRepo)
  extends ControllerActions[ROfertaCuidad, ROfertaCuidadRow] with Circe {

  override implicit val encoder: Encoder[ROfertaCuidadRow] = deriveEncoder[ROfertaCuidadRow]

  override implicit val decoder: Decoder[ROfertaCuidadRow] = deriveDecoder[ROfertaCuidadRow]

  def rOfertaCiudades: Action[AnyContent] = Action.async { request =>
    getAction(rOfertaCiudadRepo)
  }

  def rOfertaCiudad(idROfertaCiudad: Int): Action[AnyContent] = Action.async { request =>
    queryAction(rOfertaCiudadRepo, "Relacion no encontrada")(_.idROfertaCuidad === idROfertaCiudad)
  }

}
