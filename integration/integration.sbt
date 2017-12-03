libraryDependencies ++= Seq (
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  "junit" % "junit" % "4.12" % Test,
  "cloud.localstack" % "localstack-utils" % "0.1.7" % Test,
  "com.whisk" %% "docker-testkit-scalatest" % "0.9.5",
  "com.whisk" %% "docker-testkit-impl-spotify" % "0.9.5" exclude("org.glassfish.hk2","hk2-locator") exclude("org.glassfish.hk2","hk2-utils")
)