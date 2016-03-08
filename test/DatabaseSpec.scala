import java.sql.{Driver, DriverManager}

import org.scalatest.BeforeAndAfter
import org.scalatestplus.play.PlaySpec
import play.api.Logger
import scalikejdbc._
import scalikejdbc.config.DBs

import scala.collection.mutable.Stack

class DatabaseSpec extends PlaySpec with BeforeAndAfter {
  implicit var session = AutoSession

  before {
    DBs.setupAll()
  }

  after {
    DBs.closeAll()
  }

  "Database" must {
    "List of database drivers" in {
      val drivers = DriverManager.getDrivers

      while (drivers.hasMoreElements) {
        val driver:Driver = drivers.nextElement()
        Logger.info(driver.toString)
      }
    }
    "Fetch users" in {
      val sql: SQL[Nothing, NoExtractor] = sql"select * from users"
      val map: SQL[Map[String, Any], HasExtractor] = sql.toMap
      val list: SQLToList[Map[String, Any], HasExtractor] = map.list
      val users: List[Map[String, Any]] = list.apply()
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