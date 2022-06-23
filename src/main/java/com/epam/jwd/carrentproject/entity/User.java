package com.epam.jwd.carrentproject.entity;

public class User extends AbstractEntity {

    private int userId;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String passportNum;
    private boolean isActive;
    private int userRoleId;

    public static class UserBuilder {
        private User newUser;

        public UserBuilder() {
            newUser = new User();
        }

        public UserBuilder withUserId(int userId) {
            newUser.userId = userId;
            return this;
        }

        public UserBuilder withLogin(String login) {
            newUser.login = login;
            return this;
        }

        public UserBuilder withPassword(String password) {
            newUser.password = password;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }


        public UserBuilder withLastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public UserBuilder withPassport(String passportNum) {
            newUser.passportNum = passportNum;
            return this;
        }

        public UserBuilder withIsActive(boolean isActive) {
            newUser.isActive = isActive;
            return this;
        }

        public UserBuilder withUserRole(int userRoleId) {
            newUser.userRoleId = userRoleId;
            return this;
        }

        public User build() {
            return newUser;
        }

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (isActive != user.isActive) return false;
        if (userRoleId != user.userRoleId) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return passportNum != null ? passportNum.equals(user.passportNum) : user.passportNum == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passportNum != null ? passportNum.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + userRoleId;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", passportNum='" + passportNum + '\'' +
                ", isActive=" + isActive +
                ", userRoleId=" + userRoleId +
                '}';
    }
}
