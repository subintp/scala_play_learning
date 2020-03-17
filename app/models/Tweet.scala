package models

import java.util.{Date, UUID}
import play.api.libs.json._

case class Tweet(user_id: UUID, tweet_id: UUID, created_at: Date = new Date(), content: String)

object Tweet {
  implicit val TweetReads = Json.reads[Tweet]
  implicit val TweetWrites = Json.writes[Tweet]
}