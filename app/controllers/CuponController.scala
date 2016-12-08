package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.generic.semiauto._
import model.entities.Tables.{Cupon, CuponRow}
import model.repositories.CuponRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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
    cuponRepo.queryIfExists(c =>
      c.idUsuario === request.body.idUsuario && c.idOferta === request.body.idOferta).flatMap { b =>
        if (b) Future.successful(Ok(Mensaje(error = true, "El usuario ya genero un cupon para esta oferta").asJson.noSpaces))
        else insertAction(request, cuponRepo)
      }
  }

  def cuponesPorUsuario(correoUsuario: String): Action[AnyContent] = Action.async { request =>
    cuponRepo.updateVigencia(correoUsuario).flatMap { _ =>
      queryAllAction(cuponRepo)(_.idUsuario === correoUsuario)
    }
  }

}
