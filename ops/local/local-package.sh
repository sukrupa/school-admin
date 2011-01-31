#!/bin/sh
sh build package
rm -rf /tmp/sukrupa/*
cp target/school-admin-local.zip /tmp/sukrupa
cd /tmp/sukrupa
unzip school-admin-local.zip
