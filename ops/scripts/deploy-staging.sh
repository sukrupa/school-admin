#!/bin/bash

ssh $1@$2 "cd $3; unzip -o $4; sh bootstrap.sh"
wget --spider http://twu-staging:8080/sukrupa/app/students || exit 1