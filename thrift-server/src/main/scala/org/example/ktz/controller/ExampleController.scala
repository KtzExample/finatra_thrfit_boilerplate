package org.example.ktz.controller

import org.example.ktz.thriftscala.TUserService
import org.example.ktz.thriftscala.TUserService.{GetAllUserInfo, GetCarInfoById, GetUserInfoById, SetUserInfoById}
import com.google.inject.Inject
import com.twitter.finagle.Service
import com.twitter.finatra.thrift.Controller
import com.twitter.util.Future
import org.example.ktz.persistance.FakeDatabase

/**
  * Created by ktz on 2016. 12. 5..
  */
class ExampleController @Inject()(fakeDatabase: FakeDatabase) extends Controller with TUserService.BaseServiceIface{
  override val getAllUserInfo: Service[GetAllUserInfo.Args, GetAllUserInfo.Result] = handle(GetAllUserInfo){_ =>
    Future.value(fakeDatabase.userInfo.toList.map(_._2))
  }

  override val getUserInfoById: Service[GetUserInfoById.Args, GetUserInfoById.Result] = handle(GetUserInfoById){args =>
    Future.value(fakeDatabase.userInfo.get(args.userId).toSeq)
  }

  override val setUserInfoById: Service[SetUserInfoById.Args, SetUserInfoById.Result] = handle(SetUserInfoById){args =>
    Future.value(fakeDatabase.set(args.userInfoToSet).toSeq)
  }

  override val getCarInfoById: Service[GetCarInfoById.Args, GetCarInfoById.Result] = handle(GetCarInfoById){args =>
    Future.value(fakeDatabase.userInfo.get(args.userId).map(_.carName).toSeq)
  }
}