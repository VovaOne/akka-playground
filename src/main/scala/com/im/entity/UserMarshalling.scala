package com.im.entity

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._


case class Error(message: String)

trait UserMarshalling extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val userFormat2 = jsonFormat2(User)
  implicit val errorFormat = jsonFormat1(Error)

}

object UserMarshalling extends UserMarshalling
