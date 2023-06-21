#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Sprite.java Brick.java Mario.java Coin.java Json.java
java Game
