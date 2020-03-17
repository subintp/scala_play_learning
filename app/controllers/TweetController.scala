package controllers

import java.util.UUID

import dao.TweetDao
import javax.inject._
import models.Tweet
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents, tweetDao: TweetDao)(implicit ec: ExecutionContext) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(tweetDao.findAllTweets.toString)
  }

  def find(id: UUID) = Action.async { implicit request: Request[AnyContent] =>
    // Use Option, Some, and None pattern here
    val tweet: Future[Option[Tweet]] =  Future {
      tweetDao.find(id)
    }
    tweet.map { t => Ok(Json.toJson(t))}
  }

  def create(): Action[JsValue] = Action.async(parse.json) { implicit request: Request[JsValue] =>
    val responseString: Future[String] = Future {
      tweetDao.findAllTweets.toString
    }
    responseString.map { r => Ok(r) }
  }
}