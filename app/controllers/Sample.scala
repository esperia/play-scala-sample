package controllers

import play.api.mvc._

class Sample extends Controller {

  def show(page: String) = Action {
    val a = page match {
      case "test" => test
      case _ => NotFound
    }
    NotFound
  }

  def test = Action {
    Ok("test: ok")
  }

}
