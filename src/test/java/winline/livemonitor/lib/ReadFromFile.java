package winline.livemonitor.lib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFromFile {

	public static String getText(String FILE_NAME) throws IOException {
		String body = "";
		List<String> lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
		for (String line : lines) {
			body += line;
			
		}
		return body;
	}

	public static String getText1(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public static String getText2(String filePath) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String line;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((line = br.readLine()) != null)
			buffer.append(line);

		return buffer.toString();
	}
}
