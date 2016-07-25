#!/bin/bash

repo="$(git config remote.origin.url)"
repo="${repo/https:\/\/github.com\//git@github.com:}"
commit=$(git rev-parse --verify HEAD)
user="$DEPLOY_GIT_NAME"
mail="$COMMIT_AUTHOR_EMAIL"
commitMessage="$DEPLOY_COMMIT_MSG"

branch="$(git branch | grep '*' | cut -d' ' -f 2)"
if [[ "$branch" != "master" && "$branch" != "develop" ]]; then
    exit
fi

openssl aes-256-cbc -K $encrypted_f0f45782d3d4_key -iv $encrypted_f0f45782d3d4_iv -in deployKey.enc -out deployKey -d
ssh-add deployKey

git clone --branch=gh-pages "$repo" pagesOut
cd pagesOut

git config user.name "$user"
git config user.email "$mail"

rm -rf "$branch/*"
./generateJavadoc "../src/main/java" "$branch"

git add -A
git commit -m "${commitMessage/COMMIT_SHA/$commit}"
git push
