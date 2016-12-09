package controllers

import javax.inject.Inject

import model.repositories.CuponRepo
import play.api.mvc._

/**
  * Created by osocron on 8/12/16.
  */
class CanjearCupones @Inject()(cuponRepo: CuponRepo) extends Controller {

  def canjearCupones = Action {
    Ok(views.html.cupones())
  }

}
