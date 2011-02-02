#!/bin/sh

CURRENT_DIR=$(pwd)
FIREFOX_LINK="/usr/bin/start-firefox.sh"
FIREFOX_SCRIPT="$CURRENT_DIR/ops/local/start-firefox.sh"

echo "Going to create a link [$FIREFOX_LINK]"
echo "to [$FIREFOX_SCRIPT]"
echo "we are in [$CURRENT_DIR]"

if [ -h "$FIREFOX_LINK" ] ;
then
    sudo rm $FIREFOX_LINK
fi
sudo ln -s $FIREFOX_SCRIPT $FIREFOX_LINK