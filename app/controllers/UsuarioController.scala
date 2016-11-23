package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
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

  override implicit val decoder: Decoder[UsuarioRow] = deriveDecoder[UsuarioRow]

  def usuarios: Action[AnyContent] = Action.async { request =>
    getAction(usuarioRepo)
  }

  def usuario(correoUsuario: String): Action[AnyContent] = Action.async { request =>
    queryAction(usuarioRepo, "Usuario no encontrado")(_.correoUsuario === correoUsuario)
  }

  def nuevoUsuario: Action[UsuarioRow] = Action.async(circe.json[UsuarioRow]) { request =>
    insertAction(request, usuarioRepo)
  }

}
