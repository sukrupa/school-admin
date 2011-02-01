#!/bin/sh


FIREFOX_PIDS=$(ps aux | grep firefox | grep -v grep | grep -v /bin/sh | awk '{ print $2 }')



if [ -z $FIREFOX_PIDS ]; then
    echo "No running foxes found, nothing to stop."
else
    echo "Found firefox PIDS [$FIREFOX_PIDS], killing them all!"
    kill $FIREFOX_PIDS
fi
