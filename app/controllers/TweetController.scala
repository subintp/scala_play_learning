package controllers

import java.util.UUID

import dao.TweetDao
import javax.inject._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents, tweetDao: TweetDao)(implicit ec: ExecutionContext) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(tweetDao.findAllTweets.toString)
  }

  def find(id: UUID) = Action { implicit request: Request[AnyContent] =>
    // Use Option, Some, and None pattern here
    val tweet = tweetDao.find(id)
    Ok(Json.toJson(tweet))
  }

  def create(): Action[JsValue] = Action.async(parse.json) { implicit request: Request[JsValue] =>
    val reponseString: Future[String] = Future {
      tweetDao.findAllTweets.toString
    }
    reponseString.map { r => Ok(r) }
  }
}