#!/bin/bash
findSukrupaServerProcess() {
    PID=""
    case $(uname) in
      Darwin | Linux)
        PID=$(ps aux | grep java | grep school-admin | grep -v org.hsqldb.server.Server | grep -v ant-launcher.jar | awk '{ print $2 }')
        ;;
      *)
       echo "Unrecognised OS, supported OS's are MacOSX, Linux & SunOS (Solaris)"
       exit 1
    esac
}

findSukrupaServerProcessByClassname() {
    PID=""
    case $(uname) in
      Darwin | Linux)
        PID=$(ps aux | grep java | grep org.sukrupa.app.SchoolAdminApp | grep -v ant-launcher.jar | awk '{ print $2 }')
        ;;
      *)
       echo "Unrecognised OS, supported OS's are MacOSX, Linux & SunOS (Solaris)"
       exit 1
    esac
}

stopSukrupaServerClassname() {
    findSukrupaServerProcessByClassname
    if [ -z $PID ]; then
        echo "No running server found with name 'school-admin', nothing to stop."
    else
        echo "Killing process with PID [${PID}]"
        kill -9 $PID
        echo
        echo "Sukrupa Server stopped"
    fi
}

stopSukrupaServer() {
    findSukrupaServerProcess
    if [ -z $PID ]; then
        echo "No running server by classname found, nothing to stop."
    else
        echo "Killing process with PID [${PID}]"
        kill -9 $PID
        echo
        echo "Sukrupa Server stopped"
    fi
}
JAVA_PROCESSES=$(ps aux | grep java | awk '{ print $1 "\t" $2 "\t" $9 "\t" $10 "\t" $11 "\t" $13 }')
echo -e "Running processes:\n$JAVA_PROCESSES"
stopSukrupaServer
stopSukrupaServerClassname
