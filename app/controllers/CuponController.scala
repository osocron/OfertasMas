package controllers

import javax.inject.Inject

import io.circe.Encoder
import io.circe.generic.semiauto._
import model.entities.Tables.{Cupon, CuponRow}
import model.repositories.CuponRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

/**
  * Created by osocron on 20/11/16.
  */
class CuponController @Inject()(cuponRepo: CuponRepo)
  extends ControllerActions[Cupon, CuponRow] with Circe {

  override implicit val encoder: Encoder[CuponRow] = deriveEncoder[CuponRow]

  def cupones = Action.async { request =>
    getAction(cuponRepo)
  }

  def cupon(codigoCupon: String) = Action.async { request =>
    queryAction(cuponRepo, "Cupon no encontrado")(_.codigoCupon === codigoCupon)
  }

}
