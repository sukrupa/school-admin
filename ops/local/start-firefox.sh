#!/bin/sh
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

echo "Going to launch firefox with [$FIREFOX]"

$FIREFOX $* &
exit $?