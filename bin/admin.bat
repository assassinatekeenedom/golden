@echo off
setlocal
set RUN.ROOT=%cd%
set RUN.APACHE=%RUN.ROOT%\httpd
set RUN.GIT=%RUN.ROOT%\git
set RUN.JAVA=%RUN.ROOT%\java
set RUN.MVN=%RUN.ROOT%\maven
set RUN.SVN=%RUN.ROOT%\svn
set CATALINA_HOME=%RUN.ROOT%\tomcat
set PATH=%PATH%;%RUN.SVN%\bin;%RUN.JAVA%\bin;%RUN.MVN%\bin;%RUN.GIT%\bin;%RUN.APACHE%\bin;%CATALINA_HOME%\bin;
cd action
call action.bat
endlocal