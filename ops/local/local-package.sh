#!/bin/sh
sh build package
rm -rf /tmp/sukrupa/*
cp target/school-admin.zip /tmp/sukrupa
cd /tmp/sukrupa
unzip school-admin.zip
