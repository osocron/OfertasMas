package model.repositories

import javax.inject.Inject

import model.entities.Tables.{EstadoCupon, EstadoCuponRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 7/12/16.
  */
class EstadosCuponRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[EstadoCupon, EstadoCuponRow] {
  override val t: TableQuery[EstadoCupon] = TableQuery[EstadoCupon]
}
