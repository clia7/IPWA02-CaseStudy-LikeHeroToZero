package herotozero.app;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Auth implements Serializable {
    private String username;
    private String password;
    private boolean loggedIn = false;

    public String login() {
        if ("scientist".equals(username) && "secret".equals(password)) {
            loggedIn = true;
            return "dashboard.xhtml?faces-redirect=true";
        } else {
            loggedIn = false;
            return "login.xhtml?faces-redirect=true&error=true";
        }
    }

    public String logout() {
        loggedIn = false;
        username = null;
        password = null;
        return "login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}