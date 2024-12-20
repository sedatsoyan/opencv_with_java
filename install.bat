@echo off

REM Java'nın kurulu olup olmadığını kontrol et
java -version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo Java bulunamadı! Lütfen Java JDK kurun ve tekrar deneyin.
    pause
    exit /b 1
)

REM Javac'ın kurulu olup olmadığını kontrol et
javac -version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo javac bulunamadı! Lütfen Java JDK kurun ve tekrar deneyin.
    pause
    exit /b 1
)

REM Java dosyasını derle
echo Java programınızı derliyorum...
javac *.java
IF %ERRORLEVEL% NEQ 0 (
    echo Java kodları derlenirken bir hata oluştu.
    pause
    exit /b 1
)

REM Java programını çalıştır
echo Java programınızı çalıştırıyorum...
java Main
IF %ERRORLEVEL% NEQ 0 (
    echo Java programınız çalıştırılırken bir hata oluştu.
    pause
    exit /b 1
)

echo Program başarıyla çalıştırıldı!
pause
