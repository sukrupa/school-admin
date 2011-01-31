#!/bin/bash

ssh $1@$2 "cd $3; unzip -o $4; sh bootstrap.sh"

