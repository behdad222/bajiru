package app.bajiru.ir.object.Response;

import app.bajiru.ir.object.Model.User;

public class UsersResponse {
    private User[] users;

    public User[] getUsers () {
        return users;
    }

    public void setUsers (User[] users) {
        this.users = users;
    }
}
