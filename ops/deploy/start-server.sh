#!/bin/bash

nohup java -cp "lib/*:web-server.jar" org.sukrupa.app.SchoolAdminApp sukrupa.war >> sukrupa.log &