import sbt.Keys._

lazy val versions = new {
  val finatra = "2.6.0"
  val guice = "4.0"
  val logback = "1.1.7"
  val mockito = "1.9.5"
  val scalatest = "3.0.0"
  val scalacheck = "1.13.4"
  val specs2 = "2.3.12"
}

lazy val baseSetting = Seq(
  scalaVersion := "2.11.8",
  version := "1.0",
  libraryDependencies ++= Seq(
    "com.twitter" %% "finatra-thrift" % versions.finatra,
    "ch.qos.logback"  % "logback-classic" % versions.logback,
    "ch.qos.logback" % "logback-classic" % versions.logback % "test",

    "com.twitter" %% "finatra-http" % versions.finatra % "test",
    "com.twitter" %% "inject-server" % versions.finatra % "test",
    "com.twitter" %% "inject-app" % versions.finatra % "test",
    "com.twitter" %% "inject-core" % versions.finatra % "test",
    "com.twitter" %% "inject-modules" % versions.finatra % "test",
    "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

    "com.twitter" %% "finatra-thrift" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests",
    "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests",

    "org.mockito" % "mockito-core" % versions.mockito % "test",
    "org.scalacheck" %% "scalacheck" % versions.scalacheck % "test",
    "org.scalatest" %% "scalatest" % versions.scalatest % "test",
    "org.specs2" %% "specs2" % versions.specs2 % "test"
  ),
  fork in run := true,
  fork in Test := true
)
scalaVersion := "2.11.8"

lazy val `thrift-server` = (project in file("thrift-server")).settings(baseSetting).settings(
  name:= "thrift-server"
).dependsOn(`thrift-idl`)

lazy val `thrift-idl` = (project in file("thrift-idl")).settings(baseSetting).settings(
  name:= "thrift-idl",
  scroogeThriftDependencies in Compile := Seq(
    "finatra-thrift_2.11"
  )
)