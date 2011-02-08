#!/bin/sh

INPUT_ARGS=$1

if [ -z $INPUT_ARGS ]; then
  COMMANDS=""
else
  COMMANDS="\"$1\""
fi

java -Dlog4j.configuration=logging/log4j.xml -jar hsqldb-sql-runner.jar $COMMANDS
