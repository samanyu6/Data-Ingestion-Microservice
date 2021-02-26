#!/bin/sh

login() {
  git config --global user.email "${EMAIL_ID}"
  git config --global user.name "samanyu6"
}

commit() {
  git checkout master

  git commit -m "Travis deploy $TRAVIS_BUILD_NUMBER" -m "[skip ci]"
}

push() {
  git remote rm origin
  # Add new "origin" with access token in the git URL for authentication
  git remote add origin https://samanyu6:${DEPLOY_TOKEN}@github.com/samanyu6/Data-Ingestion-Microservice.git > /dev/null 2>&1
  git push origin master
}

login
commit
push
