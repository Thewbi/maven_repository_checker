package fileiterator;

import java.io.IOException;
import java.net.URISyntaxException;

public interface PathIterator {

	void iterate(String path) throws IOException, URISyntaxException;
	
}
