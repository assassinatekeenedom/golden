echo  ----------[  Current Status ]----------
set jump=%cd%
cd ..\..\..\
git status
git remote -v
cd %jump%
echo  ----------[  Version Control  ]----------
for /F "tokens=*" %%i in (list.txt) do echo %i% %%i
set /p target="Select: " %=%
cd %target%
call %target%.bat
cd ..