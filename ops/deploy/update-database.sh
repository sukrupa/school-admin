#!/bin/sh
BUILD_OVERRIDES="-Dresources.main.dir=install -Dlib.main.dir=lib -Dsrc.sql.dir=install/dbdeploy/sql"
sh install/ant/bin/ant $BUILD_OVERRIDES -buildfile install/build/build.xml db:update $@

