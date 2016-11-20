package controllers

import javax.inject.Inject

import io.circe.Encoder
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

  def estados = Action.async { request =>
    getAction(estadosRepo)
  }

  def estado(idEstado: Int) = Action.async { request =>
    queryAction(estadosRepo, "Estado no encontrado")(_.idEstado === idEstado)
  }

}
