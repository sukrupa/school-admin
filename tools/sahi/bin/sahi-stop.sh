#!/bin/bash

findSahiServerProcess() {
    PID=$(cat ~/.sahi-server.pid)
}

stopSahiServer() {
    findSahiServerProcess
    if [ -z $PID ]; then
        echo "No running server by PID file ~/.sahi-server.pid found, nothing to stop."
    else
        echo "Killing process with PID [${PID}]"
        kill -9 $PID
        rm ~/.sahi-server.pid

        echo
        echo "Sahi Server stopped"
    fi
}
stopSahiServer