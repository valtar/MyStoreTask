/**
 * Created by Valery on 01.12.2015.
 */
public class Pair {
    private Integer id;
    private Integer dependsOnId;

    public Pair(Integer id, Integer depends){
        this.setId(id);
        this.setDependsOnId(depends);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (!getId().equals(pair.getId())) return false;
        return getDependsOnId().equals(pair.getDependsOnId());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDependsOnId().hashCode();
        return result;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDependsOnId() {
        return dependsOnId;
    }

    public void setDependsOnId(Integer dependsOnId) {
        this.dependsOnId = dependsOnId;
    }
}
