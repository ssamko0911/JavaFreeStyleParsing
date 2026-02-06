package entities;

import java.util.List;
import java.util.Objects;

public class Author {
    private String name;
    private List<String> roles;

    public Author(String name,  List<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Author setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;

        return Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        if (roles.isEmpty()) {
            return name;
        }

        return name + " (" + String.join(", ", roles) + ")";
    }
}
