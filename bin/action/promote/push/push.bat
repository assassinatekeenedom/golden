@echo off
set /p message="Message: " %=%
set jump=%cd%
cd ..\..\..\..\
git add -A 
git commit -m "%message% - automated push"
git push -u
cd %jump%