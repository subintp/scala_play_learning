package controllers.api

import java.util.UUID

import dao.TweetDao
import javax.inject._
import models.Tweet
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents, tweetDao: TweetDao)(implicit ec: ExecutionContext) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(tweetDao.findAll))
  }

  def find(id: UUID) = Action.async { implicit request: Request[AnyContent] =>
    // Use Option, Some, and None pattern here
    val tweet: Future[Option[Tweet]] =  Future {
      tweetDao.find(id)
    }
    tweet.map { t => Ok(Json.toJson(t))}
  }

  def create(): Action[JsValue] = Action.async(parse.json) { implicit request: Request[JsValue] =>
    val TweetFromJson: JsResult[Tweet] =
      Json.fromJson[Tweet](request.body)

    TweetFromJson match {
      case JsSuccess(t: Tweet, path: JsPath) =>
        val tweet: Future[Option[Tweet]] = Future {
          tweetDao.create(t)
        }
        tweet.map { t => Ok(Json.toJson(t)) }

      case e @JsError(_) =>
        val errorResponse: Future[String] = Future {
          JsError.toJson(e).toString()
        }
        errorResponse.map { t => Ok(Json.toJson(t)) }
    }
  }
}