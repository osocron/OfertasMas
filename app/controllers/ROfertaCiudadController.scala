package controllers

import javax.inject.Inject

import io.circe.Encoder
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

  def rOfertaCiudades = Action.async { request =>
    getAction(rOfertaCiudadRepo)
  }

  def rOfertaCiudad(idROfertaCiudad: Int) = Action.async { request =>
    queryAction(rOfertaCiudadRepo, "Relacion no encontrada")(_.idROfertaCuidad === idROfertaCiudad)
  }

}