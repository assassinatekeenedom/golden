set /p named="Local Name: " %=%
set jump=%cd%
cd ..\..\..\..\
cd projects\%named%
set cache=%cd%
%jump%\.build.bat %named%
cd %jump%