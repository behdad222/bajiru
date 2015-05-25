package app.reyhoon.ir.Object.Response;

import app.reyhoon.ir.Object.Model.User;

public class UsersResponse {
    private User[] users;

    public User[] getUsers () {
        return users;
    }

    public void setUsers (User[] users) {
        this.users = users;
    }
}
