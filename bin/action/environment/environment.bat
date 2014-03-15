@echo off
echo  _________________[  Full Development Environment  ]_________________
cd ..\service
echo call service.bat apache>.apache.bat
call .apache.bat
cd service
del .apache.bat  && echo deleted cached script: .apache.bat
echo ___________________________________________________
cd bin/action/service
echo call service.bat log4j>.log4j.bat
call .log4j.bat
cd service
del .log4j.bat && echo deleted cached script: .log4j.bat
echo ___________________________________________________
cd service
echo call service.bat wiki a>.wiki.bat
call .wiki.bat
cd service
del .wiki.bat && echo deleted cached script: .wiki.bat
echo ___________________________________________________
cd service
echo call service.bat selenium hub>.hub.bat
call .hub.bat
del .hub.bat && echo deleted cached script: .hub.bat
echo ___________________________________________________
cd service
echo call service.bat selenium node>.node.bat
call .node.bat
del .node.bat && echo deleted cached script: .node.bat
echo ___________________________________________________
echo call service.bat tomcat>.tomcat.bat
call .tomcat.bat
cd action\service
del .tomcat.bat && echo deleted cached script: .tomcat.bat
echo ___________________________________________________