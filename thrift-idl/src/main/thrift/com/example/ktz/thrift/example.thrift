namespace java org.example.ktz.thriftjava
#@namespace scala org.example.ktz.thriftscala

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

    list<TUserInfo> getUserInfoById(i64 userId)

    list<TUserInfo> setUserInfoById(TUserInfo userInfoToSet)

    list<TUserCar> getCarInfoById(i64 userId)
}