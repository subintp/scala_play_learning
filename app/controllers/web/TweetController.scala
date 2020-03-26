package controllers.web

import actions.{AuthenticateAction, LoggingAction}
import dao.TweetDao
import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents,
                                tweetDao: TweetDao,
                                loggingAction: LoggingAction,
                                authenticate: AuthenticateAction,
)(implicit ec: ExecutionContext)
    extends BaseController {

  def index() = authenticate { implicit request: Request[AnyContent] =>
    val tweets = tweetDao.findAll
    Ok(views.html.tweet.index(tweets))
  }
}
