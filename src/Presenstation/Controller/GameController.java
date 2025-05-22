package Presenstation.Controller;

import Business.Entidades.Game;
import Business.ManageGame;
import Business.ManageGameGenerators;
import Business.ManageUser;
import Presenstation.Messages;
import Business.Refresh.UpdateGame;
import Business.Refresh.UpdateGrafica;
import Presenstation.View.Scenes.GameScene;
import Presenstation.View.Scenes.Scenes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controlador principal del juego. Gestiona las acciones del usuario en la vista del juego,
 * como la compra y mejora de cafeteras, logout, eliminación de usuario y navegación de escenas.
 */
public class GameController implements ActionListener {


    private final ManageGameGenerators manageGameGenerators;
    private UpdateGame updateGame;
    private final Messages messages = new Messages();
    private final ManageUser manageUser;
    private UpdateGrafica updateGrafica;
    private final GameScene gameScene;
    private final MainController mainController;
    private final ManageGame manageGame;


    /**
     * Constructor del GameController.
     *
     * @param view Vista del juego (GameScene).
     * @param mainController Controlador principal de la aplicación.
     * @param manageGameGenerators Objeto de lógica de negocio para el juego.
     * @param manageUser Objeto de lógica de negocio para usuarios.
     */
    public GameController(GameScene view, MainController mainController, ManageGameGenerators manageGameGenerators, ManageUser manageUser, ManageGame manageGame) {

        this.manageGameGenerators = manageGameGenerators;

        this.manageUser = manageUser;
        this.gameScene = view;
        this.mainController = mainController;
        this.manageGame = manageGame;
    }

    /**
     * Devuelve la escena actual del juego.
     *
     * @return Escena del juego.
     */
    public GameScene getScene() {
        return gameScene;
    }

    /**
     * Maneja los eventos de acción de la interfaz.
     *
     * @param e Evento de acción.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("MORE_COFFE")) {

            manageGame.increaseNumCafes();
            manageGame.updateGame();


        }else if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
            mainController.resetLogin();
            stopThreads();

            mainController.nextScene(Scenes.MENU);
        }else if (e.getActionCommand().equalsIgnoreCase("DELETE")) {
            mainController.resetLogin();
            stopThreads();
            deleteUser();

        }else if (e.getActionCommand().equalsIgnoreCase("Cafetera")) {

            compraCafetera();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeCheta")) {
            compraCafeteraCheta();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeGod")) {
            compraCafeGod();

        }else if(e.getActionCommand().equalsIgnoreCase("GAME_MANAGEMENT")){

            manageGameGenerators.updateGame();
            manageGameGenerators.updateGenerators();
            mainController.resetGameManagement();
            stopThreads();
            mainController.nextScene(Scenes.GAME_MANAGEMENT);
        }else if (e.getActionCommand().equalsIgnoreCase("CafeteraMejora")) {
            int confirm = messages.confirmMejora();

            if (confirm == JOptionPane.YES_OPTION) {
                cafeteraMejora();
            }

        }else if (e.getActionCommand().equalsIgnoreCase("CafeChetaMejora")) {
            int confirm = messages.confirmMejora();

            if (confirm == JOptionPane.YES_OPTION) {
                chetaMejora();
            }


        } else if (e.getActionCommand().equalsIgnoreCase("CafeGodMejora")) {
            int confirm = messages.confirmMejora();

            if (confirm == JOptionPane.YES_OPTION) {
                godMejora();
            }


        }else if (e.getActionCommand().equalsIgnoreCase("FINISHGAME")) {
            manageGameGenerators.updateGame();
            manageGameGenerators.updateGenerators();
            mainController.resetGameManagement();
            stopThreads();
            finishGame();

        }
    }

    /**
     * Marca la partida como finalizada y cambia a la escena de gestión de partidas.
     */
    public void finishGame() {
        manageGame.getGame().setFinished();
        manageGame.setFinish();
        mainController.nextScene(Scenes.GAME_MANAGEMENT);
    }

    /**
     * Elimina el usuario actual previa confirmación del usuario.
     */
    public void deleteUser() {
        int confirm = messages.confirmDelete();

        if (confirm == JOptionPane.YES_OPTION) {
            manageUser.deleteUser();
            messages.deleteUser();

            mainController.nextScene(Scenes.MENU);
        }
    }

    /**
     * Muestra un mensaje indicando que no hay suficiente café.
     */
    public void notEnoughtCoffe() {
        messages.needCoffe();
    }

