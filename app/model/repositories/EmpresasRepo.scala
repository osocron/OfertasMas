package model.repositories

import javax.inject.Inject

import model.entities.Tables._
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future

/**
  * Created by osocron on 19/11/16.
  */
class EmpresasRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Empresas, EmpresasRow] {

  override val t: TableQuery[Empresas] = TableQuery[Empresas]

  def getEmpresaPorOferta(idOferta: Int): Future[Option[EmpresasRow]] = {
    db.run((for {
      oferta  <- Ofertas if oferta.idOferta === idOferta
      empresa <- Empresas if empresa.idEmpresa === oferta.idOferta
    } yield empresa).result.headOption)
  }

}
