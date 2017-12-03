import com.whisk.docker.{DockerContainer, DockerKit, DockerReadyChecker, LogLineReceiver}

import scala.concurrent.duration._

/**
  * Created by prasaadk on 02/12/2017.
  */
trait DockerSparkService extends DockerKit {

  val DefaultSparkWebUI = 4040
  val DefaultSparkUIPort = 8080
  val DefaultWorkerUIPort = 8081
  val DefaultSparkMaster = 7077

  val sparkContainer = DockerContainer("p7hb/docker-spark", Some("spark-int"), Some(Seq("ls -ltr", "start-master.sh")))
    .withHostname("spark")
    .withPorts(DefaultSparkUIPort -> Some(DefaultSparkUIPort),
                DefaultSparkMaster -> Some(DefaultSparkMaster),
                DefaultWorkerUIPort -> Some(DefaultWorkerUIPort),
                DefaultSparkWebUI -> Some(DefaultSparkWebUI))
    .withReadyChecker(DockerReadyChecker.Always)
    .withLogLineReceiver(LogLineReceiver(false, s => println(s"logging: $s")))
    .withCommand("start-all.sh")
//    .withReadyChecker(
//      DockerReadyChecker
//      .HttpResponseCode(DefaultSparkUIPort, "/", Some("localhost"))
//      .within(5000.millis)
//      .looped(20, 1250.millis))

  override def dockerContainers: List[DockerContainer] =
    sparkContainer :: super.dockerContainers
}