    /**
     * Compra una cafetera si hay suficiente café disponible.
     */
    public void compraCafetera() {
        if(manageGameGenerators.enughtCoffeCafeteria()){

            manageGame.restarCafe("cafetera");//restar cafe
            manageGameGenerators.startGeneratorCafetera();//encender generador
            manageGameGenerators.updatePriceCoffe("cafetera");
            manageGameGenerators.updateGame();
            manageGameGenerators.updateGenerators();

            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }

    /**
     * Compra una cafetera Cheta si hay suficiente café disponible.
     */
    public void compraCafeteraCheta() {
        if(manageGameGenerators.enughtCoffeCheta()){
            manageGame.restarCafe("CafeCheta");
            manageGameGenerators.startGeneratorCafeteraCheta();
            manageGameGenerators.updatePriceCoffe("CafeCheta");
            manageGameGenerators.updateGame();
            manageGameGenerators.updateGenerators();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }

    /**
     * Compra una cafetera God si hay suficiente café disponible.
     */
    public void compraCafeGod(){
        if(manageGameGenerators.enughtCoffeGod()){
            manageGameGenerators.startGeneratorCafeteraGod();
            manageGame.restarCafe("CafeGod");
            manageGameGenerators.updatePriceCoffe("CafeGod");
            manageGameGenerators.updateGame();
            manageGameGenerators.updateGenerators();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }

    /**
     * Cambia a la escena de gestión del juego, actualizando primero el estado del juego.
     */
    public void updateTablas(){
        ArrayList<Integer> quantitats= manageGameGenerators.getQuantitas();
        ArrayList<String> proudccioUnitat = manageGameGenerators.getProduccionsUnitat();//falla
        ArrayList<Integer> precioBase = manageGameGenerators.getPreciosBase();//

        //tabla millores
        ArrayList<Integer> costMultiplicadores = manageGameGenerators.getCostMultplicadors();//falla
        ArrayList<Integer> multiplicadores = manageGameGenerators.getMultplicadors();

        getScene().updateTablas(quantitats, proudccioUnitat,precioBase, costMultiplicadores, multiplicadores);
    }

    /**
     * Mejora las cafeteras normales si hay suficiente café.
     */
    public void cafeteraMejora() {
        if(manageGameGenerators.enoughtCoffeMejoraCafetera()){
            manageGame.restarCafeMejora("cafetera");
            manageGameGenerators.mejorarCafetera();
            manageGameGenerators.updateGame();
            manageGameGenerators.updateGenerators();
            updateTablas();
        }else{
            messages.needCoffeOrGenerator();
        }

    }

    /**
     * Mejora las cafeteras Cheta si hay suficiente café.
     */
    public void chetaMejora() {
        if(manageGameGenerators.enoughtCoffeMejoraCheta()){
            manageGame.restarCafeMejora("CafeCheta");
            manageGameGenerators.mejorarCheta();
            manageGameGenerators.updateGame();
            updateTablas();
            manageGameGenerators.updateGenerators();
        }else{
            messages.needCoffeOrGenerator();
        }
    }

    /**
     * Mejora las cafeteras God si hay suficiente café.
     */
    public void godMejora() {
        if(manageGameGenerators.enoughtCoffeMejoraGod()){
            manageGame.restarCafeMejora("cafeGod");
            manageGameGenerators.mejorarGod();
            manageGameGenerators.updateGame();
            manageGameGenerators.updateGenerators();
            updateTablas();

        }else{
            messages.needCoffeOrGenerator();
        }

    }

    /**
     * Establece el hilo de actualización del juego.
     *
     * @param updateGame Instancia de UpdateGame.
     */
    public void setupdateGame(UpdateGame updateGame) {
        this.updateGame = updateGame;
   }
    /**
     * Devuelve el número de cafés disponibles en la partida.
     *
     * @return Número de cafés actuales.
     */
    public int getNumCoffesDisponibles(){
           return manageGameGenerators.getGame().getNumCafes();
    }

    /**
     * Establece el hilo de actualización de la gráfica.
     *
     * @param updateGrafica Instancia de UpdateGrafica.
     */
    public void setUpdateGrafica(UpdateGrafica updateGrafica){
        this.updateGrafica = updateGrafica;
    }

    /**
     * Detiene todos los hilos activos: generadores, actualización de escena y gráfica.
     */
    public void stopThreads(){
        manageGameGenerators.stopGenerators();
        updateGrafica.interrupt();
        updateGame.interrupt();
    }
}