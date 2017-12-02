lazy val commonSettings = Seq (
  organization := "demo.sparkapp",
  version := "1.0",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file("root"))
  .settings(commonSettings: _*)
  .aggregate(application, integration)

lazy val application = (project in file("application"))
  .settings(commonSettings: _*)
  .settings(
    name := "application"
  )

lazy val integration = (project in file("integration"))
  .settings(commonSettings: _*)
  .dependsOn(application)
  .settings(
    name := "integration"
  )