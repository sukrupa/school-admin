#!/bin/bash

sh stop-server.sh
nohup java -cp "lib/*:web-server.jar" org.sukrupa.app.SchoolAdminApp sukrupa.war &
echo "I, suhas, should be back at the command line"
echo "ME TOOOO!!"
