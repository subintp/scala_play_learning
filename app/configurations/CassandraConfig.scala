package configurations

import com.typesafe.config.ConfigFactory

trait CassandraConfig {
  val cassandraConf = ConfigFactory.load("cassandra.conf")

  lazy val cassandraKeyspace = cassandraConf.getString("cassandra.keyspace")
  lazy val cassandraHosts = cassandraConf.getString("cassandra.hosts").split(",").toSeq.map(_.trim)
  lazy val cassandraPort = cassandraConf.getInt("cassandra.port")
}
