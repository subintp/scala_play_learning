package dao

import javax.inject.{Inject, Singleton}
import storage.CassandraSession

@Singleton
class TweetDao @Inject() (session: CassandraSession)  {
  def findAllTweets: Unit = {
    val tweets = session.session.execute("select * from twitter.tweets limit 2").all()
    println("Hello Tweets")
    println(tweets)
  }
}