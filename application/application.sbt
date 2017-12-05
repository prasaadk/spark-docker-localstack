libraryDependencies ++= Seq (
  "org.glassfish.hk2" % "hk2-locator" % "2.4.0-b34",
  "org.glassfish.hk2" % "hk2-utils" % "2.4.0-b34",
  "javax.ws.rs" % "javax.ws.rs-api" % "2.0.1",
  "org.apache.spark" %% "spark-sql" % "2.2.0" exclude("org.glassfish.hk2","hk2-locator") exclude("org.glassfish.hk2","hk2-utils") exclude("javax.validation", "validation-api")
)
