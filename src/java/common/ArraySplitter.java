/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Sudeera Perera
 */
public class ArraySplitter {
    public static List<String[]> splitArray(String[] largeArray, int chunkSize) {
        List<String[]> smallArrays = new ArrayList<>();
        int length = largeArray.length;
        int startIndex = 0;

        while (startIndex < length) {
            int remainingLength = length - startIndex;
            int currentChunkSize = Math.min(chunkSize, remainingLength);
            String[] smallArray = new String[currentChunkSize];
            System.arraycopy(largeArray, startIndex, smallArray, 0, currentChunkSize);
            smallArrays.add(smallArray);
            startIndex += currentChunkSize;
        }

        return smallArrays;
    }
}
