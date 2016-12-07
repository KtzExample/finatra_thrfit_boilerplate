package org.example.ktz

import com.twitter.finatra.thrift.ThriftServer
import com.twitter.finatra.thrift.filters._
import com.twitter.finatra.thrift.routing.ThriftRouter
import org.example.ktz.controller.ExampleController
import org.example.ktz.module.DatabaseModule

/**
  * Created by ktz on 2016. 12. 5..
  */

object FinatraExampleThriftServerMain extends FinatraExampleThriftServer

class FinatraExampleThriftServer extends ThriftServer{
  override val name: String = "ktzExample"

  override val modules = Seq(DatabaseModule)

  override val defaultFinatraThriftPort: String = ":9090"

  //admin page: http://localhost:9990/admin

  override protected def configureThrift(router: ThriftRouter): Unit = {
    router
      .filter[LoggingMDCFilter]
      .filter[TraceIdMDCFilter]
      .filter[ThriftMDCFilter]
      .filter[AccessLoggingFilter]
      .filter[StatsFilter]
      .add[ExampleController]
  }
}
