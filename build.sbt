name := "ebean-j2cache"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.avaje.ebeanorm" % "avaje-ebeanorm" % "7.6.1",
  "de.ruedigermoeller" % "fst" % "2.42",
  "net.sf.ehcache" % "ehcache-core" % "2.6.11",
  //"org.slf4j" % "slf4j-api" % "1.7.22",
  //"org.slf4j" % "slf4j-log4j12" % "1.7.22",
  //"ch.qos.logback" % "logback-core" % "1.1.7",
  "redis.clients" % "jedis" % "2.8.2",
  "com.h2database" % "h2" % "1.4.194"
)