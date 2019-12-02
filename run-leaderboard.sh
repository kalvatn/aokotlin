#!/bin/bash

AOC_LEADERBOARD_ID="116603"
AOC_SESSION_COOKIE="$(cat src/main/resources/session.cookie)"

echo "$AOC_LEADERBOARD_ID"
echo "$AOC_SESSION_COOKIE"

docker run --rm -it -p 80:8081 \
	-e AOC_LEADERBOARD_ID=$AOC_LEADERBOARD_ID \
	-e AOC_SESSION_COOKIE=$AOC_SESSION_COOKIE \
	thomaslandro/aoc-leaderboard-go:latest
