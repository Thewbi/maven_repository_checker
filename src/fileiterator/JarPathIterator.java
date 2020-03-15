package fileiterator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarPathIterator implements PathIterator {
	
	private int brokenFiles;

	public void iterate(String filepath) throws IOException, URISyntaxException {

		Path path = Paths.get(filepath);

		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.jar");

		Files.walk(path).filter(Files::isRegularFile).filter(p -> matcher.matches(p)).forEach(p -> {
			checkHealth(p);
		});

		if (brokenFiles == 0) {
			System.out.println("No broken files detected in \"" + filepath + "\"!");
		} else {
			System.out.println(brokenFiles + " broken files detected in \"" + filepath + "\"!");
		}
	}

	private void checkHealth(Path path) {

		// if a jar file is not a valid zip file, it is broken!
		// Files break if restricted network connections are used.
		// Instead of downloading a jar file, the firewall will return
		// a html page with a notice saying that the connection is blocked.
		// Maven will happily take that html page and store it as the downloaded
		// jar file. 
		// This in turn will serve broken dependencies to your build, breaking 
		// your build in the process.
		try {
			
			try (ZipFile file = new ZipFile(path.toFile())) {
				
				Enumeration<? extends ZipEntry> e = file.entries();
				while (e.hasMoreElements()) {
					
					@SuppressWarnings("unused")
					ZipEntry entry = e.nextElement();
				}
			}
		} catch (Exception e) {
			System.out.println(path);
			brokenFiles++;
		}
	}
}
