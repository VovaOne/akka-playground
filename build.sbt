name := """akka"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= {
  val akkaVersion = "2.4.4"
  Seq(
    "com.typesafe.akka" %% "akka-actor"      % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core"  % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental"  % akkaVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"  % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
    "ch.qos.logback"    %  "logback-classic" % "1.1.3",
    "com.typesafe.akka" %% "akka-testkit"    % akkaVersion   % "test",
    "org.scalatest"     %% "scalatest"       % "2.2.0"       % "test"

  )
}
