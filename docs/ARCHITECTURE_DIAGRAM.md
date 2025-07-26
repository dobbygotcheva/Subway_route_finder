# Subway Route Finder - Architecture Diagram

## 🏗️ System Architecture Overview

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                           PRESENTATION LAYER                               │
├─────────────────────────────────────────────────────────────────────────────┤
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────────────────┐ │
│  │   SubwayGUI     │  │ ModernSubwayGUI │  │      SubwayPrinter         │ │
│  │   (Basic UI)    │  │   (Enhanced UI) │  │    (Output Formatting)     │ │
│  └─────────────────┘  └─────────────────┘  └─────────────────────────────┘ │
│           │                     │                           │               │
│           └─────────────────────┼───────────────────────────┘               │
│                                 │                                         │
└─────────────────────────────────┼─────────────────────────────────────────┘
                                  │
┌─────────────────────────────────┼─────────────────────────────────────────┐
│                        APPLICATION LAYER                                  │
├─────────────────────────────────┼─────────────────────────────────────────┤
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────────────────┐ │
│  │ SubwayTester    │  │   LoadTester    │  │         Demo               │ │
│  │ (CLI Interface) │  │   (Testing)     │  │    (Demonstration)         │ │
│  └─────────────────┘  └─────────────────┘  └─────────────────────────────┘ │
│           │                     │                           │               │
│           └─────────────────────┼───────────────────────────┘               │
│                                 │                                         │
└─────────────────────────────────┼─────────────────────────────────────────┘
                                  │
┌─────────────────────────────────┼─────────────────────────────────────────┐
│                         DOMAIN LAYER                                     │
├─────────────────────────────────┼─────────────────────────────────────────┤
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │                        Subway                                      │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────────────┐ │   │
│  │  │   Station   │  │ Connection  │  │      Route Finding          │ │   │
│  │  │   Entity    │  │   Entity    │  │      Algorithm              │ │   │
│  │  └─────────────┘  └─────────────┘  └─────────────────────────────┘ │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
│                                 │                                         │
└─────────────────────────────────┼─────────────────────────────────────────┘
                                  │
┌─────────────────────────────────┼─────────────────────────────────────────┐
│                    INFRASTRUCTURE LAYER                                  │
├─────────────────────────────────┼─────────────────────────────────────────┤
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │                    SubwayLoader                                    │   │
│  │              (Data Loading Service)                                │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
│                                 │                                         │
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │                 ObjectvilleSubway.txt                              │   │
│  │                    (Data Source)                                   │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────────────┘
```

## 🔄 Data Flow Diagram

```
User Input
    │
    ▼
┌─────────────┐
│   GUI/CLI   │ ◄─── Presentation Layer
│  Interface  │
└─────────────┘
    │
    ▼
┌─────────────┐
│   Subway    │ ◄─── Application Layer
│  (Orchestrator)
└─────────────┘
    │
    ▼
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│  Station    │    │ Connection  │    │   Network   │ ◄─── Domain Layer
│  Entities   │    │  Entities   │    │   Graph     │
└─────────────┘    └─────────────┘    └─────────────┘
    │                   │                   │
    └───────────────────┼───────────────────┘
                        │
                        ▼
┌─────────────┐    ┌─────────────┐
│SubwayLoader │    │   File      │ ◄─── Infrastructure Layer
│  (Factory)  │    │  Data       │
└─────────────┘    └─────────────┘
```

## 🎯 Class Relationships

### Inheritance Hierarchy
```
JFrame (Swing)
    │
    └── SubwayGUI
        │
        └── ModernSubwayGUI
```

### Composition Relationships
```
Subway
├── List<Station> stations
├── List<Connection> connections
└── Map<Station, List<Station>> network

ModernSubwayGUI
├── Subway subway
├── JComboBox<String> startStationCombo
├── JComboBox<String> endStationCombo
├── JTextArea directionsArea
├── JButton findRouteButton
├── JButton clearButton
└── JLabel statusLabel
```

### Dependencies
```
SubwayGUI ──► Subway ──► Station
     │           │           │
     │           │           └── Connection
     │           │
     │           └── SubwayLoader ──► File
     │
     └── SubwayPrinter

ModernSubwayGUI ──► Subway
        │              │
        │              └── (same as above)
        │
        └── SwingWorker (Background Processing)
```

## 🔧 Design Patterns Visualization

### 1. MVC Pattern
```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│    Model    │    │   View      │    │ Controller  │
│             │    │             │    │             │
│  Subway     │◄──►│ SubwayGUI   │◄──►│ Route       │
│  Station    │    │ SubwayPrinter│   │ Finding     │
│  Connection │    │             │    │ Logic       │
└─────────────┘    └─────────────┘    └─────────────┘
```

### 2. Factory Pattern
```
┌─────────────┐    ┌─────────────┐
│ SubwayLoader│───►│   Subway    │
│  (Factory)  │    │  (Product)  │
└─────────────┘    └─────────────┘
```

### 3. Strategy Pattern
```
┌─────────────┐
│ SubwayPrinter│
│  (Strategy) │
└─────────────┘
      │
      ├── ConsolePrinter (could be added)
      ├── HTMLPrinter (could be added)
      └── PDFPrinter (could be added)
```

### 4. Observer Pattern
```
┌─────────────┐    ┌─────────────┐
│ SwingWorker │───►│   GUI       │
│  (Subject)  │    │ (Observer)  │
└─────────────┘    └─────────────┘
```

## 🎨 SOLID Principles Implementation

### Single Responsibility Principle
```
Station.java     ──► Station data and identity
Connection.java  ──► Connection data
SubwayLoader.java ──► Data loading
SubwayPrinter.java ──► Output formatting
Subway.java      ──► Route finding and network management
```

### Open/Closed Principle
```
JFrame
  │
  ├── SubwayGUI (closed for modification)
  └── ModernSubwayGUI (open for extension)
```

### Liskov Substitution Principle
```
ModernSubwayGUI can substitute JFrame anywhere
```

### Interface Segregation Principle
```
Station exposes only station-related methods
Connection exposes only connection-related methods
```

### Dependency Inversion Principle
```
GUI depends on Subway abstraction, not concrete implementation
Subway depends on List/Map abstractions, not concrete implementations
```

## 🚀 Extension Points

### Easy to Add:
```
New GUI Themes
├── DarkThemeSubwayGUI
├── LightThemeSubwayGUI
└── CustomThemeSubwayGUI

New Data Sources
├── DatabaseSubwayLoader
├── APISubwayLoader
└── XMLSubwayLoader

New Output Formats
├── HTMLSubwayPrinter
├── PDFSubwayPrinter
└── JSONSubwayPrinter

New Algorithms
├── DijkstraRouteFinder
├── AStarRouteFinder
└── MultiPathRouteFinder
```

This architecture demonstrates clean separation of concerns, loose coupling, and high cohesion - making the system maintainable, testable, and extensible. 