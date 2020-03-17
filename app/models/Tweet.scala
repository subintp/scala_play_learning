package models

import java.util.{Date, UUID}

import play.api.libs.json.{Json, Writes}

case class Tweet(user_id: UUID, tweet_id: UUID, created_at: Date = new Date(), content: String)

object Tweet {

  implicit val TweetWrites = new Writes[Tweet] {
    def writes(tweet: Tweet) = Json.obj(
      "user_id"  -> tweet.user_id,
      "tweet_id" -> tweet.tweet_id,
      "created_at" -> tweet.created_at,
      "content" -> tweet.content
    )
  }
}