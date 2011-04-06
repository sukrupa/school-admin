#!/bin/sh
BUILD_OVERRIDES="-Dresources.main.dir=install"
sh install/ant/bin/ant $BUILD_OVERRIDES -buildfile install/build.xml img:recreate $@