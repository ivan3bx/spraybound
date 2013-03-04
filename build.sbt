import AssemblyKeys._

assemblySettings

organization     := "com.reboundable"

name             := "Rebound Server"

version          := "0.9"

scalaVersion := "2.10.0"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

ideaProjectName  := "Rebound Project"

resolvers ++= Seq(
  "Spray Repository"    at "http://repo.spray.io/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "io.spray"            %   "spray-can"     % "1.1-M7",
  "io.spray"            %   "spray-routing" % "1.1-M7",
  "io.spray"            %   "spray-testkit" % "1.1-M7",
  "io.spray"            %%  "spray-json"    % "1.2.3",
  "com.typesafe.akka"   %%  "akka-actor"    % "2.1.0",
  "postgresql"          %   "postgresql"    % "9.1-901.jdbc4",
  "play"                %%  "anorm"         % "2.1.0",
  "com.github.seratch"  %%  "scalikejdbc"   % "[0.5,)",
  "org.slf4j"           %   "slf4j-simple"  % "[1.7,)",
  "org.specs2"          %%  "specs2"        % "1.13" % "test",
  "org.subethamail"     %   "subethasmtp"   % "3.1.3"
)

seq(Revolver.settings: _*)

javaOptions += "-Xmx250m"
