package DTO;

import java.util.Objects;

public class DTOClient {
    private int id;
    private String name;
    private String lastName;
    private int membership;

    public DTOClient(){}

    public DTOClient(int id){
        this.id = id;
    }

    public DTOClient(String name, String lastName, int membership){
        this.name = name;
        this.lastName = lastName;
        this.membership = membership;
    }

    public DTOClient(int id, String name, String lastName, int membership){
        this(name, lastName, membership);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", apellido='" + lastName + '\'' +
                ", membresia=" + membership +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTOClient DTOClient = (DTOClient) o;
        return id == DTOClient.id && membership == DTOClient.membership && Objects.equals(name, DTOClient.name) && Objects.equals(lastName, DTOClient.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, membership);
    }
}











