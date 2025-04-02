package Persistence;

import Business.entity.Upgrade;
import java.util.List;

public interface UpgradeDAO {

    void addUpgrade(Upgrade upgrade);
    void updateUpgrade(Upgrade upgrade);
    void deleteUpgrade(Upgrade upgrade);
    Upgrade getUpgrade(int id);
    List<Upgrade> getAllUpgrades();
}
