package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import io.circe.generic.auto._
import io.circe.syntax._
import model.entities.Tables.{Usuario, UsuarioRow}
import model.repositories.UsuarioRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by osocron on 20/11/16.
  */
class UsuarioController @Inject()(usuarioRepo: UsuarioRepo)
  extends ControllerActions[Usuario, UsuarioRow] with Circe {

  override implicit val encoder: Encoder[UsuarioRow] = deriveEncoder[UsuarioRow]

  override implicit val decoder: Decoder[UsuarioRow] = deriveDecoder[UsuarioRow]

  def usuarios: Action[AnyContent] = Action.async { _ =>
    getAction(usuarioRepo)
  }

  def usuario(correoUsuario: String): Action[AnyContent] = Action.async { _ =>
    queryAction(usuarioRepo, "Usuario no encontrado")(_.correoUsuario === correoUsuario)
  }

  def nuevoUsuario: Action[UsuarioRow] = Action.async(circe.json[UsuarioRow]) { request =>
    insertAction(request, usuarioRepo)
  }

  def login: Action[AnyContent] = Action.async { request =>
    val data = request.body.asFormUrlEncoded
    (for {
      correo <- data.get("correo").map(_.toString)
      password <- data.get("contrasena").map(_.toString)
    } yield for {
      b <- usuarioRepo.queryIfExists(u => u.correoUsuario === correo && u.contrasenaUsuario === password)
    } yield
      if (b) Ok(Message(error = false, "Encontrado").asJson.noSpaces)
      else Ok(Message(error = true, "Usuario no encontrado").asJson.noSpaces)).head
  }

}
