import com.whisk.docker.{DockerContainer, DockerKit, DockerReadyChecker}

/**
  * Created by prasaadk on 02/12/2017.
  */
trait DockerLocalstackService extends DockerKit {

  val S3Port = 4572

  val localstackContainer = DockerContainer("localstack/localstack")
    .withPorts(S3Port -> Some(S3Port))
    .withReadyChecker(DockerReadyChecker.Always)

  override def dockerContainers: List[DockerContainer] =
    localstackContainer :: super.dockerContainers
}
