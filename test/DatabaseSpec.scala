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
    "Test query" in {
      try sql"select * from users".toMap.list.apply()
      catch { case e: Exception =>
        sql"create table accounts(name varchar(100) not null)".execute.apply()
        Seq("Alice", "Bob", "Chris").foreach { name =>
          sql"insert into accounts values ($name)".update.apply()
        }
        sql"select * from accounts".toMap.list.apply()
      }
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