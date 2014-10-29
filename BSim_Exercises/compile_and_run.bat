

set CLASSPATH=.;.\Java\lib

set Path=.\Java\bin;%Path%

javac -classpath .;.\Libraries\core.jar;.\Libraries\vecmath.jar;.\Libraries\BSim.jar BSimExercises.java 



java  -classpath .;.\Libraries\core.jar;.\Libraries\vecmath.jar;.\Libraries\BSim.jar BSimExercises 
