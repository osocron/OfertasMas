package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Empresas, EmpresasRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class EmpresasRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Empresas, EmpresasRow] {
  override val t: TableQuery[Empresas] = TableQuery[Empresas]
}
