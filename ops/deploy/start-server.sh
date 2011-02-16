#!/bin/bash

nohup java -cp "lib/*" -Dweb.root.dir=web -Dweb.server.realm.file=jetty-realms.properties org.sukrupa.app.SchoolAdminApp >> sukrupa.log &