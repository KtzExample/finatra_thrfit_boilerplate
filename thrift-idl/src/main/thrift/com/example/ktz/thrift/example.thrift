namespace java org.example.ktz.thriftjava
#@namespace scala org.example.ktz.thriftscala

include "finatra-thrift/finatra_thrift_exceptions.thrift"

struct TUserCar {
    1: required string carName
    2: required i64 serialNumber
}

struct TUserInfo {
    1: required i64 userId
    2: required string userName
    3: required i32 userAge
    4: required bool sex
    5: required TUserCar carName
    6: optional string mothersName
}

service TUserService {
    list<TUserInfo> getAllUserInfo()
    throws (
        1: finatra_thrift_exceptions.ClientError clientError,
        2: finatra_thrift_exceptions.ServerError serverError
    )

    list<TUserInfo> getUserInfoById(i64 userId)
    throws (
        1: finatra_thrift_exceptions.ClientError clientError,
        2: finatra_thrift_exceptions.ServerError serverError
    )

    list<TUserInfo> setUserInfoById(TUserInfo userInfoToSet)
    throws (
        1: finatra_thrift_exceptions.ClientError clientError,
        2: finatra_thrift_exceptions.ServerError serverError
    )

    list<TUserCar> getCarInfoById(i64 userId)
    throws (
        1: finatra_thrift_exceptions.ClientError clientError,
        2: finatra_thrift_exceptions.ServerError serverError
    )
}