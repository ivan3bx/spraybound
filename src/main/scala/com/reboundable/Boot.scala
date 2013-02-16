package com.reboundable

import com.reboundable.smtp.PersistingMessageHandlerFactory
import spray.can.server.SprayCanHttpServerApp
import akka.actor.Props
import org.subethamail.smtp.server.SMTPServer
import org.subethamail.smtp.MessageHandlerFactory


object Boot extends App with SprayCanHttpServerApp {
  println("It is what I say it is")

  initializeWeb()
  initializeSmtp()

  def initializeWeb() {
    val service = system.actorOf(Props[MyServiceActor], "my-service")
    newHttpServer(service) ! Bind(interface = "localhost", port = 8080)
  }


  def initializeSmtp() {
    val factory: MessageHandlerFactory = new PersistingMessageHandlerFactory();
    val smtpServer: SMTPServer = new SMTPServer(factory)
    smtpServer.setPort(25000)
    smtpServer.start()
  }

}