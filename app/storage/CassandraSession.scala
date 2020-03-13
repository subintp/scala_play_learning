package storage

import com.datastax.driver.core.{Cluster, HostDistance, PoolingOptions, Session}
import configurations.CassandraConfig
import javax.inject.Singleton

@Singleton
class CassandraSession extends CassandraConfig {

  lazy val poolingOptions: PoolingOptions = {
    new PoolingOptions()
      .setConnectionsPerHost(HostDistance.LOCAL, 4, 10)
      .setConnectionsPerHost(HostDistance.REMOTE, 2, 4)
  }

  lazy val cluster: Cluster = {
    val builder = Cluster.builder()
    for (cp <- cassandraHosts) builder.addContactPoint(cp)
    builder.withPort(cassandraPort)
    builder.withPoolingOptions(poolingOptions)

    builder.build()
  }

  lazy val session: Session = cluster.connect()
}