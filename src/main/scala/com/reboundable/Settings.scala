package com.reboundable

import com.typesafe.config._

class Settings(config: Config) {
  config.checkValid(ConfigFactory.defaultReference())

  val dbuser = config.getString("db.username")
  val dbpass = config.getString("db.password")
  val dbname = config.getString("db.database")
}
