#!/bin/zsh

javac -cp .:./Libraries/core.jar:./Libraries/vecmath.jar:./Libraries/BSim.jar BSimExercises.java 
java  -cp .:./Libraries/core.jar:./Libraries/vecmath.jar:./Libraries/BSim.jar BSimExercises 
