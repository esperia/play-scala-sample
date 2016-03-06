import java.sql.{Driver, DriverManager}

import org.scalatestplus.play.PlaySpec
import play.api.Logger
import play.api.Play.current
import play.api.db.DB

import scala.collection.mutable.Stack

class DatabaseSpec extends PlaySpec {

  "Database" must {
    "List of database drivers" in {
      val drivers = DriverManager.getDrivers

      while (drivers.hasMoreElements) {
        val driver:Driver = drivers.nextElement()
        Logger.info(driver.toString)
      }
    }
    "Test query" in {
      var outString = ""
      val conn = DB.getConnection()
      try {
        val stmt = conn.createStatement
        val rs = stmt.executeQuery("SELECT 9 as testkey ")
        while (rs.next()) {
          outString += rs.getString("testkey")
        }
      } finally {
        conn.close()
      }
      outString mustBe "9"
    }
  }

  "Slick" must {
    "How to use Scala test" in {
      // TODO
    }

    "throw NoSuchElementException if an empty stack is popped" in {
      val emptyStack = new Stack[Int]
      a [NoSuchElementException] must be thrownBy {
        emptyStack.pop()
      }
    }
  }
}