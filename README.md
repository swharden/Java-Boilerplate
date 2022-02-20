# Java Boilerplate

[![CI with Maven](https://github.com/swharden/Java-Boilerplate/actions/workflows/ci.yaml/badge.svg)](https://github.com/swharden/Java-Boilerplate/actions/workflows/ci.yaml)

**This repository contains simple Java application.**
* [JUnit](http://junit.org) is used for testing
* [Maven](https://maven.apache.org) is used to manage packaging
* [GitHub Actions](https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven) is used for CI (continuous integration, building and testing in the cloud)

## Source Code

The core of this app is just two source code files: the app, and a single test

* [`App.java`](/src/main/java/com/mycompany/app/App.java)
* [`AppTest.java`](/src/test/java/com/mycompany/app/AppTest.java)

## Setting-Up the Development Environment

These are the steps I took to get this project up and running using Windows.

I used [Visual Studio Code](https://code.visualstudio.com/) and [Eclipse](https://www.eclipse.org/ide/) during testing.

### Get the JDK
* https://www.oracle.com/java/technologies/downloads/

### Set-Up Maven

#### Download
* https://maven.apache.org/download.cgi
* Unzip and store somewhere important

#### Add Environment Variables
* `JAVA_HOME` = `C:\Program Files\Java\jdk-17.0.2`
* `MAVEN_HOME` = `C:\Users\scott\Documents\important\apps\apache-maven-3.8.4`

#### Add Maven bin folder to your system path
* `%MAVEN_HOME%\bin`

#### Confirm Maven Works
```sh
mvn -v
```

### Create a Quickstart App

* This command creates a `my-app` folder with a basic project in it.
* I made the contents of this folder the base of my repository.

```
mvn -B archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
```

### Java Project Structure

Notice the pairing of folder hierarchy between Java classes and their tests.

```
my-app
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- mycompany
    |               `-- app
    |                   `-- App.java
    `-- test
        `-- java
            `-- com
                `-- mycompany
                    `-- app
                        `-- AppTest.java
```

### Ignore Files and Folders

You may wish to edit your source control's ignore file so Java-related files and folders are not tracked.

Refer to this project's [`.gitignore`](.gitignore) file to see a list of ignored names.


### Customize the Project Object Model (POM)

See [Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html) for details

* **`groupId`** is the unique identifier of the organization or group that created the project.
  * The groupId is one of the key identifiers of a project and is typically based on the fully qualified domain name of your organization.
  * For example `org.apache.maven.plugins` is the designated groupId for all Maven plugins.

* **`artifactId`** is the unique base name of the primary artifact being generated by this project.
  * The primary artifact for a project is typically a JAR file. 
  * Secondary artifacts like source bundles also use the artifactId as part of their final name. 
  * Typical artifacts have the form `myapp-1.0.jar`

* **`version`** is the version of the artifact generated by the project.

* **`name`** is the display name used for the project.

* **`url`** is where the project's site can be found.

* **`properties`** contains value placeholders accessible anywhere within a POM.

* **`dependencies`** is the cornerstone of the POM.

* **`build`** handles things like declaring your project's directory structure and managing plugins.

### Build and Test (command line)
* `mvn compile` - compile only
* `mvn test` - run tests
* `mvn verify` - ensure all steps (compile/test/package) succeed
* `mvn exec:java` - run the application (see note below)

Execution requires editing [pom.xml](pom.xml) to add a plugin:

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <version>1.2.1</version>
  <executions>
      <execution>
          <goals>
              <goal>java</goal>
          </goals>
      </execution>
  </executions>
  <configuration>
      <mainClass>com.mycompany.app.App</mainClass>
  </configuration>
</plugin>
```

### Open in Eclipse
* `File` > `Open Projects from File System`
* Set `Import source` to the root of this repository
* Click `Finish`

### Continuous Integration with GitHub Actions

* I separate `compile` from `test` so if one fails I can tell from the job name which one it is

* Adding `--no-transfer-progress` reduces noise in the CI logs

* Consider which Java version to use for testing. Older versions are faster because they are more likely cached on the CI server.

```yaml
name: CI

on:
  workflow_dispatch:
  push:
  pull_request:

jobs:
  verify:
    name: Build and Test
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v2
      - name: Get JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: "11"
          distribution: "temurin"
          cache: "maven"
      - name: 🛠️ Compile
        run: mvn --batch-mode --no-transfer-progress compile
      - name: 🧪 Test
        run: mvn --batch-mode --no-transfer-progress test
```

* See [ci.yaml](.github/workflows/ci.yaml) for actual workflow file used by this repository

* See [actions/setup-java](https://github.com/actions/setup-java) for Java action documentation

* See [Building and testing Java with Maven](https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven) for Maven-specific Action documentation

## References
* [Maven: Getting Started](https://maven.apache.org/guides/getting-started/)
* [How to install Maven on Windows](https://mkyong.com/maven/how-to-install-maven-in-windows/)
* [Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)
* [JUnit user guide](https://junit.org/junit5/docs/current/user-guide/)
* [GitHub's Maven.gitignore](https://github.com/github/gitignore/blob/main/Maven.gitignore)