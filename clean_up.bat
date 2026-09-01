@echo off
setlocal
rem =====================================================================
rem Pre-push check: same task chain as CI (.github/workflows/ci.yml),
rem preceded by auto-formatting. Usage: .\clean_up.bat
rem =====================================================================

echo [1/2] ktlintFormat - autofix formatting...
call gradlew.bat ktlintFormat
if errorlevel 1 goto fail

echo [2/2] ktlintCheck + assembleDebug + unitTests (same as CI)...
call gradlew.bat ktlintCheck :composeApp:assembleDebug unitTests
if errorlevel 1 goto fail

echo.
echo OK - all green, safe to push.
exit /b 0

:fail
echo.
echo FAILED - see output above. Do not push.
exit /b 1
