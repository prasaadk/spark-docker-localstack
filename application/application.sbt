libraryDependencies ++= Seq (
  "org.apache.spark" %% "spark-sql" % "2.2.0" exclude("org.glassfish.hk2", "hk2-utils") exclude("org.glassfish.hk2", "hk2-locator") exclude("javax.validation", "validation-api"),
  "org.apache.hadoop" % "hadoop-aws" % "2.7.4"
)