package models

import scala.collection.mutable

object MemoryModel {
  private val users = mutable.Map[String,String]("jakixcar" -> "123")
  def validateUser(username:String,password:String):Boolean={
    users.get(username).map(_ == password).getOrElse(false)

  }

  def createUser(username:String,password:String):Boolean={
    if(users.contains((username))) false
    else {
      users(username) = password
      true
    }
  }

}
