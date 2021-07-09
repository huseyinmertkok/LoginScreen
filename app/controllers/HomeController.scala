package controllers

import javax.inject._
import models.MemoryModel
import play.api.mvc._


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def login() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login())
  }

  def validateLoginGet(username:String,password:String) = Action{
    Ok(s"$username logged in with $password")
  }

  def validateLoginPost = Action{request =>
    val postvals = request.body.asFormUrlEncoded
    postvals.map{args=>
      val username = args("username").head
      println(username)
      val password = args("password").head
      if(MemoryModel.validateUser(username,password))
        Redirect(routes.WebSocketController.index(username))
      else
        Redirect(routes.HomeController.login()).flashing("error" -> "Invalid username or password")

    }getOrElse (Ok("Hatalı şifre"))
  }

  def createUser = Action{ request =>
    val postvals = request.body.asFormUrlEncoded

    postvals.map{args=>
      val username = args("username").head
      println(username)
      val password = args("password").head
      if(MemoryModel.createUser(username,password))
        Redirect(routes.HomeController.login()).flashing("error" -> "Register complete")
      else
        Redirect(routes.HomeController.login()).flashing("error" -> "Username already exist")
    }getOrElse (Ok("Hatalı şifre"))
  }
}
