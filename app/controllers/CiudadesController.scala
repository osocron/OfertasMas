package controllers

import javax.inject.Inject

import io.circe.Encoder
import io.circe.generic.semiauto._
import model.entities.Tables.{Ciudades, CiudadesRow}
import model.repositories.CiudadesRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

/**
  * Created by osocron on 19/11/16.
  */
class CiudadesController @Inject()(ciudadesRepo: CiudadesRepo)
  extends ControllerActions[Ciudades, CiudadesRow] with Circe {

  override implicit val encoder: Encoder[CiudadesRow] = deriveEncoder[CiudadesRow]

  def ciudades = Action.async { request =>
    getAction(ciudadesRepo)
  }

  def ciudad(idCiudad: Int) = Action.async { request =>
    queryAction(ciudadesRepo, "Ciudad no encontrada")(_.idCiudad === idCiudad)
  }

}