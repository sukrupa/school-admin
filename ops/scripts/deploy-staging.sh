#!/bin/bash

ssh $1@$2 "cd $3; unzip -o $4; sh bootstrap.sh"
sleep 10
wget --spider http://$2:8080/sukrupa/app/students || exit 1