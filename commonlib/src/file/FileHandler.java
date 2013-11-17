package file;

import java.io.File;
import java.util.Collection;

public interface FileHandler {
	
	<T> void handle(File file, Collection<T> t);

}
