# 🚇 Subway Route Finder

A modern Java application that finds optimal routes between subway stations using Object-Oriented Programming principles and design patterns. Features both command-line and graphical user interfaces with a beautiful, modern design.

![Java](https://img.shields.io/badge/Java-11+-orange.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)
![Platform](https://img.shields.io/badge/Platform-Cross--platform-blue.svg)

## 🌟 Features

- **🎯 Route Finding**: Find optimal routes between any two stations
- **🔄 Transfer Handling**: Automatically handles transfers between subway lines
- **🎨 Modern GUI**: Beautiful, responsive graphical interface
- **💻 CLI Support**: Command-line interface for automation
- **📊 Route Summary**: Detailed route information with connection counts
- **⚡ Performance**: Efficient breadth-first search algorithm
- **🧪 Testable**: Comprehensive test suite included
- **📚 Well Documented**: Complete documentation with design principles

## 🏗️ Architecture

This project demonstrates several key software engineering principles:

- **Object-Oriented Programming**: Encapsulation, Inheritance, Polymorphism, Abstraction
- **Design Patterns**: MVC, Factory, Strategy, Observer patterns
- **SOLID Principles**: All five principles implemented
- **Clean Architecture**: Layered design with clear separation of concerns
- **Domain-Driven Design**: Business logic separated from infrastructure

## 📦 Installation

### Prerequisites

- **Java 11 or higher**
- **Git** (for cloning)

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/subway-route-finder.git
   cd subway-route-finder
   ```

2. **Compile the project**
   ```bash
   javac *.java
   ```

3. **Run the modern GUI**
   ```bash
   java ModernSubwayGUI
   ```

## 🚀 Usage

### Graphical User Interface (Recommended)

Launch the modern GUI application:

```bash
java ModernSubwayGUI
```

**Features:**
- 🎨 Beautiful modern interface with gradients
- 📱 Responsive design with hover effects
- ⚡ Background processing for smooth UX
- 📊 Detailed route summaries
- 🎯 Easy station selection via dropdowns

### Command Line Interface

Find routes between specific stations:

```bash
java SubwayTester "Start Station" "End Station"
```

**Examples:**
```bash
# Direct route
java SubwayTester "DRY Drive" "Head First Theater"

# Route with transfer
java SubwayTester "DRY Drive" "Infinite Circle"

# Complex route
java SubwayTester "Weather-O-Rama, Inc." "Prime Numbers"
```

### Demo Mode

Run the comprehensive demonstration:

```bash
java Demo
```

### Testing

Verify the system is working correctly:

```bash
java LoadTester
```

## 🗺️ Available Stations

The Objectville subway system includes these stations:

| Station Name | Line Connections |
|--------------|------------------|
| DRY Drive | Meyer Line, Booch Line, Gamma Line |
| Weather-O-Rama, Inc. | Meyer Line |
| Boards 'R' Us | Meyer Line |
| Head First Theater | Meyer Line |
| LSP Lane | Meyer Line, Booch Line |
| JavaBeans Boulevard | Meyer Line, Booch Line |
| OOA&D Oval | Meyer Line, Booch Line, Gamma Line |
| Head First Labs | Meyer Line, Booch Line, Gamma Line |
| Infinite Circle | Meyer Line, Booch Line, Gamma Line |
| Long Way | Meyer Line, Booch Line, Gamma Line |
| Null Object | Meyer Line, Booch Line, Gamma Line |
| Prime Numbers | Meyer Line, Booch Line, Gamma Line |
| Fibonacci | Meyer Line, Booch Line, Gamma Line |

## 🎨 Subway Lines

- **Meyer Line**: Main route connecting all stations
- **Booch Line**: Alternative route through the system
- **Gamma Line**: Third route providing additional connections

## 📁 Project Structure

```
subway-route-finder/
├── 📄 Core Application
│   ├── Subway.java              # Main system orchestrator
│   ├── Station.java             # Station entity
│   ├── Connection.java          # Connection entity
│   └── SubwayLoader.java        # Data loading service
├── 🖥️ User Interfaces
│   ├── SubwayGUI.java           # Basic GUI interface
│   ├── ModernSubwayGUI.java     # Enhanced modern GUI
│   ├── SubwayPrinter.java       # Output formatting
│   └── SubwayTester.java        # Command-line interface
├── 🧪 Testing & Demo
│   ├── LoadTester.java          # Data loading tests
│   └── Demo.java                # Comprehensive demonstration
├── 📊 Data
│   └── ObjectvilleSubway.txt    # Subway network data
├── 📚 Documentation
│   ├── README.md                # This file
│   ├── DESIGN_PRINCIPLES.md     # OOP and design principles
│   ├── ARCHITECTURE_DIAGRAM.md  # System architecture
│   └── LICENSE                  # MIT License
└── 📋 Configuration
    └── .gitignore               # Git ignore rules
```

## 🔧 Development

### Building from Source

1. **Clone and navigate**
   ```bash
   git clone https://github.com/yourusername/subway-route-finder.git
   cd subway-route-finder
   ```

2. **Compile all classes**
   ```bash
   javac *.java
   ```

3. **Run tests**
   ```bash
   java LoadTester
   ```

4. **Launch application**
   ```bash
   java ModernSubwayGUI
   ```

### Adding New Features

The project is designed for easy extension:

#### Adding New GUI Themes
```java
public class DarkThemeSubwayGUI extends ModernSubwayGUI {
    // Override color scheme for dark theme
}
```

#### Adding New Data Sources
```java
public class DatabaseSubwayLoader implements SubwayLoader {
    // Load from database instead of file
}
```

#### Adding New Output Formats
```java
public class HTMLSubwayPrinter extends SubwayPrinter {
    // Generate HTML output
}
```

## 🧪 Testing

### Automated Tests

Run the comprehensive test suite:

```bash
java LoadTester
```

**Test Coverage:**
- ✅ Station loading and validation
- ✅ Connection loading and validation
- ✅ Route finding algorithms
- ✅ GUI functionality
- ✅ Error handling

### Manual Testing

Test different route scenarios:

```bash
# Direct routes
java SubwayTester "DRY Drive" "Head First Theater"

# Routes with transfers
java SubwayTester "DRY Drive" "Infinite Circle"

# Complex routes
java SubwayTester "Weather-O-Rama, Inc." "Prime Numbers"
```

## 📚 Documentation

- **[DESIGN_PRINCIPLES.md](DESIGN_PRINCIPLES.md)**: Comprehensive OOP and design patterns analysis
- **[ARCHITECTURE_DIAGRAM.md](ARCHITECTURE_DIAGRAM.md)**: Visual system architecture and data flow
- **Code Comments**: Self-documenting code with clear method names

## 🤝 Contributing

We welcome contributions! Please follow these guidelines:

### How to Contribute

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes**
4. **Test thoroughly**
   ```bash
   javac *.java
   java LoadTester
   ```
5. **Commit your changes**
   ```bash
   git commit -m "Add: your feature description"
   ```
6. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```
7. **Create a Pull Request**

### Contribution Guidelines

- **Code Style**: Follow existing Java conventions
- **Documentation**: Add comments for complex logic
- **Testing**: Include tests for new features
- **Design Principles**: Maintain OOP and SOLID principles
- **Performance**: Consider algorithm efficiency

### Areas for Contribution

- 🎨 **New GUI Themes**: Dark mode, custom themes
- 📊 **Enhanced Visualizations**: Route maps, station diagrams
- 🔧 **New Algorithms**: A*, Dijkstra, multi-path routing
- 📱 **Mobile Support**: Android/iOS versions
- 🌐 **Web Interface**: Web-based route finder
- 🗄️ **Database Support**: SQL/NoSQL data sources
- 📈 **Analytics**: Route statistics and analytics
- 🌍 **Internationalization**: Multi-language support

## 🙏 Acknowledgments

- **Objectville Subway System**: Fictional subway network for demonstration
- **Java Swing**: GUI framework for desktop applications
- **Design Patterns**: Gang of Four patterns for clean architecture
- **SOLID Principles**: Robert C. Martin's design principles

---

*This project demonstrates professional software engineering practices and serves as an excellent example of well-structured Object-Oriented Programming.* 
