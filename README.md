Thanks to https://github.com/witchi/fmx.6.decompiler
# Oracle_FMX6_Viewer
Oracle_FMX6_Viewer is a java project to get components of Oracle Forms files "FMX" 6i version through a graphical interface. This project is implemented using Java 11.0.21 and Main-Class: info.phosco.forms.viewer.tabbed.Desktop.
after compiling it:

\jdk-11u21-windows-x64\bin\java.exe --module-path \javafx-sdk-11\lib --add-modules javafx.base,javafx.graphics,javafx.media,javafx.swing,javafx.web,javafx.swt,javafx.controls,javafx.fxml -cp .;.\jfxtras-window-11-r2.jar -jar Oracle_FMX6_Viewer.jar info.phosco.forms.viewer.tabbed.Desktop
