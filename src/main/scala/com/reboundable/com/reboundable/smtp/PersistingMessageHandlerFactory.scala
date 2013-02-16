package com.reboundable.com.reboundable.smtp

import org.subethamail.smtp.{MessageHandler, MessageContext, MessageHandlerFactory}


/**
 * User: ivan
 * Date: 2/16/13
 * Time: 5:21 PM
 */

class PersistingMessageHandlerFactory extends MessageHandlerFactory {
  def create(ctx: MessageContext): MessageHandler = new PersistingMessageHandler(ctx)
}