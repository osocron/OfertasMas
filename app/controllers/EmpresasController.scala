package controllers

import javax.inject.Inject

import io.circe.Encoder
import io.circe.generic.semiauto._
import model.entities.Tables.{Empresas, EmpresasRow}
import model.repositories.EmpresasRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

/**
  * Created by osocron on 20/11/16.
  */
class EmpresasController @Inject()(empresasRepo: EmpresasRepo)
  extends ControllerActions[Empresas, EmpresasRow] with Circe {

  override implicit val encoder: Encoder[EmpresasRow] = deriveEncoder[EmpresasRow]

  def empresas = Action.async { request =>
    getAction(empresasRepo)
  }

  def empresa(idEmpresa: Int) = Action.async { request =>
    queryAction(empresasRepo, "Empresa no encontrada")(_.idEmpresa === idEmpresa)
  }

}
