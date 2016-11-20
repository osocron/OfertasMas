package controllers

import javax.inject.Inject

import io.circe.Encoder
import io.circe.generic.semiauto._
import model.entities.Tables.{Usuario, UsuarioRow}
import model.repositories.UsuarioRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

/**
  * Created by osocron on 20/11/16.
  */
class UsuarioController @Inject()(usuarioRepo: UsuarioRepo)
  extends ControllerActions[Usuario, UsuarioRow] with Circe {

  override implicit val encoder: Encoder[UsuarioRow] = deriveEncoder[UsuarioRow]

  def usuarios = Action.async { request =>
    getAction(usuarioRepo)
  }

  def usuario(correoUsuario: String) = Action.async { request =>
    queryAction(usuarioRepo, "Usuario no encontrado")(_.correoUsuario === correoUsuario)
  }

}
