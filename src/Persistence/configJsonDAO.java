package Persistence;
import Business.Entidades.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class configJsonDAO {

public Config getConfigDAO() throws IOException {
    String[] config = new String[4];
    int ip = 0;
    BufferedReader br = new BufferedReader(new FileReader("data/config.json"));

    br.readLine();
    br.readLine();
    String line = br.readLine();
    String[] obj = line.split(":");
    config[0] = obj[1].replaceAll("[\\s\",]", "");
    line = br.readLine();
    obj = line.split(":");
    ip = Integer.parseInt(obj[1].replaceAll("[\\s\",]", ""));
    for (int i = 1; i < 4; i++) {
        line = br.readLine();
        obj = line.split(":");
        config[i] = obj[1].replaceAll("[\\s\",]", "");
    }
    

    return new Config(config[0],ip,config[1],config[2],config[3]);
}
}
