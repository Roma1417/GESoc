package utn.dds.tpAnual.db.dto;

public class UserDTO {
    private String token;
    private String username;

    public UserDTO(){

    }

    public UserDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
