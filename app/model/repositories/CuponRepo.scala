package model.repositories

import java.sql.Timestamp
import java.util.Calendar
import javax.inject.Inject

import model.entities.Tables.{Cupon, CuponRow}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by osocron on 19/11/16.
  */
class CuponRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends RepositoryUtils[Cupon, CuponRow] {

  override val t: TableQuery[Cupon] = TableQuery[Cupon]

  def updateVigencia(correoUsuario: String)(implicit ec: ExecutionContext): Future[Seq[Future[Int]]] = {
    for {cupones <- queryAllByPredicate(_.idUsuario === correoUsuario)}
      yield {
        for {cupon <- cupones} yield {
          if (vencio(cupon.fechaCreacion)) {
            val q = for {c <- Cupon if c.codigoCupon === cupon.codigoCupon} yield c.idEstadoCupon
            val updateAction = q.update(2)
            db.run(updateAction)
          } else Future.successful(0)
        }
      }
  }

  def canjear(codigoCupon: Int)(implicit ec: ExecutionContext): Future[Int] = {
    val q = for {c <- Cupon if c.codigoCupon === codigoCupon} yield c.idEstadoCupon
    val updateAction = q.update(3)
    db.run(updateAction)
  }

  def vencio(timestamp: Timestamp): Boolean = {
    val fiveDays: Long = 5 * 24 * 60 * 60 * 1000
    timestamp.getTime + fiveDays < Calendar.getInstance().getTime.getTime
  }

}
