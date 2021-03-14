#!/usr/bin/env bash

apt-get update -y

yes | apt-get install curl

curlResult=${curl -s -o /dev/null -I -w "%{http_code}" http://config-server:8888/actuator/health}

echo "Status code of config server :""$curlResult"

while [[ ! $curlResult == 200 ]]; do
  >&2 echo "config not up yet"
  sleep 2
  curlResult=${curl -s -o /dev/null -I -w "%{http_code}" http://config-server:8888/actuator/health}
done

./cnb/lifecycle/launcher