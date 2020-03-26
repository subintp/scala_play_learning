package actions

import javax.inject.Inject
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class AuthenticateAction @Inject()(parser: BodyParsers.Default)(
  implicit ec: ExecutionContext
) extends ActionBuilderImpl(parser) {

  private val logger = play.api.Logger(this.getClass)

  override def invokeBlock[A](request: Request[A],
                              block: (Request[A]) => Future[Result]) = {
    logger.info("ENTERED AuthenticatedUserAction::invokeBlock ...")
    val maybeUsername = request.session.get(models.Global.SESSION_USERNAME_KEY)
    maybeUsername match {
      case None => {
        Future.successful(Forbidden("Dude, you’re not logged in."))
      }
      case Some(u) => {
        val res: Future[Result] = block(request)
        res
      }
    }
  }
}
