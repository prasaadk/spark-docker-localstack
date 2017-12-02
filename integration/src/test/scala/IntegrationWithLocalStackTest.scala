import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import cloud.localstack.LocalstackTestRunner
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.s3.{AmazonS3, AmazonS3Client}
import org.junit.{Before, Test}
import org.scalatest.FlatSpec

/**
  *
  * This test depends on a localstack process kicked off in the background.
  * Start localstack on command line before running this
  * `localstack start --docker`
  *
  * Created by prasaadk on 02/12/2017.
  */
@RunWith(classOf[LocalstackTestRunner])
class IntegrationWithLocalStackTest extends FlatSpec with AssertionsForJUnit {

  @Test def testLocalS3API() {
    val s3 = new AmazonS3Client(new ProfileCredentialsProvider())
    s3.setEndpoint(LocalstackTestRunner.getEndpointS3())
    val buckets = s3.listBuckets()
    assertResult(3)(buckets.size())
  }
}
