package Persistence;
import Business.Entidades.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase encargada de leer el archivo de configuración JSON
 * y devolver un objeto {@link Config} con los datos necesarios para la conexión a la base de datos.
 */
public class configJsonDAO {

    /**
     * Lee el archivo "data/config.json" y construye un objeto {@link Config} con los datos extraídos.
     *
     * @return Objeto {@link Config} con los datos de conexión.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
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
