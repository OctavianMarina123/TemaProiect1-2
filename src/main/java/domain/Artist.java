package domain;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Artist extends Entity<Integer>{
    private String name;
    private int sceneId;
    private LocalDateTime date;

    public Artist(Integer id,String name, int sceneId, LocalDateTime date) {
        super(id);
        this.name = name;
        this.sceneId = sceneId;
        this.date = date;
    }

    public Artist(String name, int sceneId, LocalDateTime date) {
        super(0);
        this.name = name;
        this.sceneId = sceneId;
        this.date = date;
    }


    public Integer getID(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return getSceneId() == artist.getSceneId() && getName().equals(artist.getName()) && getDate().equals(artist.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSceneId(), getDate());
    }

    @Override
    public void modify(Entity<Integer> entity) {
        Artist artist = (Artist) entity;
        setName(artist.getName());
        setSceneId(artist.getSceneId());
        setDate(artist.getDate());
        setId(artist.getId());
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", sceneId=" + sceneId +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
