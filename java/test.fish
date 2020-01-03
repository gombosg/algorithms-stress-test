#!/usr/bin/env fish

if test (count $argv) = "0"
  echo "Give class or file name as argument!"
  exit 1
end

echo "Running all test cases from ./tests"

set PROGRAM java -cp ./target/classes $argv
# python:
# set PROGRAM python3 $argv

set TESTS (find ./tests -type f -name '*[^.a]' | sort -g)
# length of diff output
set LENGTH 10000

for TEST in $TESTS
    set RESULT (cat $TEST | $PROGRAM | tr -d '[:space:]')
    set EXPECTED (cat $TEST.a | tr -d '[:space:]')
    if test $RESULT = $EXPECTED
        printf "Test %s OK\n" $TEST
    else
        printf "Test %s ERROR \n -actual:   %s\n -expected: %s\n -diff" $TEST (echo $RESULT | cut -c 1-$LENGTH) (echo $EXPECTED | cut -c 1-$LENGTH)
        # break
    end
end
