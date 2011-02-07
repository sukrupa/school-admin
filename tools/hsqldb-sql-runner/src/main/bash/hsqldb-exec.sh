#!/bin/sh

java -Dlog4j.configuration=logging/log4j.xml -jar hsqldb-sql-runner.jar $*
