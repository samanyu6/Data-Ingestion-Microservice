#!/bin/bash
# check-config-up.sh

apt-get -y update
apt-get -qq -y install curl

#yes | apt-get install curl

curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://config-server:8888/actuator/health)

echo "result status code:" "$curlResult"

while [[ ! $curlResult == "200" ]]; do
  >&2 echo "Config server is not up yet!"
  sleep 2
  curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://config-server:8888/actuator/health)
done

echo "Starting process"
./cnb/lifecycle/launcher java org.springframework.boot.loader.JarLauncher