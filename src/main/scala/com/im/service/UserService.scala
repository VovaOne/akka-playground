package com.im.service

import akka.actor.{Props, Actor}
import akka.actor.Actor.Receive
import akka.util.Timeout
import com.im.entity.User

import scala.concurrent.Future

object UserServiceProtocol {
  def props(implicit timeout: Timeout) = Props(new UserService)

  def name = "userService"

  case class GetUser(id: Long)

  case class SaveUser(user: User)

}

class UserService extends Actor {

  import UserServiceProtocol._
  import context._

  override def receive: Receive = {

    case GetUser(id) =>
      def getNewUser: Option[User] = {
        Option(new User(id = id, name = s"name$id"))
      }
      sender ! getNewUser

    case SaveUser(user: User) => sender ! 1

    case _ => ???
  }

}
