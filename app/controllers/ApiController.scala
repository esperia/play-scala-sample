package controllers

import javax.inject._

import scalikejdbc.AutoSession

//import play.api.db.DB
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import scalikejdbc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ApiController @Inject() extends Controller {
  implicit val session = AutoSession

  def identifier(versions: Integer, identifier: String) = action(versions, identifier, null)

  def action(versions: Integer, identifier: String, action: String) = Action {
    Ok("versions=" + versions + ", identifier=" + identifier + ", action=" + action)
  }

}
