name := """play-scala-sample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalikejdbc" %% "scalikejdbc"                  % "2.3.5",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.3.5",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.0",
  "mysql" % "mysql-connector-java" % "5.1.38", // official in 34
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"


fork in run := true