package controllers

import dao.TweetDao
import javax.inject._
import play.api.mvc._

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents, tweetDao: TweetDao) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(tweetDao.findAllTweets.toString)
  }
}
