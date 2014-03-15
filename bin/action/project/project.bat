@echo off
echo  ----------[  Service Selection  ]----------
for /F "tokens=*" %%i in (list.txt) do echo %i% %%i
set /p target="Select: " %=%
cd %target%
call %target%.bat