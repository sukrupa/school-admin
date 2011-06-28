wmic PROCESS where (name="java.exe" and commandline like "%%sahi%%") terminate

rem Pause for 1 second before deleting (yes it's a hack--batch files suck)
ping 127.0.0.1 -n 2 -w 1000 > nul

del /f sahi*.log*