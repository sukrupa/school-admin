#!/bin/bash

nohup java -cp "lib/*" org.sukrupa.app.SchoolAdminApp -Dweb.root.dir=web >> sukrupa.log &