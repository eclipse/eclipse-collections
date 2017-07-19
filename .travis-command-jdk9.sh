#!/bin/bash

set -euo pipefail

# Prevent accidental execution outside of Travis:
if [ -z "${TRAVIS+false}" ]
then
  echo "TRAVIS environment variable is not set"
  exit 1
fi

# Switch to desired JDK, download if required:
function install_jdk {
  JDK_URL="http://download.java.net/java/jdk9/archive/178/binaries/jdk-9+178_linux-x64_bin.tar.gz"

  FILENAME="jre-9+178_linux-x64_bin.tar.gz"

  rm -rf /tmp/jdk/$JDK
  mkdir -p /tmp/jdk/$JDK

  if [ ! -f "/tmp/jdk/$FILENAME" ]
  then
    curl -L $JDK_URL -o /tmp/jdk/$FILENAME
  fi

  echo "Extracting archive"
  tar -xzf /tmp/jdk/$FILENAME -C /tmp/jdk/$JDK --strip-components 1
  echo "Completed extracting archive"

  if [ -z "${2+false}" ]
  then
    export JAVA_HOME="/tmp/jdk/$JDK"
    export JDK_HOME="${JAVA_HOME}"
    export JAVAC="${JAVA_HOME}/bin/javac"
    export PATH="${JAVA_HOME}/bin:${PATH}"
  fi
  $JAVA_HOME/bin/java -version
  echo "Completed setting environment"
}

source $HOME/.jdk_switcher_rc
case "$JDK" in
Java8)
  echo "Java 8 build already executed!"
  ;;
Java9)
  install_jdk
  ;;
esac

