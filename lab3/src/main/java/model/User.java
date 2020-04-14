package model;

import model.enums.Authority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class User {
    private static final String NAME_REGEX = "^[A-Z][a-z]{2,14}$";
    private static final String LOGIN_REGEX ="^[a-zA-Z][a-zA-Z0-9\\-\\_\\.]{1,18}[a-zA-Z0-9]$";

    private UUID id;

    @Pattern(regexp = NAME_REGEX, message = "First name must match \"^[A-Z][a-z]{2,14}$\" ")
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @Pattern(regexp = NAME_REGEX, message = "Last name must match \"^[A-Z][a-z]{2,14}$\" ")
    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @Pattern(regexp = LOGIN_REGEX, message = "Login must match \"^[a-zA-Z][a-zA-Z0-9\\-\\_\\.]{1,18}[a-zA-Z0-9]$\" ")
    @NotNull(message = "Login cannot be null")
    private String login;

    @NotNull(message = "Password cannot be null")
    private String password;

    private Authority authority;

    private boolean isActive;

    private Set<Order> orders = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Authority getAuthority() {
        return authority;
    }

    public User setAuthority(Authority authority) {
        this.authority = authority;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public User setActive(boolean active) {
        isActive = active;
        return this;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public User setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authority=" + authority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
