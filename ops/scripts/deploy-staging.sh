#!/bin/bash

ssh $1@$2 "cd $3; unzip -o $4; sh bootstrap.sh"
#set -x -e # uncomment for testing the script

times=0
while [[ times -lt 6 ]]; do
    echo "Trying to connect to staging server..."
    ! ( wget --spider http://$2:8080/sukrupa/app/students ) || exit 0
    echo "sleeping 10 sec"
    sleep 10
    times=`expr $times + 1`
done
exit 1
