#!/usr/bin/env bash

function redis() {
  type="$1"
  if [ $type == 1 ]; then
    nohup /usr/local/Cellar/redis/5.0.8/bin/redis-server &
    echo "start redis"
  elif [ $type == 0 ]; then
    /usr/local/Cellar/redis/5.0.8/bin/redis-cli SHUTDOWN SAVE
    echo "stop redis"
  fi
}

redis 0
