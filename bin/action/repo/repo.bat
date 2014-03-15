@echo off
echo  ----------[  Service Selection  ]----------
for /F "tokens=*" %%i in (list.txt) do echo %i% %%i
set /p target="Select: " %=%
set /p url="URL: " %=%
set /p named="Local Name: " %=%
set jump=%cd%\%target%
cd ..\..\..\
if not exist repos mkdir repos
cd repos

set change = N;
set ready = N;
if exist "%named%" set change=Y
if "%change%" == "Y" (
    set /p confirm="Do you want to replace this [Y]?  " %=%    
) else (
    set ready=Y
)

if "%confirm%" == "Y" (
    rmdir %named% /q /s
    set ready=Y
)
set cache=%cd%
if "%ready%" == "Y" (
    cd %jump%
    call .%target%.bat %url% %cache%\%named%
) else (
    cd %jump%
)