import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.util.Collections;
import java.util.Locale;

public class MD5Checksum {
    public static void main(String[] args) {
        String directoryPath = "/path/to/fileOrDirectory";
        List<String> filePaths = new ArrayList<>();
        try {
            listFilesRecursively(Paths.get(directoryPath), filePaths);
            for(int i = 0 ; i<filePaths.size();i++){
                String hashThenString = calculateFileMD5(filePaths.get(i)) + "  " + filePaths.get(i);
                filePaths.set(i, hashThenString);
            }
            Collections.sort(filePaths, Collator.getInstance(Locale.US));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String resultString = listToString(filePaths);
        String resultHash = calculateStringMD5(resultString);
        System.out.println(resultHash);
    }

    public static void listFilesRecursively(Path directoryPath, List<String> filePaths) throws IOException {
        Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (Files.isRegularFile(file)) {
                    filePaths.add(file.toAbsolutePath().toString());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                // Handle the case where the file cannot be visited due to some error.
                // Return FileVisitResult.CONTINUE to continue visiting other files.
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // Skip adding the starting directory to the list
                if (dir.equals(directoryPath)) {
                    return FileVisitResult.CONTINUE;
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static String calculateFileMD5(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        Path path = Paths.get(filePath);

        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        byte[] md5Bytes = md.digest();
        StringBuilder md5Hash = new StringBuilder();
        for (byte b : md5Bytes) {
            md5Hash.append(String.format("%02x", b));
        }

        return md5Hash.toString();
    }

    public static String listToString(List<String> arrayOfStrings) {
        StringBuilder combinedString = new StringBuilder();
        for (String str : arrayOfStrings) {
            combinedString.append(str).append("\n");
        }
        return combinedString.toString();
    }

    public static String calculateStringMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(input.getBytes());

            StringBuilder md5Hash = new StringBuilder();
            for (byte b : md5Bytes) {
                md5Hash.append(String.format("%02x", b));
            }

            return md5Hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
