package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Ofertas, OfertasRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class OfertasRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Ofertas, OfertasRow] {
  override val t: TableQuery[Ofertas] = TableQuery[Ofertas]
}
