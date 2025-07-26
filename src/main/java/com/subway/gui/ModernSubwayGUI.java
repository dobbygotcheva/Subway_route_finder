package com.subway.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.List;
import com.subway.core.Subway;
import com.subway.core.SubwayLoader;
import com.subway.gui.SubwayPrinter;

public class ModernSubwayGUI extends JFrame {
    private Subway subway;
    private JComboBox<String> startStationCombo;
    private JComboBox<String> endStationCombo;
    private JTextArea directionsArea;
    private JButton findRouteButton;
    private JButton clearButton;
    private JLabel statusLabel;
    private JPanel mainPanel;
    
    // Modern color scheme
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private static final Color SECONDARY_COLOR = new Color(41, 128, 185);
    private static final Color ACCENT_COLOR = new Color(231, 76, 60);
    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(52, 73, 94);
    private static final Color LIGHT_TEXT_COLOR = new Color(149, 165, 166);
    
    private String[] stationNames = {
        "DRY Drive",
        "Weather-O-Rama, Inc.",
        "Boards 'R' Us",
        "Head First Theater",
        "LSP Lane",
        "JavaBeans Boulevard",
        "OOA&D Oval",
        "Head First Labs",
        "Infinite Circle",
        "Long Way",
        "Null Object",
        "Prime Numbers",
        "Fibonacci"
    };
    
