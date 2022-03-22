package domain;

import java.util.Objects;

public class Scene extends Entity<Integer>{
    private String name;
    private int capacity;

    public Scene(String name, int capacity) {
        super(0);
        this.name = name;
        this.capacity = capacity;
    }
    public Scene(Integer id,String name, int capacity) {
        super(id);
        this.name = name;
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scene)) return false;
        Scene scene = (Scene) o;
        return getCapacity() == scene.getCapacity() && getID() == scene.getID() && Objects.equals(getName(), scene.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCapacity(), getID());
    }

    @Override
    public void modify(Entity<Integer> entity) {
        Scene scene = (Scene) entity;
        setName(scene.getName());
        setCapacity(scene.getCapacity());
        setID(scene.getId());
    }

    @Override
    public String toString() {
        return "Scene{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
