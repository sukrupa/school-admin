#!/bin/bash
findSukrupaServerProcess() {
	SUKRUPA_WEB_SERVER=web-server.jar
    case $(uname) in
      Darwin | Linux)
        PID=$(ps aux | grep $SUKRUPA_WEB_SERVER | grep java | grep -v grep | awk '{ print $2 }')
        ;;
      *)
       echo "Unrecognised OS, supported OS's are MacOSX, Linux & SunOS (Solaris)"
       exit 1
    esac

}


stopSukrupaServer() {
    findSukrupaServerProcess
    if [ -z $PID ]; then
        echo "No running server found, nothing to stop."
    else
        echo "Killing process with PID [${PID}]"
        kill $PID
        echo
        echo "Sukrupa Server stopped"
    fi
}

stopSukrupaServer