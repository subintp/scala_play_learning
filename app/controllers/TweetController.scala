package controllers

import java.util.UUID

import dao.TweetDao
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents, tweetDao: TweetDao) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(tweetDao.findAllTweets.toString)
  }

  def find(id: UUID) = Action { implicit request: Request[AnyContent] =>
    // Use Option, Some, and None pattern here
    val tweet = tweetDao.find(id)
    Ok(Json.toJson(tweet))
  }
}