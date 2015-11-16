package by.bsuir.group172301.matskevich.tour.entity;

/**
 * Base entity
 */
public abstract class Entity {

    /**
     * entity id
     */
    private int id;

    /**
     * get id
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * set id
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

}
