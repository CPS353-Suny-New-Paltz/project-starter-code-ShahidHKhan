package project.usercompute;

import java.util.List;

@FunctionalInterface
public interface DataSource {
   
    List<Integer> get();
}
