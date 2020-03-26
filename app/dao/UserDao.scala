package dao

import java.util.UUID

import javax.inject.{Inject, Singleton}
import storage.CassandraSession
import models.{Tweet, User}

@Singleton
class UserDao @Inject()(session: CassandraSession) {

  lazy val getUserQuery =
    """
      |SELECT * FROM twitter.users
      |WHERE name = ? AND
      |WHERE encrypted_password = ?
      |LIMIT 1
      |ALLOW FILTERING
    """.stripMargin

  lazy val getUserStatement = session.session.prepare(getUserQuery)

  def find(name: String, encrypted_password: String): Option[User] = {
    val statement = getUserStatement.bind(name, encrypted_password)
    val user = session.session.execute(statement).one()
    if (user != null)
      Option(User(user.getString("name"), user.getString("encrypted_password")))
    else None
  }
}
