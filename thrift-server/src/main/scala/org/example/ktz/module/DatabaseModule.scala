package org.example.ktz.module

import com.example.ktz.thriftscala.{TUserCar, TUserInfo}
import com.google.inject.{Provides, Singleton}
import com.twitter.inject.{Logging, TwitterModule}
import org.example.ktz.persistance.FakeDatabase

/**
  * Created by ktz on 2016. 12. 5..
  */
object DatabaseModule extends TwitterModule with Logging{

  @Provides
  @Singleton
  def provideDatabase: FakeDatabase = FakeDatabase(
    Map(1.toLong -> TUserInfo(
      1,
      "martin",
      29,
      true,
      TUserCar(
        "K5",
        123456
      ),
      None
    ),
    2.toLong -> TUserInfo(
      2,
      "Lea",
      30,
      true,
      TUserCar(
        "K3",
        34521
      ),
      Some("Mom")
    ),
    3.toLong -> TUserInfo(
      3,
      "Liam",
      35,
      true,
      TUserCar(
        "K9",
        12314
      ),
      Some("Father")
    )
    )
  )
}
