#!/bin/bash

ssh $1@$2 "cd $3; unzip -o $4; sh bootstrap.sh"
#set -x -e # uncomment for testing the script

ntimes=0
while [ $ntimes -lt 6 ]; do
    echo "Trying to connect to staging server..."
    ! ( wget --spider http://$2:8080/sukrupa/app/students ) || exit 0
    echo "sleeping 10 sec"
    sleep 10
    ntimes=`expr $ntimes + 1`
done
exit 1
