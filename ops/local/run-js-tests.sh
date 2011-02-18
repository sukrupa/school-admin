#!/bin/bash

discoverFirefoxCommand() {
    local FIREFOX_BINARY
    case $(uname) in
      Linux)
        FIREFOX_BINARY="firefox"
        ;;
      Darwin)
        FIREFOX_BINARY="/Applications/Firefox.app/Contents/MacOS/firefox-bin"
        ;;
      *)
       echo "Unrecognised OS, supported OS's are MacOSX, Linux & SunOS (Solaris)"
       exit -1
    esac

    echo "$FIREFOX_BINARY"
}

FIREFOX=$(discoverFirefoxCommand)
CONFIGFILE=src/test/resources/jsTestDriver.conf

java -jar lib/JsTestDriver.jar --port 4224 --browser $FIREFOX --tests all --config $CONFIGFILE --testOutput target/reports
