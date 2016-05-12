package com.im.api

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.util.Timeout
import com.im.entity.User
import com.im.service.UserServiceProtocol._
import com.im.entity.UserMarshalling


import scala.concurrent.{Future, ExecutionContext}

class RestApi(system: ActorSystem, timeout: Timeout) extends RestRoutes {
  implicit val requestTimeout = timeout

  implicit def executionContext = system.dispatcher

  override def createUserService(): ActorRef = system.actorOf(props, name)
}


trait RestRoutes extends UserServiceApi {

  def routes: Route = userRoute

  import UserMarshalling._

  val userRoute =
    get {
      pathPrefix("user" / LongNumber) { id =>
        pathEndOrSingleSlash {
          get {
            onSuccess(getUser(id)) { user =>
              complete(user)
            }
          }
        }
      }
    } ~
      path("user") {
        pathEndOrSingleSlash {
          post {
            entity(as[User]) { user =>
              onSuccess(saveUser(user)) { id =>
                complete(OK, s"$id")
              }
            }
          }
        }
      }
}

trait UserServiceApi {

  import akka.pattern.ask
  import com.im.service.UserServiceProtocol._

  def createUserService(): ActorRef

  implicit def executionContext: ExecutionContext

  implicit def requestTimeout: Timeout

  lazy val userService: ActorRef = createUserService()

  def getUser(id: Long): Future[Option[User]] = {
    userService.ask(GetUser(id)).mapTo[Option[User]]
  }

  def saveUser(user: User): Future[Long] = Future(1)
}
