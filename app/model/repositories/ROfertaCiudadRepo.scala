package model.repositories

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import model.entities.Tables.{ROfertaCuidad, ROfertaCuidadRow}
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class ROfertaCiudadRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[ROfertaCuidad, ROfertaCuidadRow] {
  override val t: TableQuery[ROfertaCuidad] = TableQuery[ROfertaCuidad]
}
