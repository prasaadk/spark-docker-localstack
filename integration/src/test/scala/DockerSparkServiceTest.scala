import com.whisk.docker.DockerFactory
import com.whisk.docker.impl.spotify.DockerKitSpotify
import com.whisk.docker.scalatest.DockerTestKit
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.time.{Second, Seconds, Span}

/**
  * Created by prasaadk on 02/12/2017.
  */
class SparkContainerTest extends FlatSpec
  with Matchers
  with DockerTestKit
  with DockerKitSpotify
  with DockerSparkService {

  "spark master" should "be ready with log line checker" in {
    isContainerReady(sparkContainer).futureValue shouldBe true
    sparkContainer.getPorts().futureValue.get(7077) should not be empty
    sparkContainer.getPorts().futureValue.get(8080) should not be empty
    sparkContainer.getIpAddresses().futureValue should not be Seq.empty
  }
}
