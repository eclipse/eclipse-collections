#!/bin/bash

set -euxo pipefail

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
  ./mvnw -version
  echo "Completed setting environment"
  ./mvnw install --no-transfer-progress
}

case "$JDK" in
Java14-EA)
  install_jdk_and_run_ea_build ${JDK14_EA_URL}
  ;;
esac
