# Object-Oriented Programming & Design Principles in Subway Route Finder

## Overview

The Subway Route Finder application demonstrates several key Object-Oriented Programming (OOP) principles and design patterns. This document explains the architectural decisions and how they contribute to a maintainable, extensible, and well-structured codebase.

## 🏗️ Architecture Overview

```
Subway Route Finder
├── Core Domain (Business Logic)
│   ├── Subway.java          - Main system orchestrator
│   ├── Station.java         - Entity representing stations
│   ├── Connection.java      - Entity representing connections
│   └── SubwayLoader.java   - Data loading responsibility
├── Presentation Layer
│   ├── SubwayPrinter.java  - Output formatting
│   ├── SubwayGUI.java      - Basic GUI interface
│   └── ModernSubwayGUI.java - Enhanced GUI interface
└── Application Layer
    ├── SubwayTester.java   - Command-line interface
    ├── LoadTester.java     - Testing utilities
    └── Demo.java          - Demonstration program
```

## 🎯 Object-Oriented Programming Principles

### 1. **Encapsulation (Information Hiding)**

**Principle**: Bundle data and methods that operate on that data within a single unit (class).

**Examples in the Code**:

```java
// Station.java - Encapsulates station data and behavior
public class Station {
    private String name;  // Private data
    
    public String getName() {  // Public interface
        return name;
    }
    
    public boolean equals(Object obj) {  // Encapsulated behavior
        // Implementation details hidden from clients
    }
}
```

```java
// Subway.java - Encapsulates network data and operations
public class Subway {
    private List stations;      // Private data
    private List connections;   // Private data
    private Map network;        // Private data
    
    public void addStation(String stationName) {  // Public interface
        // Implementation details hidden
    }
    
    private void addToNetwork(Station station1, Station station2) {  // Private helper
        // Internal implementation hidden
    }
}
```

**Benefits**:
- **Data Protection**: Internal state cannot be directly modified
- **Implementation Flexibility**: Can change internal structure without affecting clients
- **Maintainability**: Changes to internal logic don't break external code

### 2. **Inheritance and Polymorphism**

**Principle**: Create hierarchies of related classes and use common interfaces.

**Examples**:

```java
// GUI inheritance hierarchy
public class ModernSubwayGUI extends JFrame {
    // Inherits all JFrame functionality
    // Adds custom subway-specific behavior
}

// Polymorphic behavior with Swing components
JButton findRouteButton = createModernButton("🔍 Find Route", PRIMARY_COLOR);
JButton clearButton = createModernButton("🗑️ Clear", ACCENT_COLOR);
// Both buttons share common interface but have different behaviors
```

**Benefits**:
- **Code Reuse**: Common functionality inherited from parent classes
- **Extensibility**: Easy to add new GUI variants
- **Consistency**: All GUI components follow same patterns

### 3. **Abstraction**

**Principle**: Hide complex implementation details behind simple interfaces.

**Examples**:

```java
// Subway.java - Abstract interface for route finding
public List getDirections(String startStationName, String endStationName) {
    // Complex breadth-first search algorithm hidden behind simple method
    // Clients don't need to know about network traversal, path building, etc.
}

// SubwayLoader.java - Abstract data loading
public Subway loadFromFile(File subwayFile) throws IOException {
    // Complex file parsing hidden behind simple method
    // Clients just call loadFromFile() without knowing file format details
}
```

**Benefits**:
- **Simplicity**: Complex operations appear simple to use
- **Flexibility**: Can change implementation without affecting clients
- **Testability**: Can test high-level behavior independently

### 4. **Composition over Inheritance**

**Principle**: Favor object composition over class inheritance.

**Examples**:

