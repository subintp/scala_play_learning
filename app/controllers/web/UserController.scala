package controllers.web

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(val controllerComponents: ControllerComponents)(
  implicit ec: ExecutionContext
) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok("Hello world")
  }
}
