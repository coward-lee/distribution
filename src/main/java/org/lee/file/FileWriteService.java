package org.lee.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteService {
    public static void writeFile(String fileName, String writeContent) throws IOException {
        File file = new File(fileName);
        boolean writableFile = false;
        if (!file.exists()){
            writableFile =  file.createNewFile();
        }else if (!file.isDirectory()){
            writableFile = true;
        }
        if (writableFile){
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(writeContent);
            fileWriter.close();
        }
    }
}
