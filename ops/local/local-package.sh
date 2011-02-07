#!/bin/sh

SCHOOL_ADMIN_ZIPFILE=school-admin.zip

sh build package
rm -rf /tmp/sukrupa
mkdir /tmp/sukrupa
cp target/$SCHOOL_ADMIN_ZIPFILE /tmp/sukrupa/

cd /tmp/sukrupa
unzip $SCHOOL_ADMIN_ZIPFILE
chmod +x start-server.sh
chmod +x stop-server.sh

