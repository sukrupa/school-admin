#!/bin/bash

sh stop-server.sh
sh -c "java -cp "lib/*:web-server.jar" org.sukrupa.platform.WebServer sukrupa.war" &
