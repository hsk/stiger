rem call javac JLex/Main.java

rem call  java JLex.Main Parse/Tiger.lex
rem move Parse\Tiger.lex.java Parse\Yylex.java
rem call javac ErrorMsg/ErrorMsg.java
rem call scalac Absyn.scala

call java java_cup.Main -parser Grm -expect 3 Parse/Grm.cup
move Grm.java Parse\.
move sym.java Parse\.

call javac Parse/sym.java
call javac Parse/Lexer.java
call javac Parse/Yylex.java
call javac Parse/Parse.java -classpath "C:\Program Files\Scala\lib\scala-library.jar;."
