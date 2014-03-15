@echo off
set jump=%cd%
cd ..\..\..\..\
git pull origin master
cd %jump%