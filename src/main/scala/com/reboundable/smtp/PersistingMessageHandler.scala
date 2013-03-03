package com.reboundable.smtp

import java.io.InputStream
import java.lang.String
import org.subethamail.smtp.{ MessageContext, MessageHandler }
import io.Source
import anorm._
import anorm.SqlParser._
import com.reboundable.Database
import java.sql.Connection

/**
 * User: ivan
 * Date: 2/16/13
 * Time: 5:32 PM
 */

class PersistingMessageHandler(private val ctx: MessageContext) extends MessageHandler {
  var from: String = _
  var recipient: String = _
  var data: String = _

  def from(from: String) {
    this.from = from
  }

  def recipient(recipient: String) {
    this.recipient = recipient
  }

  def data(data: InputStream) {
    this.data = Source.fromInputStream(data).mkString("")
  }

  def done() {
    println("Here's what I will insert:")
    println("From: " + from)
    println("  To: " + recipient)
    println("Data: " + data)
    save()
  }

  object DB {
  }

  def save() = {

    Database.withConnection {
      implicit connection: Connection =>
        val result: Option[Long] = SQL(
          """
      INSERT INTO email_messages(author, recipients, contents")
      values ({author}, {recipients}, {contents})
      """)
          .on('author -> from, 'recipients -> recipient, 'contents -> data)
          .executeInsert()
    }

    // Message.insert(from, recipient, data)

  }
}