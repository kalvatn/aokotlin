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

echo "preparing $YEAR-$DAY"
MAIN_FILE="src/main/kotlin/com/kalvatn/aoc/year$YEAR/Y${YEAR}D${DAY}.kt"
TEST_FILE="src/test/kotlin/com/kalvatn/aoc/year$YEAR/Y${YEAR}D${DAY}Test.kt"
TEST_INPUT_FILE="src/main/resources/inputs/$YEAR/${DAY}_test"

echo "$MAIN_FILE"
echo "$TEST_FILE"
echo "$TEST_INPUT_FILE"

cp templates/Y201XDYY.kt $MAIN_FILE
cp templates/Y201XDYYTest.kt $TEST_FILE
mkdir -p "src/main/resources/inputs/$YEAR"
touch $TEST_INPUT_FILE

sed -i "s/\$YEAR/$YEAR/g" $MAIN_FILE
sed -i "s/\$YEAR/$YEAR/g" $TEST_FILE
sed -i "s/\$DAY/$DAY/g" $MAIN_FILE
sed -i "s/\$DAY/$DAY/g" $TEST_FILE

echo "generated"
cat $MAIN_FILE
cat $TEST_FILE
cat $TEST_INPUT_FILE
