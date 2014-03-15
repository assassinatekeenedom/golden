set jump=%cd%
cd ..\..\..\..\
set root=%cd%
cd %jump%
if "%1%" == "" (
echo  ----------[  Wiki Selection  ]----------
for /F "tokens=*" %%i in (list.txt) do echo %i% %%i
set /p target="Wiki Name: " %=%
set /p port="Port Number: " %=%
) else (
    set target=unified
    set port=1337
)
echo java -Xmx100M -jar fitnesse.jar -p %port% -r %target% -l %root%\logs -d %root%\wiki > fitnesse.bat
start fitnesse.bat