#!/bin/sh
mkdir texts
awk 'BEGIN{file="/dev/null"; g=0}/FEDERALIST No. [1-9][^0-9]/{close(file); g++; file="texts/paper_0"g".txt"}{print $0 > file}' pg1404.txt

# clean up last paper - paper_09.txt
mv texts/paper_09.txt texts/tmp
awk 'BEGIN{file="texts/paper_09.txt"}/FEDERALIST No. 10/{close(file); file="/dev/null"}{print $0 > file}' texts/tmp
rm texts/tmp

awk 'BEGIN{file="/dev/null"; g=9}/FEDERALIST No. [1-9][0-9]/{close(file); g++; file="texts/paper_"g".txt"}{print $0 > file}' pg1404.txt

# clean up last paper - paper_85.txt
mv texts/paper_85.txt texts/tmp
awk 'BEGIN{file="texts/paper_85.txt"}/End of the Project Gutenberg EBook/{close(file); file="/dev/null"}{print $0 > file}' texts/tmp
rm texts/tmp
