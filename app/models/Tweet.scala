package models

import java.sql.Timestamp
import java.util.UUID

case class Tweet(user_id: UUID, tweet_id: UUID, created_at: Timestamp, content: String)
