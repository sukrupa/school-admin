#!/bin/sh

CURRENT_DIR=$(pwd)

echo "Going to create a link to the firefox script, we are in [$CURRENT_DIR]"

sudo ln -s $CURRENT_DIR/ops/local/start-firefox.sh /usr/bin/start-firefox.sh