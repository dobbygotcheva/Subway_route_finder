package com.subway.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import com.subway.core.Subway;
import com.subway.core.SubwayLoader;
import com.subway.gui.SubwayPrinter;

public class SubwayGUI extends JFrame {
    private Subway subway;
    private JComboBox<String> startStationCombo;
    private JComboBox<String> endStationCombo;
    private JTextArea directionsArea;
    private JButton findRouteButton;
    private JButton clearButton;
    private JLabel statusLabel;
    
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
    
    public SubwayGUI() {
        setTitle("Objectville Subway Route Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        
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
    }
    
    private void initComponents() {
        // Create components
        startStationCombo = new JComboBox<>(stationNames);
        endStationCombo = new JComboBox<>(stationNames);
        directionsArea = new JTextArea();
        directionsArea.setEditable(false);
        directionsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        directionsArea.setLineWrap(true);
        directionsArea.setWrapStyleWord(true);
        
        findRouteButton = new JButton("Find Route");
        findRouteButton.setBackground(new Color(70, 130, 180));
        findRouteButton.setForeground(Color.WHITE);
        findRouteButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(220, 20, 60));
        clearButton.setForeground(Color.WHITE);
        
        statusLabel = new JLabel("Ready to find routes");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(new Color(100, 100, 100));
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // North panel - Title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(25, 25, 112));
        JLabel titleLabel = new JLabel("🚇 Objectville Subway Route Finder");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Center panel - Main content
        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Station selection panel
        JPanel selectionPanel = new JPanel(new GridBagLayout());
        selectionPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Select Stations", 
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        selectionPanel.add(new JLabel("From Station:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        selectionPanel.add(startStationCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.0;
        selectionPanel.add(new JLabel("To Station:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        selectionPanel.add(endStationCombo, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(findRouteButton);
        buttonPanel.add(clearButton);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        selectionPanel.add(buttonPanel, gbc);
        
        centerPanel.add(selectionPanel, BorderLayout.NORTH);
        
        // Directions panel
        JPanel directionsPanel = new JPanel(new BorderLayout());
        directionsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Route Directions", 
            TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12)));
        
        JScrollPane scrollPane = new JScrollPane(directionsArea);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        directionsPanel.add(scrollPane, BorderLayout.CENTER);
        
        centerPanel.add(directionsPanel, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // South panel - Status
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);
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
            JOptionPane.showMessageDialog(this, "Please select different start and end stations.", 
                                        "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            statusLabel.setText("Finding route from " + startStation + " to " + endStation + "...");
            findRouteButton.setEnabled(false);
            
            // Run route finding in background to keep UI responsive
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
                        statusLabel.setText("Route found successfully!");
                    } catch (Exception ex) {
                        statusLabel.setText("Error finding route: " + ex.getMessage());
                        directionsArea.setText("Error: Could not find route between the specified stations.\n\n" +
                                            "Please check that both stations exist and are connected.");
                    } finally {
                        findRouteButton.setEnabled(true);
                    }
                }
            };
            worker.execute();
            
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            directionsArea.setText("Error occurred while finding route.");
            findRouteButton.setEnabled(true);
        }
    }
    
    private void displayRoute(List route, String startStation, String endStation) {
        if (route == null || route.isEmpty()) {
            directionsArea.setText("No route found between " + startStation + " and " + endStation + ".\n\n" +
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
        
        // Add route summary
        directionsArea.append("\n\n" + "=".repeat(50) + "\n");
        directionsArea.append("Route Summary:\n");
        directionsArea.append("From: " + startStation + "\n");
        directionsArea.append("To: " + endStation + "\n");
        directionsArea.append("Total connections: " + route.size() + "\n");
        directionsArea.append("=".repeat(50));
    }
    
    private void clearDirections() {
        directionsArea.setText("");
        statusLabel.setText("Ready to find routes");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SubwayGUI gui = new SubwayGUI();
            gui.setVisible(true);
        });
    }
} 