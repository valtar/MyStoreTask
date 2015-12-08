import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Valery on 03.12.2015.
 */
public class InputDependency {
    private static String[] getLinesFromFile(String filename)  {
        Scanner sc = null;
        List<String> lines = new ArrayList<String>();
        try {
            sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            sc.close();
        }


        return lines.toArray(new String[0]);

    }

    public static Pair[] getParisOfDependedIds(String[] args) {
        if(args == null){
            return null;
        }

        if(args.length == 0){
            return null;
        }

        String[] fileLines = getLinesFromFile(args[0]);

        if (fileLines == null) {
            return null;
        }

        List<Pair> pairs = new ArrayList<Pair>();
        Pair pair = null;
        for (String line : fileLines) {
            pair = getPairIdsFromLine(line);
            if(pair != null){
                pairs.add(pair);
            }
        }

        if(pairs.size() == 0){
            return null;
        }

        return pairs.toArray(new Pair[0]);
    }

    private static Pair getPairIdsFromLine(String line) {
        Scanner sc = new Scanner(line);
        Pair pair = new Pair(null, null);

        if(!sc.hasNextInt()) {
            return null;
        }
        pair.setId(sc.nextInt());

        if(!sc.hasNextInt()) {
            return null;
        }
        pair.setDependsOnId(sc.nextInt());

        return pair;
    }
}
