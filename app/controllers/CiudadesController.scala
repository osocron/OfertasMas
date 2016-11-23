package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
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

  override implicit val decoder: Decoder[CiudadesRow] = deriveDecoder[CiudadesRow]

  def ciudades: Action[AnyContent] = Action.async { request =>
    getAction(ciudadesRepo)
  }

  def ciudad(idCiudad: Int): Action[AnyContent] = Action.async { request =>
    queryAction(ciudadesRepo, "Ciudad no encontrada")(_.idCiudad === idCiudad)
  }

  def ciudadesPorEstado(idEstado: Int): Action[AnyContent] = Action.async { request =>
    queryAllAction(ciudadesRepo)(_.idEstado === idEstado)
  }

}
