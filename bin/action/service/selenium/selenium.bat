if "%1%" == "" (
echo  ----------[  Selenium Startup  ]----------
for /F "tokens=*" %%i in (list.txt) do echo %i% %%i
set /p target="Target: " %=%
) else (
set target=%1%
)
cd %target%
call %target%.bat