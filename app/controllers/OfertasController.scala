package controllers

import javax.inject.Inject

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import io.circe.generic.auto._
import io.circe.syntax._
import model.entities.Tables
import model.entities.Tables.{Ofertas, OfertasRow}
import model.repositories.OfertasRepo
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by osocron on 20/11/16.
  */
class OfertasController @Inject()(ofertasRepo: OfertasRepo)
  extends ControllerActions[Ofertas, OfertasRow] with Circe {

  override implicit val encoder: Encoder[OfertasRow] = deriveEncoder[OfertasRow]

  override implicit val decoder: Decoder[OfertasRow] = deriveDecoder[OfertasRow]

  def ofertas: Action[AnyContent] = Action.async { _ =>
    getAction(ofertasRepo)
  }

  def oferta(idOferta: Int): Action[AnyContent] = Action.async { _ =>
    queryAction(ofertasRepo, "Oferta no encontrada")(_.idOferta === idOferta)
  }

  def ofertaPorNombre(nombreOferta: String): Action[AnyContent] = Action.async { _ =>
    queryAction(ofertasRepo, "Oferta no encontrada")(_.nombreOferta === nombreOferta)
  }

  def ofertasPorCategorias(nombreCategoria: String): Action[AnyContent] =
    ofertasByFunction(ofertasRepo.getOfertasPorCategoria(nombreCategoria))

  def ofertasPorCiudadCategoria(idCiudad: Int, idCategoria: Int): Action[AnyContent] =
    ofertasByFunction(ofertasRepo.getOfertasPorCiudadCategoria(idCiudad, idCategoria))


  def ofertasByFunction(b: => Future[Seq[Tables.OfertasRow]]): Action[AnyContent] = Action.async { _ =>
    (for {
      ofertas <- b
    } yield Ok(ofertas.asJson.noSpaces))
      .recover { case cause => Ok(Mensaje(error = true, cause.getMessage).asJson.noSpaces) }
  }

}
