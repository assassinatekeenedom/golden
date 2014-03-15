@echo off
if "%1%" == "" (
echo  ----------[  Service Selection  ]----------
for /F "tokens=*" %%i in (list.txt) do echo %i% %%i   
set /p target="Select: " %=%
) else (
set target=%1%
)
echo ............ Processing: %target%
cd %target%
if "%2%" == "" (
call %target%.bat
) else (
if "%3" == "" (
call %target%.bat %2%
) else (
call %target%.bat %2% %3%
)
)
cd ..\..\