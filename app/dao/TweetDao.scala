package dao

import java.util.{ UUID, Date}
import javax.inject.{Inject, Singleton}
import storage.CassandraSession
import models.Tweet

@Singleton
class TweetDao @Inject() (session: CassandraSession)  {

  lazy val getTweetQuery =
    """
      |SELECT * FROM twitter.tweets
      |WHERE tweet_id = ?
      |LIMIT 1
      |ALLOW FILTERING
    """.stripMargin

  lazy val getTweetStatement = session.session.prepare(getTweetQuery)

  lazy val createTweetQuery =
    """
      |INSERT INTO twitter.tweets(tweet_id, user_id, content, created_at)
      |VALUES(?, ?, ?, ?)
    """.stripMargin

  lazy val createTweetStatement = session.session.prepare(createTweetQuery)

  def findAllTweets: Unit = {
    val tweets = session.session.execute("select * from twitter.tweets limit 2").all()
    println("Hello Tweets")
    println(tweets)
  }

  def find(tweetId: UUID): Option[Tweet] = {
    val row = session.session.execute(getTweetStatement.bind(tweetId)).one()
    if (row != null)
        Option(
          Tweet(
            row.getUUID("user_id"),
            row.getUUID("tweet_id"),
            row.getTimestamp("created_at"),
            row.getString("content")
          )
        )
    else None
  }

  def create(tweet: Tweet): Option[Tweet] = {
    val statement = createTweetStatement.bind(tweet.tweet_id, tweet.user_id, tweet.content, tweet.created_at)
    val row = session.session.execute(statement).one()
    if (row != null)
      Option(
        Tweet(
          row.getUUID("user_id"),
          row.getUUID("tweet_id"),
          row.getTimestamp("created_at"),
          row.getString("content")
        )
      )
    else None
  }
}