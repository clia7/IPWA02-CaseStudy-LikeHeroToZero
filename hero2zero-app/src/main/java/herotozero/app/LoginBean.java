package herotozero.app;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private boolean loggedIn = false;

    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "admin123");
        users.put("user", "user123");
    }

    public String login() {
        String expectedPassword = users.get(username);

        if (expectedPassword != null && expectedPassword.equals(password)) {
            loggedIn = true;
            return "dashboard.xhtml?faces-redirect=true";
        } else {
            jakarta.faces.context.FacesContext.getCurrentInstance().addMessage(null,
                    new jakarta.faces.application.FacesMessage(jakarta.faces.application.FacesMessage.SEVERITY_ERROR,
                            "Login fehlgeschlagen", "Benutzername oder Passwort ist falsch."));
            return null; 
        }
    }

    public String logout() {
        loggedIn = false;
        username = null;
        password = null;
        jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    // Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
