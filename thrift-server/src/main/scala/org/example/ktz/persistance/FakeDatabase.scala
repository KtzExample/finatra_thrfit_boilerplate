package org.example.ktz.persistance

import com.example.ktz.thriftscala.TUserInfo

/**
  * Created by ktz on 2016. 12. 5..
  */
case class FakeDatabase(var userInfo: Map[Long, TUserInfo]) {
  def set(tUserInfo: TUserInfo): Option[TUserInfo] = userInfo.get(tUserInfo.userId) match {
    case Some(_) =>
      userInfo.filter(_._1 != tUserInfo.userId) ++ Map(tUserInfo.userId -> tUserInfo)
      Some(tUserInfo)
    case None =>
      None
  }
}
