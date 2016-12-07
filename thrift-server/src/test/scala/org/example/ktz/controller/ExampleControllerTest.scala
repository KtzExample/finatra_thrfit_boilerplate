package org.example.ktz.controller

import com.example.ktz.thriftscala.{TUserCar, TUserInfo, TUserService}
import com.twitter.finatra.thrift.EmbeddedThriftServer
import com.twitter.inject.app.TestInjector
import com.twitter.inject.server.FeatureTest
import com.twitter.util.Future
import org.example.ktz.FinatraExampleThriftServer

/**
  * Created by ktz on 2016. 12. 6..
  */
class ExampleControllerTest extends FeatureTest{
  val modules: Seq[Nothing] = Seq.empty
  override val server: EmbeddedThriftServer = new EmbeddedThriftServer(new FinatraExampleThriftServer)
  override val injector = TestInjector(modules: _*)

  val client: TUserService[Future] = server.thriftClient[TUserService[Future]](clientId = "client123")


  "ExampleController" should {
    "getAllUserInfo Well" in {
      println(client.getAllUserInfo().value)
      client.getAllUserInfo().value.size should equal(3)
    }

    "getUserInfoById Well" in {
      println(client.getUserInfoById(1).value)
      client.getUserInfoById(1).value.nonEmpty should equal(true)
    }

    "setUserInfoById Well" in {
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

    "getCarInfoById Well" in {
      println(client.getCarInfoById(1).value)
      client.getCarInfoById(1).value.nonEmpty should equal(true)
    }
  }
}