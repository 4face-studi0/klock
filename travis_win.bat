choco install -y jdk8 -params 'installdir=c:\\java8'
SET JAVA_HOME=c:\java8
CALL gradlew.bat -s -i mingwX64Test
