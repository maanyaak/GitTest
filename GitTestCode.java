import java.io.*;
import java.util.concurrent.*;
public class GitTestCode{
	public static void main(String[] args) {

	        ExecutorService executor = Executors.newFixedThreadPool(2);

	        Callable<Long> frankensteinTask = () -> processFile("Frankenstein.txt");
	        Callable<Long> romeoJulietTask =    () -> processFile("RomeoJuliet.txt");

	        long totalStart = System.currentTimeMillis();

	        try {
	            Future<Long> frankensteinTime = executor.submit(frankensteinTask);
	            Future<Long> romeoJulietTime = executor.submit(romeoJulietTask);

	            System.out.println("Frankenstein time: " + frankensteinTime.get() + " ms");
	            System.out.println("Romeo & Juliet time: " + romeoJulietTime.get() + " ms");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        executor.shutdown();

	        long totalEnd = System.currentTimeMillis();
	        System.out.println("Total elapsed time: " + (totalEnd - totalStart) + " ms");
	    }

	    private static long processFile(String filename) {
	        long start = System.currentTimeMillis();

	        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	            int ch;
	            while ((ch = br.read()) != -1) {
	                if (!isVowel((char) ch)) {
	                    // do nothing — we just simulate the processing work
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        long end = System.currentTimeMillis();
	        return end - start;
	    }

	    private static boolean isVowel(char c) {
	        return "aeiouAEIOU".indexOf(c) != -1;
    }
}