package project.datacompute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataComputeAPIImpl implements DataComputeAPI {

    @Override
    public List<Integer> readInput(String inputPath) {
        
        if (inputPath == null || inputPath.isBlank()) {
            throw new IllegalArgumentException("inputPath cannot be null or blank.");
        }

        Path path = Path.of(inputPath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Input file does not exist: " + inputPath);
        }
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("Input file is not readable: " + inputPath);
        }

        List<Integer> ns = new ArrayList<>();
        try (BufferedReader r = Files.newBufferedReader(path)) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        ns.add(Integer.parseInt(line));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read input file: " + e.getMessage(), e);
        }

        return ns;
    }

    @Override
    public void writeOutput(List<String> out, String outputPath) {
        
        if (out == null) {
            throw new IllegalArgumentException("Output list cannot be null.");
        }
        if (outputPath == null || outputPath.isBlank()) {
            throw new IllegalArgumentException("outputPath cannot be null or blank.");
        }

        Path path = Path.of(outputPath);
        try (BufferedWriter w = Files.newBufferedWriter(path)) {
            for (String s : out) {
                w.write(s == null ? "" : s);
                w.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to write output file: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertRequest(DataRequest dataRequest) {
        
        if (dataRequest == null) {
            throw new IllegalArgumentException("DataRequest cannot be null.");
        }
    }
}
