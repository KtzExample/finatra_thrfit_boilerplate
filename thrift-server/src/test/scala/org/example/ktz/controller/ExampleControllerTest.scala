package org.example.ktz.controller

import org.example.ktz.thriftscala.{TUserCar, TUserInfo, TUserService}
import com.twitter.finatra.thrift.EmbeddedThriftServer
import com.twitter.inject.server.FeatureTest
import com.twitter.util.Future
import org.example.ktz.FinatraExampleThriftServer

/**
  * Created by ktz on 2016. 12. 6..
  */
class ExampleControllerTest extends FeatureTest{
  val modules: Seq[Nothing] = Seq.empty
  override lazy val server: EmbeddedThriftServer = new EmbeddedThriftServer(twitterServer = new FinatraExampleThriftServer)

  val client: TUserService[Future] = server.thriftClient[TUserService[Future]](clientId = "client123")


    test("getAllUserInfo Well") {
      println(client.getAllUserInfo().value)
      client.getAllUserInfo().value.size should equal(3)
    }

    test("getUserInfoById Well") {
      println(client.getUserInfoById(1).value)
      client.getUserInfoById(1).value.nonEmpty should equal(true)
    }

    test("setUserInfoById Well") {
      client.setUserInfoById(TUserInfo(
        3,
        "Liam",
        40,
        true,
        TUserCar(
          "K9",
          12314
        ),
        Some("Father"))).value.nonEmpty should equal(true)
    }

    test("getCarInfoById Well") {
      println(client.getCarInfoById(1).value)
      client.getCarInfoById(1).value.nonEmpty should equal(true)
    }
}