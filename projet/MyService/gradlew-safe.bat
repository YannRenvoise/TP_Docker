@echo off
setlocal

rem Run Gradle from an ASCII-only drive mapping to avoid Windows path encoding issues.
if not defined GRADLE_USER_HOME set "GRADLE_USER_HOME=%SystemDrive%\gradle-cache\car-rental-service"

set "PROJECT_DIR=%~dp0"
if "%PROJECT_DIR:~-1%"=="\" set "PROJECT_DIR=%PROJECT_DIR:~0,-1%"
set "SAFE_DRIVE="

for %%D in (X Y Z) do (
    if not exist "%%D:\" (
        subst %%D: "%PROJECT_DIR%" >nul
        if not errorlevel 1 (
            set "SAFE_DRIVE=%%D:"
            goto :mapped
        )
    )
)

echo No free drive letter available for temporary mapping.
exit /b 1

:mapped
pushd "%SAFE_DRIVE%\"
call "%SAFE_DRIVE%\gradlew.bat" %*
set "EXIT_CODE=%ERRORLEVEL%"
popd
subst %SAFE_DRIVE% /d >nul
exit /b %EXIT_CODE%
