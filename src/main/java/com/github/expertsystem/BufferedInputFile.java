package com.github.expertsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {

    /**
     * Read lines from input file
     *
     * @param filename
     * @return Full text
     * @throws IOException
     */
    public String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuffer sb = new StringBuffer();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }
}
