package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Categorias, CategoriasRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class CategoriasRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Categorias, CategoriasRow] {
  override val t: TableQuery[Categorias] = TableQuery[Categorias]
}
