#!/bin/bash

if [ -z "$1" ]; then
  echo "YEAR missing"
  exit 1
fi

if [ -z "$2" ]; then
  echo "DAY missing"
  exit 1
fi

YEAR=$1
DAY=$2

if [ $DAY -lt 10 ]; then
  DAY="0$DAY"
fi

echo "preparing $YEAR-$DAY"
MAIN_FOLDER="aoc-${YEAR}/src/main/kotlin/com/kalvatn/aoc/year$YEAR"
TEST_FOLDER="aoc-${YEAR}/src/test/kotlin/com/kalvatn/aoc/year$YEAR"

MAIN_FILE="$MAIN_FOLDER/Y${YEAR}D${DAY}.kt"
TEST_FILE="$TEST_FOLDER/Y${YEAR}D${DAY}Test.kt"

TEST_P1_1="aoc-${YEAR}/src/main/resources/inputs/$YEAR/${DAY}_test_p1_1"
TEST_P2_1="aoc-${YEAR}/src/main/resources/inputs/$YEAR/${DAY}_test_p2_1"


mkdir -p $MAIN_FOLDER
mkdir -p $TEST_FOLDER
mkdir -p "aoc-${YEAR}/src/main/resources/inputs/$YEAR"

cp templates/Y201XDYY.kt $MAIN_FILE
cp templates/Y201XDYYTest.kt $TEST_FILE
touch $TEST_P1_1
touch $TEST_P2_1

sed -i "s/\$YEAR/$YEAR/g" $MAIN_FILE
sed -i "s/\$YEAR/$YEAR/g" $TEST_FILE
sed -i "s/\$DAY/$DAY/g" $MAIN_FILE
sed -i "s/\$DAY/$DAY/g" $TEST_FILE

echo "generated"
echo "$MAIN_FILE"
echo "$TEST_FILE"
echo "$TEST_P1_1"
echo "$TEST_P2_1"
