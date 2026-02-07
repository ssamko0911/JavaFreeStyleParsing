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
        return this.name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getRoles() {
        return this.roles;
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

        return Objects.equals(this.name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public String toString() {
        if (this.roles.isEmpty()) {
            return this.name;
        }

        return this.name + " (" + String.join(", ", this.roles) + ")";
    }
}
