@echo On
@Rem ɾ��CVS�汾����Ŀ¼
@PROMPT [Com]#

@for /r . %%a in (.) do @if exist "%%a\CVS" rd /s /q "%%a\CVS"
@Rem for /r . %%a in (.) do @if exist "%%a\CVS" @echo "%%a\CVS"

@echo Mission Completed.
@pause