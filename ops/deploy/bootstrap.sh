#!/bin/bash

unzip -o $1
sh stop-server.sh
nohup java -cp "lib/*:web-server.jar" org.sukrupa.app.SchoolAdminApp sukrupa.war >> sukrupa.log &

BUILD_OVERRIDES="-Dresources.main.dir=install/dbdeploy -Dlib.main.dir=lib -Dops.deploy.sql.dir=install/dbdeploy/sql"
sh install/ant/bin/ant $BUILD_OVERRIDES -buildfile install/dbdeploy/build.xml db:delete-fake-data $@
sh install/ant/bin/ant $BUILD_OVERRIDES -buildfile install/dbdeploy/build.xml db:insert-fake-data $@