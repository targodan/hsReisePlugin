#!/bin/bash

repo="$(git config remote.origin.url)"
repo="${repo/https:\/\/github.com\//git@github.com:}"
commit=$(git rev-parse --verify HEAD)
user="$DEPLOY_GIT_NAME"
email="$DEPLOY_GIT_EMAIL"
commitMessage="${DEPLOY_COMMIT_MSG/COMMIT_SHA/$commit}"
branch="$TRAVIS_BRANCH"

echo "---- DEBUG ----"
echo "repo = $repo"
echo "commit = $commit"
echo "user = $user"
echo "email = $email"
echo "commitMessage = $commitMessage"
echo "branch = $branch"
echo "---------------"

echo "Starting deploy script on branch \"$branch\"."

pwd
git status

if [[ "$branch" != "master" && "$branch" != "develop" ]]; then
    echo "Wrong branch, wont deploy."
    exit
fi

echo "Decrypting deploy key..."
openssl aes-256-cbc -K $encrypted_f0f45782d3d4_key -iv $encrypted_f0f45782d3d4_iv -in deployKey.enc -out deployKey -d && echo "Done." || echo "Error decoding!"
ssh-add deployKey

echo "Cloning gh-pages..."
git clone --branch=gh-pages "$repo" pagesOut && echo "Done." || echo "Error cloning."
cd pagesOut

git config user.name "$user"
git config user.email "$email"

echo "Cleaning old java docs..."
rm -rf "$branch/*"
echo "Done."

echo "Generating java docs..."
./generateJavadoc "../src/main/java" "$branch" && echo "Done." || echo "Error generating."

echo "Committing..."
git add -A
git commit -m "$commitMessage" && echo "Done." || echo "Error committing."

echo "Pushing..."
git push && echo "Done." || echo "Error pushing."

echo "Deploy script finished."
