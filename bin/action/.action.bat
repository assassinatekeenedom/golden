@echo off
if "%1%" == "" (
echo  ----------[  Action Selection  ]----------
for /F "tokens=*" %%i in (list.txt) do echo %i% %%i   
set /p target="Select: " %=%
cd %target%
call %target%.bat
) else (
cd %1%
echo call .%1%.bat %2% %3% %4%>.%1%.bat
call .%1%.bat
)
