#!/bin/bash
findSukrupaServerProcess() {
    PID=$(cat ~/.webserver.pid)
}



stopSukrupaServer() {
    findSukrupaServerProcess
    if [ -z $PID ]; then
        echo "No running server by PID file ~/.webserver.pid found, nothing to stop."
    else
        echo "Killing process with PID [${PID}]"
        kill -9 $PID
        rm ~/.webserver.pid

        echo
        echo "Sukrupa Server stopped"
    fi
}
stopSukrupaServer

JAVA_PROCESSES=$(ps aux | grep java | grep -v grep |awk '{ print $1 "\t" $2 "\t" $9 "\t" $10 "\t" $11 }')
echo -e "Running processes:\n$JAVA_PROCESSES"
