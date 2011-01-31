#!/bin/bash

unzip -o $1
sh stop-server.sh
sh start-server.sh

BUILD_OVERRIDES="-Dresources.main.dir=install/dbdeploy -Dlib.main.dir=lib -Dops.deploy.sql.dir=install/dbdeploy/sql"
sh install/ant/bin/ant $BUILD_OVERRIDES -buildfile install/dbdeploy/build.xml db:delete-fake-data
sh install/ant/bin/ant $BUILD_OVERRIDES -buildfile install/dbdeploy/build.xml db:insert-fake-data