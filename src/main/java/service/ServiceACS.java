package service;

import domain.Artist;
import domain.Customer;
import domain.Scene;
import exceptions.RepoException;
import repository.RepoArtists;
import repository.RepoCustomer;
import repository.RepoScene;
import domain.Artist;
import domain.Customer;
import exceptions.RepoException;

import repository.RepoArtists;
import repository.RepoCustomer;
import repository.RepoScene;

import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

/**
 * Acs(ARTISTS-CUSTOMER-SCENES)
 **/
public class ServiceACS {
    private Properties dbUtils;
    private RepoCustomer repoCustomer;
    private RepoScene repoScene;
    private RepoArtists repoArtists;
    public ServiceACS(Properties dbUtils) {
        this.dbUtils = dbUtils;
        repoCustomer=new RepoCustomer(dbUtils);
        repoArtists=new RepoArtists(dbUtils);
        repoScene=new RepoScene(dbUtils);
    }

    public int customersSize(){
        return repoCustomer.size();
    }
    public int scenesSize(){
        return repoScene.size();
    }
    public int artistsSize(){
        return repoArtists.size();
    }

    public void customerAdd(Customer customer) throws Exception {
        repoCustomer.add(customer);
    }

    public void sceneAdd(Scene scene) throws Exception {
        repoScene.add(scene);
    }

    public void artistAdd(Artist artist) throws Exception {
        repoArtists.add(artist);
    }

    public Customer customerDelete(int i) throws RepoException {
        return repoCustomer.delete(i);
    }

    public Scene sceneDelete(int i) throws RepoException {
        return repoScene.delete(i);
    }

    public Artist artistDelete(int i) throws RepoException {
        return repoArtists.delete(i);
    }

    public void customerUpdate(Customer customer) throws Exception {
        repoCustomer.update(customer);
    }

    public void sceneUpdate(Scene scene) throws Exception {
        repoScene.update(scene);
    }

    public void artistUpdate(Artist artist) throws Exception {
        repoArtists.update(artist);
    }

    public Customer customerFindById(int id) throws RepoException {
        return repoCustomer.findById(id);
    }

    public Scene sceneFindById(int id) throws RepoException {
        return repoScene.findById(id);
    }

    public Artist artistFindById(int id) throws RepoException {
        return repoArtists.findById(id);
    }

    public Collection<Customer> findAllCustomers(){
        return repoCustomer.findAll();
    }
    public Collection<Scene> findAllScenes(){
        return repoScene.findAll();
    }
    public Collection<Artist> findAllArtists(){
        return repoArtists.findAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceACS)) return false;
        ServiceACS that = (ServiceACS) o;
        return Objects.equals(dbUtils, that.dbUtils) && Objects.equals(repoCustomer, that.repoCustomer) && Objects.equals(repoScene, that.repoScene) && Objects.equals(repoArtists, that.repoArtists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbUtils, repoCustomer, repoScene, repoArtists);
    }

    @Override
    public String toString() {
        return "ServiceACS{" +
                "dbUtils=" + dbUtils +
                ", repoCustomer=" + repoCustomer +
                ", repoScene=" + repoScene +
                ", repoArtists=" + repoArtists +
                '}';
    }
}
