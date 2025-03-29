package Business;

public class Config {
    private String portConexionBD;
    private int ipBD;
    private String nomBD;
    private String accesUserBD;
    private String passwordBD;

    public Config( String portConexion, int ip, String nom, String acc, String pwd) {
        portConexionBD = portConexion;
        ipBD = ip;
        nomBD = nom;
        accesUserBD = acc;
        passwordBD = pwd;
    }
    public String getPortConexionBD() {
        return portConexionBD;
    }

    public int getIpBD() {
        return ipBD;
    }

    public String getNomBD() {
        return nomBD;
    }

    public String getAccesUserBD() {
        return accesUserBD;
    }

    public String getPasswordBD() {
        return passwordBD;
    }

    public void printConfig(){
        System.out.println( "Port: " + portConexionBD );
        System.out.println( "IP: " + ipBD );
        System.out.println( "Nom: " + nomBD );
        System.out.println( "Acces: " + accesUserBD );
        System.out.println( "Password: " + passwordBD );
    }
}
