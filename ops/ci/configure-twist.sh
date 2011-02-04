#!/bin/sh

CURRENT_DIR=$(pwd)
FIREFOX_LINK="/usr/bin/start-firefox.sh"
FIREFOX_SCRIPT="$CURRENT_DIR/ops/local/start-firefox.sh"

SAHI_LINK_LOCATION="/var/opt"
SAHI_LINK="$SAHI_LINK_LOCATION/sukrupa-twist"
SAHI_LOCATION="$CURRENT_DIR/src/test/twist/functional-test"

echo "Going to create a link [$FIREFOX_LINK]"
echo "to [$FIREFOX_SCRIPT]"
echo "And [$SAHI_LINK]"
echo "to [$SAHI_LOCATION]"

echo "we are in [$CURRENT_DIR]"

if [ -h "$FIREFOX_LINK" ] ;
then
    sudo rm $FIREFOX_LINK
fi

if [ -h "$SAHI_LINK" ] ;
then
    sudo rm $SAHI_LINK
fi

if [ -e "$SAHI_LINK_LOCATION" ];
then
    echo "$SAHI_LINK_LOCATION exists, no need to create"
else
    echo "Creating $SAHI_LINK_LOCATION"
    sudo mkdir -p "$SAHI_LINK_LOCATION"
fi

sudo ln -s $FIREFOX_SCRIPT $FIREFOX_LINK
sudo ln -s $SAHI_LOCATION $SAHI_LINK