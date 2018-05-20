#!/bin/bash
export JAVA_HOME=/c/Program\ Files/Java/jdk1.8.0_171
export PATH=$JAVA_HOME/bin/:$PATH
./mvnw -DskipTests -U install package
