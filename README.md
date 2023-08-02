
# MD5CheckSumGenerator Java Program

## Description

The MD5CheckSumGenerator Java program is designed to replicate the effect of the following bash command:

```bash
find "$PATH" -type f -exec md5sum {} + | LC_ALL=C sort | md5sum
```

This bash command finds all regular files in the PATH directory and its subdirectories, calculates the MD5 checksum for each file, sorts the output based on the entire line (MD5 checksum followed by the file path) using the "C" locale, and finally calculates the MD5 checksum of the combined sorted output.

## Purpose

The purpose of this Java program is to recursively list all regular files in a given directory and its subdirectories, calculate the MD5 hash for each file, and then concatenate the MD5 checksum with the corresponding file path. It then sorts the list of MD5 checksums and file paths based on the entire line using the default "C" locale, and finally, it calculates the MD5 hash of the entire sorted list.

## How to Use

1. Ensure you have Java installed on your system.
2. Modify the `directoryPath` variable in the `main` method to point to the desired directory you want to analyze.
3. Run the program. It will list all the file paths of regular files in the specified directory and its subdirectories, along with their corresponding MD5 checksums.
4. The program will then sort the list based on the entire line (MD5 checksum followed by the file path) using the default "C" locale.
5. Finally, the program calculates the MD5 hash of the entire sorted list and prints the result.

## Output

The program will print the MD5 hash of the combined sorted output, replicating the effect of the original bash command.

## Note

This Java program uses the `java.nio.file` package to traverse the directory and calculate the MD5 hash for each file. It also uses `Collections.sort()` with `Collator.getInstance(Locale.US)` to sort the list based on the entire line with the default "C" locale. The program aims to achieve the same result as the provided bash command using Java code.
```
