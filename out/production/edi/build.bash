#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Brick.java Mario.java Json.java
java Game
