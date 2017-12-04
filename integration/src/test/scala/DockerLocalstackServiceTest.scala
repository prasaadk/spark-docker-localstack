import com.whisk.docker.impl.spotify.DockerKitSpotify
import com.whisk.docker.scalatest.DockerTestKit
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by prasaadk on 02/12/2017.
  */
class DockerLocalstackServiceTest extends FlatSpec
  with Matchers
  with DockerTestKit
  with DockerKitSpotify
  with DockerLocalstackService {

  "localstack container" should "be ready with log line checker" in {
    isContainerReady(localstackContainer).futureValue shouldBe true
    localstackContainer.getPorts().futureValue.get(4572) should not be empty
    localstackContainer.getIpAddresses().futureValue should not be Seq.empty
  }
}
