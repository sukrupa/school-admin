#!/bin/bash

nohup java -cp "lib/*:WEB-INF/classes" -Dweb.root.dir=web org.sukrupa.app.SchoolAdminApp >> sukrupa.log &