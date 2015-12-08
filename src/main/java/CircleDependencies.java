import java.util.*;

/**
 * Created by Valery on 30.11.2015.
 */
public class CircleDependencies {

    public static List<Integer> rollArray(int position, List<Integer> array) {

        int size = array.size();

        if (array == null || position < 0) {
            return null;
        }

        Integer[] toRoll = new Integer[array.size()];

        for (int i = 0; i < size; i++) {
            toRoll[i] = array.get((position + i) % size);
        }
        return Arrays.asList(toRoll);


    }

    public static List<Integer> searchCircleDependecyThatStartsWithPair(
            Pair currentPair, List<Integer> linkedSequenceOfDependedIds,int firstId, Pair[] pairs) {

        linkedSequenceOfDependedIds.add(currentPair.getId());

        if (currentPair.getId() == currentPair.getDependsOnId()) {
            return null;
        }

        if (currentPair.getDependsOnId() == firstId) {
            return linkedSequenceOfDependedIds;
        }

        for (Pair nextPair : pairs) {

            if (nextPair.getId() == currentPair.getDependsOnId()) {
                Pair[] pairsExeptCurrentPair = createNewPairsExceptThis(pairs, nextPair);
                List<Integer> temp =
                        searchCircleDependecyThatStartsWithPair(nextPair, linkedSequenceOfDependedIds,
                                firstId, pairsExeptCurrentPair);
                if (temp != null) {
                    return temp;
                }
            }
        }

        return null;

    }

    public static Pair[] createNewPairsExceptThis(Pair[] pairs, Pair pair) {
        Set<Pair> currentSet = new HashSet<Pair>(Arrays.asList(pairs));
        currentSet.removeAll(Arrays.asList(pair));
        return (Pair[]) currentSet.toArray(new Pair[0]);
    }

    public static List<List<Integer>> getUniqueCircleDependencies(List<List<Integer>> circleDependencies) {
        List<List<Integer>> uniqueCircleDependencies = new ArrayList<List<Integer>>();
        for (List<Integer> dependencySequence : circleDependencies) {
            if (!uniqueCircleDependencies.contains(dependencySequence)) {

                uniqueCircleDependencies.add(dependencySequence);
            }
        }
        return uniqueCircleDependencies;
    }

    public static void printCircleDependencies(String[] args) {
        Pair[] pairs = InputDependency.getParisOfDependedIds(args);

        if(pairs == null){
            System.out.println("File is not found");
            return;
        }

        List<List<Integer>> allDependecySequences = new ArrayList<List<Integer>>();
        for (Pair p : pairs) {
            List<Integer> list = new ArrayList<Integer>();
            list = searchCircleDependecyThatStartsWithPair(p, list, p.getId(), pairs);
            if (list == null) {
                continue;
            }

            //dependency sequence begins with min id.
            list = rollArray(list.indexOf(Collections.min(list)), list);

            allDependecySequences.add(list);
        }

        List<List<Integer>> uniqueCircleDependencies = getUniqueCircleDependencies(allDependecySequences);

        for (List<Integer> list : uniqueCircleDependencies) {
            for(Integer id : list){
                System.out.print(id + " ");
            }
            System.out.println(list.get(0));
        }

    }
}
