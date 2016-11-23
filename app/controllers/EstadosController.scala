package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import model.entities.Tables.{Estados, EstadosRow}
import model.repositories.EstadosRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

/**
  * Created by osocron on 20/11/16.
  */
class EstadosController @Inject()(estadosRepo: EstadosRepo)
  extends ControllerActions[Estados, EstadosRow] with Circe {

  override implicit val encoder: Encoder[EstadosRow] = deriveEncoder[EstadosRow]

  override implicit val decoder: Decoder[EstadosRow] = deriveDecoder[EstadosRow]

  def estados: Action[AnyContent] = Action.async { request =>
    getAction(estadosRepo)
  }

  def estado(idEstado: Int): Action[AnyContent] = Action.async { request =>
    queryAction(estadosRepo, "Estado no encontrado")(_.idEstado === idEstado)
  }

}
