#!/bin/bash

sh stop-server.sh
nohup java -cp "lib/*:web-server.jar" org.sukrupa.app.SchoolAdminApp sukrupa.war >> /var/log/sukrupa.log &
