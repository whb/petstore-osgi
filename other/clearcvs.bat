@echo On
@Rem É¾³ýCVS°æ±¾¿ØÖÆÄ¿Â¼
@PROMPT [Com]#

@for /r . %%a in (.) do @if exist "%%a\CVS" rd /s /q "%%a\CVS"
@Rem for /r . %%a in (.) do @if exist "%%a\CVS" @echo "%%a\CVS"

@echo Mission Completed.
@pause