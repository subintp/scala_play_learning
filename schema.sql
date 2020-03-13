CREATE KEYSPACE twitter WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 }

CREATE TABLE tweets (
   user_id UUID,
   tweet_id UUID,
   created_at timestamp,
   content text,
   PRIMARY KEY((user_id), tweet_id, created_at)
);