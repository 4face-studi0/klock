REM free some space
choco uninstall jdk10 -y
choco uninstall jdk11 -y
choco install jdk8 -y -params "installdir=c:\\java8" -params "source=false"
SET JAVA_HOME=c:\java8
CALL gradlew.bat -s -i mingwX64Test
