package models

import java.sql.{DriverManager, PreparedStatement, ResultSet}

import scala.collection.mutable
object MemoryModel {
  private val users = mutable.Map[String,String]()

  def validateUser(username:String,password:String):Boolean={
    //Postgres Connector
    var check: Boolean = false
    var usname: String = ""
    var passw: String = ""
    classOf[org.postgresql.Driver]
    val con_str = "jdbc:postgresql://localhost:5432/proje1?user=postgres&password=123"
    val conn = DriverManager.getConnection(con_str)
    try {
      val stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val rs = stm.executeQuery("SELECT * from main_table")

      while(rs.next) {
        if(!check){
          usname = rs.getString("username")
          passw = rs.getString("pass")
        }
        check = usname.equals(username) && passw.equals(password)
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
    val con_str = "jdbc:postgresql://localhost:5432/proje1?user=postgres&password=123"
    val conn = DriverManager.getConnection(con_str)
    try {
      val stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val rs = stm.executeQuery("SELECT * from main_table")

      while(rs.next) {
        if(!check){
          usname = rs.getString("username")
          passw = rs.getString("pass")
        }
        check = usname.equals(username)
      }

      if(check) false
      else {
        println("Buradan gecti")
        val query: String = "INSERT INTO main_table(username, pass) VALUES (?, ?)"
        val preparedStatement: PreparedStatement = conn.prepareStatement(query)
        preparedStatement.setString(1,username)
        preparedStatement.setString(2,password)
        preparedStatement.execute()
        true
      }
    } finally {
      conn.close()
    }

  }





}