    public ModernSubwayGUI() {
        setTitle("🚇 Modern Subway Route Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Load subway data
        try {
            SubwayLoader loader = new SubwayLoader();
            subway = loader.loadFromFile(new File("data/ObjectvilleSubway.txt"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading subway data: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        initComponents();
        layoutComponents();
        setupEventHandlers();
        applyModernStyling();
    }
    
    private void initComponents() {
        // Create components with modern styling
        startStationCombo = createModernComboBox();
        endStationCombo = createModernComboBox();
        
        directionsArea = new JTextArea();
        directionsArea.setEditable(false);
        directionsArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        directionsArea.setLineWrap(true);
        directionsArea.setWrapStyleWord(true);
        directionsArea.setBackground(CARD_COLOR);
        directionsArea.setForeground(TEXT_COLOR);
        directionsArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        findRouteButton = createModernButton("🔍 Find Route", PRIMARY_COLOR);
        clearButton = createModernButton("🗑️ Clear", ACCENT_COLOR);
        
        statusLabel = new JLabel("Ready to find your route");
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setForeground(LIGHT_TEXT_COLOR);
        
        mainPanel = new JPanel();
        mainPanel.setBackground(BACKGROUND_COLOR);
    }
    
    private JComboBox<String> createModernComboBox() {
        JComboBox<String> combo = new JComboBox<>(stationNames);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(CARD_COLOR);
        combo.setForeground(TEXT_COLOR);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return combo;
    }
    
    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        
        // Header with gradient
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content
        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Selection card
        JPanel selectionCard = createSelectionCard();
        contentPanel.add(selectionCard, BorderLayout.NORTH);
        
        // Directions card
        JPanel directionsCard = createDirectionsCard();
        contentPanel.add(directionsCard, BorderLayout.CENTER);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Status bar
        JPanel statusPanel = createStatusPanel();
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, PRIMARY_COLOR,
                    getWidth(), 0, SECONDARY_COLOR
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(900, 80));
        
        JLabel titleLabel = new JLabel("🚇 Modern Subway Route Finder");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        header.add(titleLabel, BorderLayout.CENTER);
        
        return header;
    }
    
    private JPanel createSelectionCard() {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(15, 15));
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Title
        JLabel cardTitle = new JLabel("📍 Select Your Route");
        cardTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cardTitle.setForeground(TEXT_COLOR);
        card.add(cardTitle, BorderLayout.NORTH);
        
        // Selection panel
        JPanel selectionPanel = new JPanel(new GridBagLayout());
        selectionPanel.setBackground(CARD_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // From station
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel fromLabel = new JLabel("🚉 From:");
        fromLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        fromLabel.setForeground(TEXT_COLOR);
        selectionPanel.add(fromLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        selectionPanel.add(startStationCombo, gbc);
        
        // To station
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel toLabel = new JLabel("🎯 To:");
        toLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        toLabel.setForeground(TEXT_COLOR);
        selectionPanel.add(toLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        selectionPanel.add(endStationCombo, gbc);
        
        card.add(selectionPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(CARD_COLOR);
        buttonPanel.add(findRouteButton);
        buttonPanel.add(clearButton);
        
        card.add(buttonPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createDirectionsCard() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Title
        JLabel cardTitle = new JLabel("🗺️ Route Directions");
        cardTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cardTitle.setForeground(TEXT_COLOR);
        card.add(cardTitle, BorderLayout.NORTH);
        
        // Directions area with custom scroll pane
        JScrollPane scrollPane = new JScrollPane(directionsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Custom scroll pane styling
        scrollPane.getViewport().setBackground(CARD_COLOR);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        card.add(scrollPane, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(BACKGROUND_COLOR);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        statusPanel.add(statusLabel);
        return statusPanel;
    }
    
    private void applyModernStyling() {
        // Set default font for all components
        UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 16));
    }
    
    private void setupEventHandlers() {
        findRouteButton.addActionListener(e -> findRoute());
        clearButton.addActionListener(e -> clearDirections());
        
        // Add some example routes to combo boxes
        startStationCombo.setSelectedItem("DRY Drive");
        endStationCombo.setSelectedItem("Head First Theater");
    }
    
    private void findRoute() {
        String startStation = (String) startStationCombo.getSelectedItem();
        String endStation = (String) endStationCombo.getSelectedItem();
        
        if (startStation.equals(endStation)) {
            showModernMessage("Please select different start and end stations.", 
                            "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            statusLabel.setText("🔍 Finding route from " + startStation + " to " + endStation + "...");
            findRouteButton.setEnabled(false);
            findRouteButton.setText("⏳ Searching...");
            
            // Run route finding in background
            SwingWorker<List, Void> worker = new SwingWorker<List, Void>() {
                @Override
                protected List doInBackground() throws Exception {
                    return subway.getDirections(startStation, endStation);
                }
                
                @Override
                protected void done() {
                    try {
                        List route = get();
                        displayRoute(route, startStation, endStation);
                        statusLabel.setText("✅ Route found successfully!");
                        statusLabel.setForeground(SUCCESS_COLOR);
                    } catch (Exception ex) {
                        statusLabel.setText("❌ Error finding route: " + ex.getMessage());
                        statusLabel.setForeground(ACCENT_COLOR);
                        directionsArea.setText("Error: Could not find route between the specified stations.\n\n" +
                                            "Please check that both stations exist and are connected.");
                    } finally {
                        findRouteButton.setEnabled(true);
                        findRouteButton.setText("🔍 Find Route");
                    }
                }
            };
            worker.execute();
            
        } catch (Exception e) {
            statusLabel.setText("❌ Error: " + e.getMessage());
            statusLabel.setForeground(ACCENT_COLOR);
            directionsArea.setText("Error occurred while finding route.");
            findRouteButton.setEnabled(true);
            findRouteButton.setText("🔍 Find Route");
        }
    }
    
    private void displayRoute(List route, String startStation, String endStation) {
        if (route == null || route.isEmpty()) {
            directionsArea.setText("❌ No route found between " + startStation + " and " + endStation + ".\n\n" +
                                "The stations may not be connected in the subway network.");
            return;
        }
        
        // Capture the output from SubwayPrinter
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        SubwayPrinter printer = new SubwayPrinter(ps);
        printer.printDirections(route);
        
        String directions = baos.toString();
        directionsArea.setText(directions);
        
        // Add modern route summary with larger fonts
        directionsArea.append("\n\n" + "━".repeat(60) + "\n");
        directionsArea.append("📊 ROUTE SUMMARY\n");
        directionsArea.append("━".repeat(60) + "\n");
        directionsArea.append("🚉 FROM: " + startStation.toUpperCase() + "\n");
        directionsArea.append("🎯 TO: " + endStation.toUpperCase() + "\n");
        directionsArea.append("🔗 TOTAL CONNECTIONS: " + route.size() + "\n");
        directionsArea.append("━".repeat(60));
    }
    
    private void clearDirections() {
        directionsArea.setText("");
        statusLabel.setText("Ready to find your route");
        statusLabel.setForeground(LIGHT_TEXT_COLOR);
    }
    
    private void showModernMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModernSubwayGUI gui = new ModernSubwayGUI();
            gui.setVisible(true);
        });
    }
} 