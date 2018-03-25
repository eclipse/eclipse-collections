#!/bin/bash

set -e
set +u

# Prevent accidental execution outside of Travis:
if [ -z "${TRAVIS+false}" ]
then
  echo "TRAVIS environment variable is not set"
  exit 1
fi

# Switch to desired JDK, download if required:
function install_jdk_and_run_ea_build {
  if [[ -z "$1" ]];
  then
    echo "JDK URL is not set. Exiting!"
    exit 0
  fi

  JDK_URL=$1
  echo "Downloading JDK from $JDK_URL"

  FILENAME="${JDK_URL##*/}"

  rm -rf /tmp/jdk/$JDK
  mkdir -p /tmp/jdk/$JDK

  if [ ! -f "/tmp/jdk/$FILENAME" ]
  then
    curl -L $JDK_URL -o /tmp/jdk/$FILENAME
  fi

  echo "Extracting archive"
  tar -xzf /tmp/jdk/$FILENAME -C /tmp/jdk/$JDK --strip-components 1
  echo "Completed extracting archive"

  export JAVA_HOME="/tmp/jdk/$JDK"
  export JDK_HOME="${JAVA_HOME}"
  export JAVAC="${JAVA_HOME}/bin/javac"
  export PATH="${JAVA_HOME}/bin:${PATH}"

  $JAVA_HOME/bin/java -version
  echo "export MAVEN_OPTS='-Dmaven.repo.local=$HOME/.m2/repository -Xmx2g -XX:MaxPermSize=2048m'\" > ~/.mavenrc"
  mvn -version
  echo "Completed setting environment"
  mvn install --projects '!scala-unit-tests,!jmh-scala-tests,!jmh-tests,!p2-repository' --batch-mode --show-version -Djacoco.skip=true
}

case "$JDK" in
Java8)
  echo "Java 8 already exists in Travis!"
  ;;
Java9)
  echo "Java 9 already exists in Travis!"
  ;;
Java10)
  install_jdk_and_run_ea_build "https://download.java.net/java/GA/jdk10/10/binaries/openjdk-10_linux-x64_bin.tar.gz"
  ;;
Java11-EA)
  install_jdk_and_run_ea_build ${JDK11_EA_URL}
  ;;
esac
