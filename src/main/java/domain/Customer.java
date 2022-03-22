package domain;

import java.util.Objects;

public class Customer extends Entity<Integer>{
    private String name;
    private int SceneId;

    public Customer(String name, int sceneId) {
        super(0);
        this.name = name;
        SceneId = sceneId;
    }

    public Customer(Integer id,String name, int sceneId) {
        super(id);
        this.name = name;
        SceneId = sceneId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSceneId() {
        return SceneId;
    }

    public void setSceneId(int sceneId) {
        SceneId = sceneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id == customer.id && SceneId == customer.SceneId && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, SceneId);
    }

    @Override
    public void modify(Entity<Integer> entity) {
        Customer customer = (Customer) entity;
        setName(customer.getName());
        setSceneId(customer.getSceneId());
        setId(customer.getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", SceneId=" + SceneId +
                ", id=" + id +
                '}';
    }
}
