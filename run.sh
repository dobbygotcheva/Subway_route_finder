#!/bin/bash

# Subway Route Finder Run Script
# This script provides easy access to all application features

echo "🚇 Subway Route Finder"
echo "======================"
echo ""

# Check if build exists
if [ ! -d "build/classes" ]; then
    echo "📦 Building project first..."
    ./build.sh
    echo ""
fi

# Function to show menu
show_menu() {
    echo "Please select an option:"
    echo "1) 🖥️  Modern GUI (Recommended)"
    echo "2) 🖥️  Basic GUI"
    echo "3) 💻 Command Line Interface"
    echo "4) 🧪 Run Tests"
    echo "5) 🎬 Demo Mode"
    echo "6) 📦 Build Project"
    echo "7) 🚪 Exit"
    echo ""
    read -p "Enter your choice (1-7): " choice
}

# Main loop
while true; do
    show_menu
    
    case $choice in
        1)
            echo "🚀 Starting Modern GUI..."
            java -cp build/classes com.subway.gui.ModernSubwayGUI
            ;;
        2)
            echo "🚀 Starting Basic GUI..."
            java -cp build/classes com.subway.gui.SubwayGUI
            ;;
        3)
            echo "💻 Command Line Interface"
            echo "Usage: java -cp build/classes com.subway.util.SubwayTester \"Start Station\" \"End Station\""
            echo ""
            read -p "Enter start station: " start
            read -p "Enter end station: " end
            java -cp build/classes com.subway.util.SubwayTester "$start" "$end"
            ;;
        4)
            echo "🧪 Running tests..."
            java -cp build/classes com.subway.util.LoadTester
            ;;
        5)
            echo "🎬 Running demo..."
            java -cp build/classes com.subway.util.Demo
            ;;
        6)
            echo "📦 Building project..."
            ./build.sh
            ;;
        7)
            echo "👋 Goodbye!"
            exit 0
            ;;
        *)
            echo "❌ Invalid option. Please try again."
            ;;
    esac
    
    echo ""
    read -p "Press Enter to continue..."
    echo ""
done 