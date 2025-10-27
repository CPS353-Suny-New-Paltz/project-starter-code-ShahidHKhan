package project.datacompute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
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
	    try (BufferedReader r = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
	        String line;
	        while ((line = r.readLine()) != null) {
	            line = line.trim();
	            if (!line.isEmpty()) {
	                try {
	                    ns.add(Integer.parseInt(line));
	                } catch (NumberFormatException ignored) {
	                    // skip malformed lines
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
	    try {
	        // makes sure parent directories exist
	        Path parent = path.getParent();
	        if (parent != null && !Files.exists(parent)) {
	            Files.createDirectories(parent);
	        }

	        String csv = String.join(",", out.stream()
	                .map(s -> s == null ? "" : s)
	                .toList()) + System.lineSeparator();

	        // uses my standardCHarset to Overwrite file
	        Files.writeString(
	                path,
	                csv,
	                java.nio.charset.StandardCharsets.UTF_8,
	                java.nio.file.StandardOpenOption.CREATE,
	                java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
	        );
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
