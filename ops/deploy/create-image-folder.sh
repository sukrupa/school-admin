#!/bin/sh
BUILD_OVERRIDES="-Dresources.main.dir=install/dbdeploy -Dlib.main.dir=lib -Dsrc.sql.dir=install/dbdeploy/sql"
sh install/ant/bin/ant $BUILD_OVERRIDES -buildfile install/dbdeploy/build.xml img:recreate $@