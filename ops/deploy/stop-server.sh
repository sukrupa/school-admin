#!/bin/bash
findSukrupaServerProcess() {
    PID=""
    case $(uname) in
      Darwin | Linux)
        PID=$(ps aux | grep java | grep school-admin | grep -v ant-launcher.jar | awk '{ print $2 }')
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
        kill -9 $PID
        echo
        echo "Sukrupa Server stopped"
    fi
}

stopSukrupaServer
