#!/bin/bash

# Subway Route Finder Build Script
# This script compiles the project and creates a runnable JAR file

echo "🚇 Building Subway Route Finder..."

# Create build directory
mkdir -p build/classes

# Compile all Java files
echo "📦 Compiling Java files..."
javac -d build/classes \
    src/main/java/com/subway/core/*.java \
    src/main/java/com/subway/gui/*.java \
    src/main/java/com/subway/util/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
else
    echo "❌ Compilation failed!"
    exit 1
fi

# Copy data files to build directory
echo "📁 Copying data files..."
cp -r data build/

# Create JAR file
echo "📦 Creating JAR file..."
jar cfm build/subway-route-finder.jar manifest.txt \
    -C build/classes com \
    -C build data

if [ $? -eq 0 ]; then
    echo "✅ JAR file created successfully!"
    echo "📦 JAR file location: build/subway-route-finder.jar"
else
    echo "❌ JAR creation failed!"
    exit 1
fi

# Make JAR executable
chmod +x build/subway-route-finder.jar

echo "🎉 Build completed successfully!"
echo ""
echo "🚀 To run the application:"
echo "   java -jar build/subway-route-finder.jar"
echo ""
echo "📋 Available commands:"
echo "   java -cp build/classes com.subway.gui.ModernSubwayGUI"
echo "   java -cp build/classes com.subway.gui.SubwayGUI"
echo "   java -cp build/classes com.subway.util.SubwayTester \"Start Station\" \"End Station\""
echo "   java -cp build/classes com.subway.util.Demo"
echo "   java -cp build/classes com.subway.util.LoadTester" 