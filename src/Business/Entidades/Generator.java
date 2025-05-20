package Business.Entidades;

/**
 * Clase que representa un generador automático de café en una partida.
 * Hereda de Thread y produce café periódicamente.
 */
public class Generator extends Thread {
    private int id =0;
    private int idGame;
    private String nombre;
    private double precio;
    private Double cafeSeg;//cantidad_cafe/tiempo
    private Game game;
    private double tiempoGeneracion;
    private double incrementCost;
    private Integer multiplicador;
    private Integer costMultiplicador;

    /**
     * Constructor de la clase Generator.
     *
     * @param id Identificador único del generador.
     * @param nombre Nombre del generador.
     * @param precio Precio inicial del generador.
     * @param cafeSeg Cantidad de café producido por segundo.
     * @param tiempoGeneracion Intervalo de tiempo entre cada generación de café (en segundos).
     * @param incrementCost Incremento del coste al comprar otro generador igual.
     * @param costMultiplicador Coste de mejora del multiplicador.
     * @param mutiplicador Valor inicial del multiplicador de producción.
     * @param idGame ID de la partida a la que pertenece este generador.
     */
    public Generator(int id, String nombre, double precio, Double cafeSeg, double tiempoGeneracion, double incrementCost,Integer costMultiplicador, Integer mutiplicador,int idGame) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cafeSeg = cafeSeg;

        this.tiempoGeneracion = tiempoGeneracion;
        this.incrementCost = incrementCost;
        this.multiplicador = mutiplicador;
        this.costMultiplicador = costMultiplicador;
        this.idGame = idGame;

    }

    /**
     * Método principal del hilo. Ejecuta la lógica de producción de café periódicamente
     * y la añade al total de la partida asociada.
     */
    @Override
    public void run() {
        double acumulador = 0.0;

        while (!Thread.currentThread().isInterrupted()) {
            acumulador = acumulador + (cafeSeg * tiempoGeneracion);

            int cafesEnteros = (int) acumulador;

            if (cafesEnteros >= 1) {
                game.addNumCafes(cafesEnteros);
                acumulador -= cafesEnteros;
            }

            try {
                Thread.sleep((long) (tiempoGeneracion * 1000)); // de segundos a ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Devuelve el ID del generador.
     *
     * @return ID del generador.
     */
    public long getId() {

        return id;
    }

    /**
     * Devuelve el nombre del generador.
     *
     * @return Nombre del generador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio del generador.
     *
     * @return Precio actual.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Devuelve la cantidad de café generado por segundo.
     *
     * @return Café por segundo.
     */
    public Double getCafeSeg() {
        return cafeSeg;
    }

    /**
     * Establece el precio del generador.
     *
     * @param precio Nuevo precio.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Establece la cantidad de café generado por segundo.
     *
     * @param cafeSeg Nueva cantidad de café por segundo.
     */
    public void setCafeSeg(Double cafeSeg) {
        this.cafeSeg = cafeSeg;
    }

    /**
     * Devuelve el valor del incremento de coste al comprar más unidades del generador.
     *
     * @return Valor del incremento de coste.
     */
    public double getIncrementCost() {
        return incrementCost;
    }

    /**
     * Devuelve el intervalo de tiempo entre cada generación de café.
     *
     * @return Tiempo de generación en segundos.
     */
    public double getTiempoGeneracion() {
        return tiempoGeneracion;
    }

    /**
     * Establece el valor del multiplicador de producción.
     *
     * @param multiplicador Nuevo valor del multiplicador.
     */
    public void setMultiplicador(Integer multiplicador) {
        this.multiplicador = multiplicador;
    }

    /**
     * Devuelve el valor actual del multiplicador de producción.
     *
     * @return Multiplicador actual.
     */
    public Integer getMultiplicador() {
        return multiplicador;
    }

    /**
     * Devuelve el coste de mejora del multiplicador.
     *
     * @return Coste del multiplicador.
     */
    public Integer getCostMultiplicador() {
        return costMultiplicador;
    }

    /**
     * Establece el coste de mejora del multiplicador.
     *
     * @param costMultiplicador Nuevo coste.
     */
    public void setCostMultiplicador(Integer costMultiplicador) {
        this.costMultiplicador = costMultiplicador;
    }

    /**
     * Devuelve el ID de la partida a la que pertenece el generador.
     *
     * @return ID de la partida.
     */
    public int getIdGame() {
        return idGame;
    }

    /**
     * Asocia el generador a una instancia de partida.
     *
     * @param game Objeto Game al que pertenece este generador.
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
