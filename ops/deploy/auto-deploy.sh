#!/bin/bash

ssh $1@$2 "cd $3; sh bootstrap.sh $GO_PIPELINE_COUNTER"