```java
// Subway.java - Uses composition of other objects
public class Subway {
    private List stations;      // Composition
    private List connections;   // Composition
    private Map network;        // Composition
    
    // Subway composes Station and Connection objects
    // Rather than inheriting from them
}

// ModernSubwayGUI.java - Composition of UI components
public class ModernSubwayGUI extends JFrame {
    private Subway subway;              // Composition
    private JComboBox<String> startStationCombo;  // Composition
    private JComboBox<String> endStationCombo;    // Composition
    private JTextArea directionsArea;   // Composition
    // GUI composes various UI components rather than inheriting from them
}
```

**Benefits**:
- **Flexibility**: Can change component behavior independently
- **Loose Coupling**: Components can be swapped easily
- **Testability**: Can mock individual components

## 🎨 Design Patterns

### 1. **Model-View-Controller (MVC) Pattern**

**Structure**:
- **Model**: `Subway`, `Station`, `Connection` (business logic and data)
- **View**: `SubwayPrinter`, `SubwayGUI`, `ModernSubwayGUI` (presentation)
- **Controller**: Route finding logic in `Subway.getDirections()`

```java
// Model
public class Subway {
    // Business logic and data
}

// View
public class SubwayPrinter {
    public void printDirections(List route) {
        // Presentation logic
    }
}

// Controller (in Subway class)
public List getDirections(String start, String end) {
    // Orchestrates model operations
    // Returns data for view to display
}
```

### 2. **Factory Pattern**

**Purpose**: Create objects without specifying exact classes.

```java
// SubwayLoader.java - Factory for creating Subway instances
public class SubwayLoader {
    public Subway loadFromFile(File subwayFile) throws IOException {
        // Creates and configures Subway objects
        Subway subway = new Subway();
        // ... configuration logic
        return subway;
    }
}
```

### 3. **Strategy Pattern**

**Purpose**: Define family of algorithms and make them interchangeable.

```java
// Different printing strategies
public class SubwayPrinter {
    // Strategy for formatting directions
    public void printDirections(List route) {
        // Specific formatting strategy
    }
}

// Could easily add different printing strategies:
// - ConsolePrinter
// - HTMLPrinter
// - PDFPrinter
```

### 4. **Observer Pattern**

**Purpose**: Define one-to-many dependency between objects.

```java
// SwingWorker implements observer pattern
SwingWorker<List, Void> worker = new SwingWorker<List, Void>() {
    @Override
    protected List doInBackground() throws Exception {
        return subway.getDirections(startStation, endStation);
    }
    
    @Override
    protected void done() {
        // Observer notified when background work completes
        try {
            List route = get();
            displayRoute(route, startStation, endStation);
        } catch (Exception ex) {
            // Handle errors
        }
    }
};
```

## 🔧 SOLID Principles

### 1. **Single Responsibility Principle (SRP)**

Each class has one reason to change:

- `Station.java`: Only responsible for station data and identity
- `Connection.java`: Only responsible for connection data
- `SubwayLoader.java`: Only responsible for loading data
- `SubwayPrinter.java`: Only responsible for formatting output

### 2. **Open/Closed Principle (OCP)**

Open for extension, closed for modification:

```java
// Easy to extend with new GUI variants
public class ModernSubwayGUI extends JFrame {
    // Extends functionality without modifying existing code
}

// Easy to add new printing strategies
public class SubwayPrinter {
    // Can be extended with new formatting without modifying existing code
}
```

### 3. **Liskov Substitution Principle (LSP)**

Subtypes are substitutable for their base types:

```java
// ModernSubwayGUI can be used anywhere JFrame is expected
ModernSubwayGUI gui = new ModernSubwayGUI();
// All JFrame methods work correctly
```

### 4. **Interface Segregation Principle (ISP)**

Clients shouldn't depend on interfaces they don't use:

```java
// Each class exposes only the methods it needs
public class Station {
    // Only station-related methods
}

public class Connection {
    // Only connection-related methods
}
```

### 5. **Dependency Inversion Principle (DIP)**

Depend on abstractions, not concretions:

```java
// Subway depends on abstract List interface, not concrete implementation
private List stations;
private List connections;

// GUI depends on abstract Subway interface
private Subway subway;
```

