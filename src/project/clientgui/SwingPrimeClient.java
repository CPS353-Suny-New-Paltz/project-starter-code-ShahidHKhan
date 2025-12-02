package project.clientgui;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.GridLayout;

import java.io.File;
import java.nio.file.Files;

import java.util.List;
import java.util.ArrayList;

import project.intercompute.FastInterComputeAPIImpl;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

//loads SWING
public class SwingPrimeClient extends JFrame {

	//creates an instance of my computeengine that gui will call
    private final InterComputeAPI engine = new FastInterComputeAPIImpl();

    private JTextField inlineInputField;
    private JLabel inlineResultLabel;
    private JLabel inlineStatusLabel;

    private JLabel fileInputLabel;
    private JLabel fileOutputLabel;
    private JLabel fileStatusLabel;

    private File inputFile = null;
    private File outputFile = null;

    public SwingPrimeClient() {
        super("Compute Engine Client");

        // constructor
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Inline Input", createInlinePanel());
        tabs.add("File Input/Output", createFilePanel());

        add(tabs);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createInlinePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 8, 8));

        inlineInputField = new JTextField();
        inlineResultLabel = new JLabel("Result: ");
        inlineStatusLabel = new JLabel("Status: Ready");

        JButton computeButton = new JButton("Compute");
        computeButton.addActionListener(e -> runInlineCompute());

        panel.add(new JLabel("Enter a number:"));
        panel.add(inlineInputField);
        panel.add(computeButton);
        panel.add(inlineResultLabel);
        panel.add(inlineStatusLabel);

        return panel;
    }

    private void runInlineCompute() {
        try {
        	//running a SINGLE compute request (inline)
            int n = Integer.parseInt(inlineInputField.getText().trim());
            int result = engine.processRequest(new InterRequest(n));

            if (result < 0) {
                inlineResultLabel.setText("Result: (no prime <= " + n + ")");
            } else {
                inlineResultLabel.setText("Result: " + result);
            }
            inlineStatusLabel.setText("Status: Success");
        } catch (NumberFormatException ex) {
            inlineStatusLabel.setText("Status: Invalid number format.");
        }
    }

    //this is the file handling, that will take in files and print files
    private JPanel createFilePanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 8, 8));

        JButton chooseInputBtn = new JButton("Choose Input File");
        chooseInputBtn.addActionListener(e -> chooseInputFile());

        JButton chooseOutputBtn = new JButton("Choose Output File");
        chooseOutputBtn.addActionListener(e -> chooseOutputFile());

        JButton runJobBtn = new JButton("Run Job");
        runJobBtn.addActionListener(e -> runFileJob());

        fileInputLabel = new JLabel("Input File: (none)");
        fileOutputLabel = new JLabel("Output File: (none)");
        fileStatusLabel = new JLabel("Status: Waiting");

        panel.add(chooseInputBtn);
        panel.add(fileInputLabel);
        panel.add(chooseOutputBtn);
        panel.add(fileOutputLabel);
        panel.add(runJobBtn);
        panel.add(fileStatusLabel);

        return panel;
    }

    //chooser logic
    private void chooseInputFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputFile = chooser.getSelectedFile();
            fileInputLabel.setText("Input File: " + inputFile.getAbsolutePath());
        }
    }

    private void chooseOutputFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            outputFile = chooser.getSelectedFile();
            fileOutputLabel.setText("Output File: " + outputFile.getAbsolutePath());
        }
    }

    private void runFileJob() {
        if (inputFile == null || outputFile == null) {
            fileStatusLabel.setText("Status: Choose both files.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(inputFile.toPath());
            List<Integer> nums = lines.stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .toList();

            List<Integer> outputs = new ArrayList<>();
            for (int n : nums) {
                int result = engine.processRequest(new InterRequest(n));
                outputs.add(result);
            }

            List<String> outLines = outputs.stream()
                    .map(String::valueOf)
                    .toList();
            Files.write(outputFile.toPath(), outLines);

            fileStatusLabel.setText("Status: Job complete. " +
                    outputs.size() + " results written.");

        } catch (NumberFormatException ex) {
            fileStatusLabel.setText("Status: Error - invalid number in input file.");
        } catch (Exception ex) {
            fileStatusLabel.setText("Status: Error - " + ex.getMessage());
        }
    }

    //main method for EVERYTHING
    public static void main(String[] args) {
        new SwingPrimeClient();
    }
}
