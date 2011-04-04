#!/bin/sh
sh install/ant/bin/ant $BUILD_OVERRIDES -buildfile install/dbdeploy/build.xml img:recreate $@