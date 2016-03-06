package controllers

import javax.inject._
//import play.api.db.DB
import play.api.libs.json.{Json, JsValue}
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def test = Action {
    var outString = ""
//    val conn = DB.getConnection()
//    try {
//      val stmt = conn.createStatement
//      val rs = stmt.executeQuery("SELECT * FROM `users` WHERE 1 ")
//      while (rs.next()) {
//        outString += rs.getString("name")
//      }
//    } finally {
//      conn.close()
//    }

    Ok(outString)
  }

  val json: JsValue = Json.parse(
    """
{
  "name" : "Watership Down",
  "location" : {
    "lat" : 51.235685,
    "long" : -1.309197
  },
  "residents" : [ {
    "name" : "Fiver",
    "age" : 4,
    "role" : null
  }, {
    "name" : "Bigwig",
    "age" : 6,
    "role" : "Owsla"
  } ]
}
    """)

}
