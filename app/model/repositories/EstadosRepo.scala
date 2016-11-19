package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Estados, EstadosRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class EstadosRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Estados, EstadosRow] {
  override val t: TableQuery[Estados] = TableQuery[Estados]
}
