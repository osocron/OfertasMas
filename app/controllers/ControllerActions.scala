package controllers

import cats.data.OptionT
import io.circe.generic.auto._
import io.circe.syntax._
import model.repositories.RepositoryUtils
import play.api.libs.circe.Circe
import play.api.mvc._
import slick.driver.MySQLDriver.api.Table
import slick.lifted.Rep

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * This Trait abstract over the most common CRUD operations on
  * database entities used inside a RESTful context.
  *
  * @tparam A The TableQuery type parameter
  * @tparam B The TableRow type parameter
  */
trait ControllerActions[A <: Table[B], B] extends Controller with Circe {

  case class Message(error: Boolean = false, message: String)

  val addedMsg = Message(error = false, "Agregado satisfactoriamente").asJson.noSpaces

  /**
    * GET method that returns a Json Array of B entities
    *
    * @param repo The repository with the actual implementations
    *             of CRUD operations
    * @return     A Json Response with the outcome Message
    */
  def getAction(repo: RepositoryUtils[A, B]) =
    repo.getAll.map(c => Ok(c.asJson.noSpaces)) recover {
      case cause => Ok(Message(error = false, cause.getMessage).asJson.noSpaces)
    }


  /**
    * POST method that inserts a B entity on the DB.
    *
    * @param request The implicit HTTP POST request
    * @param repo    The repository with CRUD operations for B
    * @return        A Json Response with the outcome Message
    */
  def insertAction(request: Request[B], repo: RepositoryUtils[A, B]) = {
    repo.add(request.body)
      .map(r => Ok(addedMsg))
      .recover {
        case cause =>
          Ok(Message(error = true, cause.getMessage).asJson.noSpaces)
      }
  }

  /**
    * GET method that queries for a B given a predicate.
    *
    * @param repo         The repository with CRUD operations for B
    * @param notFoundMsg  The Message if a B wasn't found
    * @param p            The query predicate
    * @return             A Json Response with the outcome Message
    */
  def queryAction(repo: RepositoryUtils[A, B],
                  notFoundMsg: String)(p: (A => Rep[Boolean])) = {
    OptionT(repo.queryByPredicate(p))
      .map(c => Ok(c.asJson.noSpaces))
      .getOrElse(Ok(Message(error = false, notFoundMsg).asJson.noSpaces))
  }

}
