@echo off
echo var GIT_HOME='%cd%' > native.js
echo var JAVA_HOME='%JAVA_HOME%' >> native.js
echo var MAVEN_HOME='%MAVEN_HOME%' >> native.js
echo var GRAPH_HOME='%GRAPHVIZ_HOME%' >> native.js