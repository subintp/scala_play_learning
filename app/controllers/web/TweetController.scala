package controllers.web

import dao.TweetDao
import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents, tweetDao: TweetDao)(implicit ec: ExecutionContext) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    val tweets = tweetDao.findAll
    Ok(views.html.tweet.index(tweets))
  }
}