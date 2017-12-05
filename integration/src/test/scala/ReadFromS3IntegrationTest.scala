import cloud.localstack.LocalstackTestRunner
import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.auth.profile.internal.AwsProfileNameLoader
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.{AmazonS3Client, AmazonS3ClientBuilder}
import com.amazonaws.util.StringUtils
import com.whisk.docker.impl.spotify.DockerKitSpotify
import com.whisk.docker.scalatest.DockerTestKit
import org.apache.http.impl.conn.DefaultHttpResponseParser
import org.junit.{Before, Test}
import org.scalatest._

/**
  * Created by prasaadk on 04/12/2017.
  */
class ReadFromS3IntegrationTest extends FlatSpec
  with Matchers
  with GivenWhenThen
  with DockerTestKit
  with DockerKitSpotify
  with DockerLocalstackService {

  "Application " should "be able to list buckets" in {
    Given("s3 endpoint is ready")
      isContainerReady(localstackContainer).futureValue shouldBe true
      localstackContainer.getPorts().futureValue.get(4572) should not be empty
      localstackContainer.getIpAddresses().futureValue should not be Seq.empty
    And("one bucket is created")
    val cc = new ClientConfiguration()
      .withMaxErrorRetry (10)
      .withConnectionTimeout (10000)
      .withSocketTimeout (10000)
      .withTcpKeepAlive (true);
      val s3 = AmazonS3ClientBuilder.standard()
        .withClientConfiguration(cc)
        .withPathStyleAccessEnabled(true)
        .withEndpointConfiguration(new EndpointConfiguration("http://localhost:4572","eu-west-1"))
        .withCredentials(new ProfileCredentialsProvider("default")).build;
      val bucket = s3.createBucket("testbucket")
    When("bucket is listed")
      val buckets = s3.listBuckets()
    Then("it should return one bucket")
      assertResult(1)(buckets.size())
  }
}