## 🏛️ Architectural Patterns

### 1. **Layered Architecture**

```
┌─────────────────────────────────────┐
│           Presentation Layer        │
│  (GUI, CLI, Printers)              │
├─────────────────────────────────────┤
│           Application Layer         │
│  (Route Finding Logic)              │
├─────────────────────────────────────┤
│           Domain Layer              │
│  (Subway, Station, Connection)     │
├─────────────────────────────────────┤
│           Infrastructure Layer      │
│  (File Loading, Data Persistence)  │
└─────────────────────────────────────┘
```

### 2. **Domain-Driven Design (DDD)**

**Domain Entities**:
- `Station`: Core business entity
- `Connection`: Core business entity
- `Subway`: Aggregate root

**Value Objects**:
- Station names
- Line names
- Route paths

**Services**:
- `SubwayLoader`: Domain service for data loading
- `SubwayPrinter`: Domain service for output formatting

## 🔄 Design Principles in Action

### 1. **Separation of Concerns**

Each class has a specific responsibility:

```java
// Data loading concern
public class SubwayLoader {
    public Subway loadFromFile(File subwayFile) throws IOException {
        // Only handles file loading
    }
}

// Route finding concern
public class Subway {
    public List getDirections(String start, String end) {
        // Only handles route finding
    }
}

// Presentation concern
public class SubwayPrinter {
    public void printDirections(List route) {
        // Only handles output formatting
    }
}
```

### 2. **Don't Repeat Yourself (DRY)**

Common functionality extracted into reusable methods:

```java
// Reusable button creation
private JButton createModernButton(String text, Color bgColor) {
    // Common button styling logic
}

// Reusable combo box creation
private JComboBox<String> createModernComboBox() {
    // Common combo box styling logic
}
```

### 3. **Keep It Simple, Stupid (KISS)**

Simple, readable implementations:

```java
// Simple, clear method names
public void addStation(String stationName) {
    if (!this.hasStation(stationName)) {
        Station station = new Station(stationName);
        stations.add(station);
    }
}

// Clear, descriptive variable names
String startStation = (String) startStationCombo.getSelectedItem();
String endStation = (String) endStationCombo.getSelectedItem();
```

## 🧪 Testing and Maintainability

### 1. **Testability**

Classes are designed for easy testing:

```java
// Can test Subway independently
Subway subway = new Subway();
subway.addStation("Test Station");
assert subway.hasStation("Test Station");

// Can test GUI components independently
JButton button = createModernButton("Test", Color.BLUE);
assert button.getText().equals("Test");
```

### 2. **Maintainability**

Clear structure makes maintenance easy:

- **Modular Design**: Changes to one component don't affect others
- **Clear Naming**: Method and variable names are self-documenting
- **Consistent Patterns**: Similar problems solved in similar ways
- **Loose Coupling**: Components can be modified independently

## 🚀 Extensibility

The design supports easy extension:

### Adding New Features:
- **New GUI Themes**: Extend existing GUI classes
- **New Data Sources**: Implement new loader classes
- **New Output Formats**: Add new printer classes
- **New Algorithms**: Extend route finding logic

### Example Extension:
```java
// Easy to add new GUI variant
public class DarkThemeSubwayGUI extends ModernSubwayGUI {
    // Override color scheme for dark theme
}

// Easy to add new data source
public class DatabaseSubwayLoader implements SubwayLoader {
    // Load from database instead of file
}
```

## 📚 Best Practices Demonstrated

1. **Consistent Naming**: Clear, descriptive names throughout
2. **Error Handling**: Proper exception handling and user feedback
3. **Documentation**: Self-documenting code with clear structure
4. **Performance**: Efficient algorithms (breadth-first search)
5. **User Experience**: Responsive UI with background processing
6. **Code Organization**: Logical package structure and file organization

This architecture demonstrates how OOP principles and design patterns create a robust, maintainable, and extensible application that can evolve with changing requirements. 