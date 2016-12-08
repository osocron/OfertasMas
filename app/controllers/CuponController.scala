package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import model.entities.Tables.{Cupon, CuponRow}
import model.repositories.CuponRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by osocron on 20/11/16.
  */
class CuponController @Inject()(cuponRepo: CuponRepo)
  extends ControllerActions[Cupon, CuponRow] with Circe {

  override implicit val encoder: Encoder[CuponRow] = deriveEncoder[CuponRow]

  override implicit val decoder: Decoder[CuponRow] = deriveDecoder[CuponRow]

  def cupones: Action[AnyContent] = Action.async { request =>
    getAction(cuponRepo)
  }

  def cupon(codigoCupon: Int): Action[AnyContent] = Action.async { request =>
    queryAction(cuponRepo, "Cupon no encontrado")(_.codigoCupon === codigoCupon)
  }

  def nuevoCupon: Action[_root_.model.entities.Tables.CuponRow] = Action.async(circe.json[CuponRow]) { request =>
    insertAction(request, cuponRepo)
  }

  def cuponesPorUsuario(correoUsuario: String): Action[AnyContent] = Action.async { request =>
    cuponRepo.updateVigencia(correoUsuario)
    queryAction(cuponRepo, "Cupones no encontrados")(_.idUsuario === correoUsuario)
  }

}
