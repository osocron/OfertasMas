package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Cupon, CuponRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class CuponRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Cupon, CuponRow]{
  override val t: TableQuery[Cupon] = TableQuery[Cupon]
}
