package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import io.circe.generic.auto._
import io.circe.syntax._
import model.entities.Tables.{Empresas, EmpresasRow}
import model.repositories.EmpresasRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by osocron on 20/11/16.
  */
class EmpresasController @Inject()(empresasRepo: EmpresasRepo)
  extends ControllerActions[Empresas, EmpresasRow] with Circe {

  override implicit val encoder: Encoder[EmpresasRow] = deriveEncoder[EmpresasRow]

  override implicit val decoder: Decoder[EmpresasRow] = deriveDecoder[EmpresasRow]

  def empresas = Action.async { request =>
    getAction(empresasRepo)
  }

  def empresa(idEmpresa: Int) = Action.async { request =>
    queryAction(empresasRepo, "Empresa no encontrada")(_.idEmpresa === idEmpresa)
  }

  def empresaPorOferta(idOferta: Int) = Action.async { request =>
    empresasRepo.getEmpresaPorOferta(idOferta).flatMap {
      case Some(e) => Future.successful(Ok(e.asJson.noSpaces))
      case None   =>  Future.successful(
        Ok(Message(error = false, "Empresa no encontrada").asJson.noSpaces)
      )
    }
  }

}
