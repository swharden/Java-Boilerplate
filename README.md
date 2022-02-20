# Java Boilerplate

This repository is an example Java app with tests and continuous integration using GitHub Actions. It uses Maven to manage packages and testing.

## Setting-Up the Development Environment

These are the steps I took to get the Java SDK and Maven running on Windows.

### Get JDK
* https://www.oracle.com/java/technologies/downloads/

### Get Maven
* https://maven.apache.org/download.cgi
* Unzip and store somewhere important

### Add Environment Variables
* `JAVA_HOME` = `C:\Program Files\Java\jdk-17.0.2`
* `MAVEN_HOME` = `C:\Users\scott\Documents\important\apps\apache-maven-3.8.4`

### Add Maven bin folder to your system path
* `%MAVEN_HOME%\bin`

### Confirm Maven Works
```sh
mvn -v
```

### Create a Skeleton App

* This command creates a `my-app` folder with a basic project in it.
* I made the contents of this folder the base of my repository.

```
mvn -B archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
```

## References
* [Maven: Getting Started](https://maven.apache.org/guides/getting-started/)
* [How to install Maven on Windows](https://mkyong.com/maven/how-to-install-maven-in-windows/)