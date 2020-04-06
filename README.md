# AlienRun

A collision avoidance game with object interactions, written in Feb 2016.

## How to use it

### 0. Prerequisite

install JavaFx SDK<br/>
https://gluonhq.com/products/javafx/

### 1. Clone it
```
git clone https://github.com/simonlaw101/AlienRun.git
```

### 2. Open "Game" folder in command prompt and type the following commands
```
set PATH_TO_FX="path_to_javafx_sdk_lib_directory"
```
e.g. set PATH_TO_FX="C:\Program Files\JavaFx\javafx-sdk-11.0.2\lib"
<br/><br/>
```
javac --module-path %PATH_TO_FX% --add-modules javafx.media,javafx.controls *.java
java --module-path %PATH_TO_FX% --add-modules javafx.media,javafx.controls GameMain
```
