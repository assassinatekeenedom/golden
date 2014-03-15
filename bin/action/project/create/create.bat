set /p named="Local Name: " %=%
set jump=%cd%
cd ..\..\..\..\
if not exist projects mkdir projects
cd projects
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
%jump%\build.bat %named%
cd %jump%