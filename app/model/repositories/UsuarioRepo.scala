package model.repositories

import javax.inject.Inject

import model.entities.Tables.{Usuario, UsuarioRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class UsuarioRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Usuario, UsuarioRow] {
  override val t: TableQuery[Usuario] = TableQuery[Usuario]
}
