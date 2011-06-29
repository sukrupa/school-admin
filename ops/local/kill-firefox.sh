#!/bin/sh
set -e

list_processes() {
    if [ "$OS" == "Windows_NT" ]; then
        tasklist
    else
        ps aux
    fi
}

FIREFOX_PIDS=$(list_processes | grep firefox | grep -v grep | grep -v /bin/sh | awk '{ print $2 }')

if [ -z $FIREFOX_PIDS ]; then
    echo "No running foxes found, nothing to stop."
else
    echo "Found firefox PIDS [$FIREFOX_PIDS], killing them all!"
    if [ "$OS" == "Windows_NT" ]; then
        taskkill //F //PID $FIREFOX_PIDS
    else
        kill $FIREFOX_PIDS
    fi
fi
