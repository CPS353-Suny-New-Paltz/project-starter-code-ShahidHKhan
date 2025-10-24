package project.datacompute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataComputeAPIFileImpl implements DataComputeAPI {

    @Override
    public List<Integer> readInput(String inputPath) {
        // Validate parameter
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

        List<Integer> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    try {
                        result.add(Integer.parseInt(trimmed));
                    } catch (NumberFormatException expected) {
                        // skip malformed lines
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read input file: " + e.getMessage(), e);
        }

        return result;
    }

    @Override
    public void writeOutput(List<String> out, String outputPath) {
        // Validate parameters
        if (out == null) {
            throw new IllegalArgumentException("Output list cannot be null.");
        }
        if (outputPath == null || outputPath.isBlank()) {
            throw new IllegalArgumentException("outputPath cannot be null or blank.");
        }

        Path path = Path.of(outputPath);
        String line = String.join(",", out) + System.lineSeparator();

        try {
            Files.writeString(path, line, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write output file: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertRequest(DataRequest dataRequest) {
        // Validate parameter
        if (dataRequest == null) {
            throw new IllegalArgumentException("DataRequest cannot be null.");
        }
    }
}