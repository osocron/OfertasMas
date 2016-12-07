package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Categorias, Ofertas, OfertasRow, RCategoriaOferta, ROfertaCuidad}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery

import scala.concurrent.Future

/**
  * Created by osocron on 19/11/16.
  */
class OfertasRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                            rCategoriaOfertaRepo: RCategoriaOfertaRepo,
                            categoriasRepo: CategoriasRepo,
                            rOfertaCiudadRepo: ROfertaCiudadRepo)
  extends RepositoryUtils[Ofertas, OfertasRow] {
  override val t: TableQuery[Ofertas] = TableQuery[Ofertas]

  def getOfertasPorCategoria(categoria: String): Future[Seq[OfertasRow]] = {
    db.run((for {
      cats <- Categorias if cats.nombreCategoria === categoria
      rCO  <- RCategoriaOferta if rCO.idCategoria === cats.idCategoria
      ofer <- Ofertas if ofer.idOferta === rCO.idOferta
    } yield ofer).result)
  }

  def getOfertasPorCiudadCategoria(idCiudad: Int, idCategoria: Int): Future[Seq[OfertasRow]] = {
    db.run((for {
      rCiudOfer <- ROfertaCuidad if rCiudOfer.idCuidad === idCiudad
      rCatOfer  <- RCategoriaOferta if rCatOfer.idCategoria === idCategoria
      ofer <- Ofertas if ofer.idOferta === rCiudOfer.idOferta && ofer.idOferta === rCatOfer.idOferta
    } yield ofer).result)
  }

}
