package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Ciudades, CiudadesRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class CiudadesRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Ciudades, CiudadesRow] {
  override val t: TableQuery[Ciudades] = TableQuery[Ciudades]
}
