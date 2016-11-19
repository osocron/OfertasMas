package model.repositories

import javax.inject.Inject

import model.entities.Tables.{RCategoriaOferta, RCategoriaOfertaRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

/**
  * Created by osocron on 19/11/16.
  */
class RCategoriaOfertaRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[RCategoriaOferta, RCategoriaOfertaRow] {
  override val t: TableQuery[RCategoriaOferta] = TableQuery[RCategoriaOferta]
}
