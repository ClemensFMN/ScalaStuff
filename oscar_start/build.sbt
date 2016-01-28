lazy val root = (project in file(".")).
  settings(
    //name := "oscar-cp template",
    scalaVersion := "2.11.4",
    resolvers += "Oscar Snapshots" at "http://artifactory.info.ucl.ac.be/artifactory/libs-release/",
    libraryDependencies += "oscar" %% "oscar-cp" % "3.0.0" withSources()
  )
