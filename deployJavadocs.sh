#!/bin/bash

repo="git@github.com:targodan/hsReisePlugin.git"
user="TravisCI"
mail="$COMMIT_AUTHOR_EMAIL"
commitMessage="Automatically updated JavaDocs."

branch=$(git branch | grep '*' | cut -d' ' -f 2)
if [[ "$branch" != "master" && "$branch" != "develop" ]]; then
    exit
fi

openssl aes-256-cbc -K $encrypted_f0f45782d3d4_key -iv $encrypted_f0f45782d3d4_iv -in deployKey.enc -out deployKey -d
ssh-add deployKey

git clone --branch=gh-pages "$repo" pagesOut
cd pagesOut

rm -rf "$branch/*"
./generateJavadoc "../src/main/java" "$branch"

git add -A
git commit -m "$commitMessage"
git push
