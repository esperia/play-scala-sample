import org.scalatest.BeforeAndAfter
import org.scalatestplus.play.PlaySpec
import play.api.libs.json._

class JsonSpec extends PlaySpec {

  val jsonTestValue: JsValue = Json.parse("""
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

  "Json" must {
    "Get values" in {
      // String
      val jsName: JsValue = (jsonTestValue \ "name").get
      val name: String = jsName.as[String]
      name mustBe "Watership Down"

      // Double
      val jsLat: JsValue = (jsonTestValue \ "location" \ "lat").get
      val lat: Double = jsLat.as[Double]
      lat mustBe 51.235685

      // floatとして取得
      val jsLong: JsValue = (jsonTestValue \ "location" \ "long").get
      val long: Float = jsLong.as[Float]
      long mustBe -1.309197f // "f"をつけないとDoubleとなり、テスト落ちる

      // JsArray
      val result: JsLookupResult = jsonTestValue \ "residents"
      val jsResidents1: JsValue = (jsonTestValue \ "residents")(1).get
      (jsResidents1 \ "name").get.as[String] mustBe "Bigwig"
    }

    "Build values" in {
      val json: JsValue = Json.obj(
        "name" -> "Watership Down",
        "location" -> Json.obj("lat" -> 51.235685, "long" -> -1.309197),
        "residents" -> Json.arr(
          Json.obj(
            "name" -> "Fiver",
            "age" -> 4,
            "role" -> JsNull
          ),
          Json.obj(
            "name" -> "Bigwig",
            "age" -> 6,
            "role" -> "Owsla"
          )
        )
      )

      // 値取得してみる
      val jsName: JsValue = (json \ "name").get
      val name: String = jsName.as[String]
      name mustBe "Watership Down"
    }
  }

}