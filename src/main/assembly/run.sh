#!/bin/bash

if type -p java; then
    echo found java executable in PATH
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    echo found java executable in JAVA_HOME     
    _java="$JAVA_HOME/bin/java"
else
    echo "No java found. Please install at least JRE 1.8."
    exit
fi

if [[ "$_java" ]]; then
    JAVA_VER=$(java -version 2>&1 | grep -i version | sed 's/.*version ".*\.\(.*\)\..*"/\1/; 1q')
    if [ "$JAVA_VER" -gt 7 ]; then
        java $PARAMS -jar ${project.build.finalName}.jar
    else         
        echo "You need to have Java 1.8 and above. You may export JAVA_HOME=<correct path of java8> and try again."
        exit
    fi
fi

