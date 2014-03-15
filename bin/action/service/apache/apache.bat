set jump=%cd%
cd ..\..\..\..\
if exist logs rmdir logs /s /q
mkdir logs
cd bin
if exist etc rmdir etc /s /q
mklink etc C:\Windows\System32\Drivers\etc /j
cd httpd\bin
start Apache.exe
cd %jump%