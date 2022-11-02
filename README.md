# TicTacToe

How to compile the code in the labor

#1.
  If u want to use a specific java version, u'll need the path to the bin folder of the java installation folder.
  The installation Path in the labor (BT7) is ->
     /usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/
     
#2.
  To compile the code, use the keyword "javac" and the path to that specific java version. Compile the java file u want to compile.
     /usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/javac Main/MainServer.java 
     
#3. 
  Important
  We need to compile the files from the highest folder, otherwise some packages are not in the right scope and the compiler cannot compile the java file u want to compile.
  
#4.
  To execute the previously createdd .class files, use the keyword "java".  We need the same path to the bin folder, if we decide to use a specific java version. 
    /usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/java Main/MainServer "8080"
    

(
#Note:
  Its possible to compile the java code with the current java version. In the labor we used java 11, because older java versions cannot handle lambda usages. Thats the reason why we compile with a specific java version.)
