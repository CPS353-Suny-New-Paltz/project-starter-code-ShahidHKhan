package project.api;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
    // User specifies input source (file, DB, etc.)
    void setInputSource(String source);

    // User specifies output destination (file, DB, etc.)
    void setOutputDestination(String destination);

    // User specifies custom delimiters OR defaults
    void setDelimiters(String delimiters);
}