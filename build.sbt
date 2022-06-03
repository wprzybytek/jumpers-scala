ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "jumpers-scala"
  )

val osName: SettingKey[String] = SettingKey[String]("osName")

osName := (System.getProperty("os.name") match {
  case name if name.startsWith("Linux") => "linux"
  case name if name.startsWith("Mac") => "mac"
  case name if name.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
})

libraryDependencies += "org.openjfx" % "javafx-base" % "18.0.1" classifier osName.value
libraryDependencies += "org.openjfx" % "javafx-controls" % "18.0.1" classifier osName.value
libraryDependencies += "org.openjfx" % "javafx-fxml" % "18.0.1" classifier osName.value
libraryDependencies += "org.openjfx" % "javafx-graphics" % "18.0.1" classifier osName.value
libraryDependencies += "org.openjfx" % "javafx-web" % "18.0.1" classifier osName.value
libraryDependencies += "org.openjfx" % "javafx-media" % "18.0.1" classifier osName.value
