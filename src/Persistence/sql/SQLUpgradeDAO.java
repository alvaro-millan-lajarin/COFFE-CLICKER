package Persistence.sql;

import Persistence.UpgradeDAO;
import Business.entity.Upgrade;

import java.util.List;

public class SQLUpgradeDAO implements UpgradeDAO {

    @Override
    public void addUpgrade(Upgrade upgrade) {
        String query = "INSERT INTO Mejora(id_generador, nombre, precio, incremento) VALUES ('" +
                upgrade.getIdGenerador() + "', '" +
                upgrade.getNombre() + "', '" +
                upgrade.getPrecio() + "', '" +
                upgrade.getIncremento() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }


    @Override
    public void updateUpgrade(Upgrade upgrade) {
        String query = "UPDATE Mejora SET " +
                "id_generador = '" + upgrade.getIdGenerador() + "', " +
                "nombre = '" + upgrade.getNombre() + "', " +
                "precio = '" + upgrade.getPrecio() + "', " +
                "incremento = '" + upgrade.getIncremento() + "' " +
                "WHERE id_mejora = '" + upgrade.getIdMejora() + "'";

        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void deleteUpgrade(Upgrade upgrade) {
        String query = "DELETE FROM Mejora WHERE id_mejora = '" + upgrade.getIdMejora() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public Upgrade getUpgrade(int id) {
        String query = "SELECT * FROM Mejora WHERE id_mejora = '" + id + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new Upgrade(
                        rs.getInt("id_mejora"),
                        rs.getInt("id_generador"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDouble("incremento")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving upgrade: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Upgrade> getAllUpgrades() {
        String query = "SELECT * FROM Mejora";
        var rs = SQLConnector.getInstance().selectQuery(query);
        List<Upgrade> upgrades = new java.util.ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                Upgrade upgrade = new Upgrade(
                        rs.getInt("id_mejora"),
                        rs.getInt("id_generador"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDouble("incremento")
                );
                upgrades.add(upgrade);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving upgrades: " + e.getMessage());
        }

        return upgrades;
    }
}
