name := "OfertasMas"

version := "1.0"

lazy val `ofertasmas` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc, cache, ws, specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.38",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "io.circe" %% "circe-core" % "0.6.0",
  "io.circe" %% "circe-generic" % "0.6.0",
  "io.circe" %% "circe-parser" % "0.6.0",
  "play-circe" %% "play-circe" % "2.5-0.6.0")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  