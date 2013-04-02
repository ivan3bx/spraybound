package com.reboundable

import anorm._
import anorm.SqlParser._
import java.sql.DriverManager
import java.sql.Connection
import scalikejdbc.ConnectionPool


object Database {

  val settings = new Settings(com.typesafe.config.ConfigFactory.load())

  Class.forName("org.postgresql.Driver").newInstance
  ConnectionPool.singleton("jdbc:postgresql://localhost/${settings.dbname}", settings.dbuser, settings.dbpass)

  def getConnection(url: String) = {
    DriverManager.getConnection(url)
  }

  def withConnection[A](block: Connection => A): A = {
    val connection: Connection = ConnectionPool.borrow()

    try {
      block(connection)
    } finally {
      connection.close()
    }
  }

  def dropAndCreate() {
    drop()
    create()
  }

  def drop() = {
    withConnection {
      implicit connection: Connection =>
      SQL("""
        DROP TABLE IF EXISTS email_messages;
      """).executeUpdate()
    }
  }

  def create() = {
    withConnection {
      implicit connection: Connection =>
      SQL("""
        CREATE TABLE email_messages (
          id SERIAL PRIMARY KEY,
          author varchar(255) NOT NULL,
          recipients varchar(255) NOT NULL,
          contents text NOT NULL
        );
      """).executeUpdate()
    }
  }
}
