package com.reboundable.com.reboundable.smtp

import java.io.InputStream
import java.lang.String
import org.subethamail.smtp.{MessageContext, MessageHandler}
import io.Source

/**
 * User: ivan
 * Date: 2/16/13
 * Time: 5:32 PM
 */

class PersistingMessageHandler(private val ctx:MessageContext) extends MessageHandler {
  var from:String = _
  var recipient:String = _
  var data:String = _


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
    println("I'm done with this line.  Here's what I have:")
    println("From: " + from)
    println("  To: " + recipient)
    println("Data: " + data)
  }
}