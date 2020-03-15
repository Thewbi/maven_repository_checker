package fileiterator;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		JarPathIterator jarPathIterator = new JarPathIterator();
		jarPathIterator.iterate("C:/Users/U5353/.m2/repository");
//		jarPathIterator.iterate("C:/PR_TBE/GitCheckouts/cu450-cockpit/External/develop-image-security-updates/target/bundles");
//		jarPathIterator.iterate("C:/dev/mbs-sh-sdk/runtime");
		
	}

}
