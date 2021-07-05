package models

import java.sql.{DriverManager, ResultSet}

import scala.collection.mutable
object MemoryModel {
  private val users = mutable.Map[String,String]()

  def validateUser(username:String,password:String):Boolean={
    println("Postgres connector")
    var check: Boolean = false
    var usname: String = ""
    var passw: String = ""
    classOf[org.postgresql.Driver]
    val con_str = "jdbc:postgresql://localhost/proje1?user=admin&password=123"
    val conn = DriverManager.getConnection(con_str)
    try {
      val stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val rs = stm.executeQuery("SELECT * from main_table")

      while(rs.next || !check) {
        usname = rs.getString("username")
        passw = rs.getString("pass")
        check = usname.equals(username) && password.equals(password)
      }
      check
    } finally {
      conn.close()
    }

  }

  def createUser(username:String,password:String):Boolean={
    println("Postgres connector")
    var check: Boolean = false
    var usname: String = ""
    var passw: String = ""
    classOf[org.postgresql.Driver]
    val con_str = "jdbc:postgresql://localhost/proje1?user=admin&password=123"
    val conn = DriverManager.getConnection(con_str)
    try {
      val stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val rs = stm.executeQuery("SELECT * from main_table")

      while(rs.next || !check) {
        usname = rs.getString("username")
        passw = rs.getString("pass")
        check = usname.equals(username) && password.equals(password)
      }

      if(check) false
      else {
        val rs = stm.executeQuery("INSERT INTO main_table VALUES(usname,passw)")
        true
      }
    } finally {
      conn.close()
    }

  }





}
