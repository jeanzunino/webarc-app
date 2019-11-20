@echo off
set description=%1%
if "%1%" == "" (
	set /p description=File name: 
)
for /f "tokens=1-9 delims=/" %%a in ("%date%") do set ymd=%%c%%b%%a
for /f "tokens=1-9 delims=:," %%a in ("%time%") do set hmss=%%a%%b%%c%%d0
set hmss=%hmss: =0%
set ymd=%ymd: =0%
set filename=V%ymd%%hmss%__%description%.sql
set scriptpath=%~dp0script\migration\tenants
echo TODO > "%scriptpath%\%filename%"
echo New file: %filename%
