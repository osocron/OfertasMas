package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Categorias, Ofertas, OfertasRow, RCategoriaOferta}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery

import scala.concurrent.Future

/**
  * Created by osocron on 19/11/16.
  */
class OfertasRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                            rCategoriaOfertaRepo: RCategoriaOfertaRepo,
                            categoriasRepo: CategoriasRepo)
  extends RepositoryUtils[Ofertas, OfertasRow] {
  override val t: TableQuery[Ofertas] = TableQuery[Ofertas]

  def getOfertasPorCategoria(categoria: String): Future[Seq[OfertasRow]] = {
    db.run((for {
      cats <- Categorias if cats.nombreCategoria === categoria
      rCO  <- RCategoriaOferta if rCO.idCategoria === cats.idCategoria
      ofer <- Ofertas if ofer.idOferta === rCO.idOferta
    } yield ofer).result)
  }

}
