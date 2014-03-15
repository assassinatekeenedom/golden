@echo off
set target=invalid
if "%1%" == "" (
echo  ----------[  Action Selection  ]----------
for /F "tokens=*" %%i in (list.txt) do echo %i% %%i   
set /p target="Select: " %=%
) else (
set target=%1%;
cd %1%
echo call .%1%.bat %2% %3% %4%>.%1%.bat
call .%1%.bat
del .%1%.bat
goto skip
)
cd %target%
call %target%.bat
:SKIP