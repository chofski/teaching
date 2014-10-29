#!/bin/zsh

javac -cp .:../Libraries/core.jar:../Libraries/vecmath.jar:../Libraries/BSim.jar Extensions.java 
java  -cp .:../Libraries/core.jar:../Libraries/vecmath.jar:../Libraries/BSim.jar Extensions 
