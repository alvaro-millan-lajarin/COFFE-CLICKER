package Presenstation.Controller;

import Business.ManageGame;
import Business.ManageUser;
import Presenstation.Messages;
import Business.Refresh.UpdateGame;
import Business.Refresh.UpdateGrafica;
import Presenstation.View.Scenes.GameScene;
import Presenstation.View.Scenes.Scene;
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
    private LoginController loginController;
    private SignUpController signUpController;
    private ManageGame manageGame;
    private UpdateGame updateGame;
    private Messages messages = new Messages();
    private ManageUser manageUser;
    private UpdateGrafica updateGrafica;
    private GameScene gameScene;
    private MainController mainController;


    /**
     * Constructor del GameController.
     *
     * @param view Vista del juego (GameScene).
     * @param mainController Controlador principal de la aplicación.
     * @param loginController Controlador de inicio de sesión.
     * @param signUpController Controlador de registro.
     * @param manageGame Objeto de lógica de negocio para el juego.
     * @param manageUser Objeto de lógica de negocio para usuarios.
     */
    public GameController(GameScene view, MainController mainController, LoginController loginController, SignUpController signUpController, ManageGame manageGame, ManageUser manageUser) {

        this.loginController = loginController;
        this.signUpController = signUpController;
        this.manageGame = manageGame;

        this.manageUser = manageUser;
        this.gameScene = view;
        this.mainController = mainController;
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
            loginController.clearUserData();
            deleteUser();

        }else if (e.getActionCommand().equalsIgnoreCase("Cafetera")) {
            compraCafetera();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeCheta")) {
            compraCafeteraCheta();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeGod")) {
            compraCafeGod();

        }else if(e.getActionCommand().equalsIgnoreCase("GAME_MANAGEMENT")){

            manageGame.updateGame();
            manageGame.updateGenerators();
            mainController.resetGameManagement();
            stopThreads();
            mainController.nextScene(Scenes.GAME_MANAGEMENT);
        }else if (e.getActionCommand().equalsIgnoreCase("CafeteraMejora")) {
            cafeteraMejora();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeChetaMejora")) {
            chetaMejora();

        } else if (e.getActionCommand().equalsIgnoreCase("CafeGodMejora")) {
            godMejora();

        }else if (e.getActionCommand().equalsIgnoreCase("FINISHGAME")) {
            manageGame.updateGame();
            manageGame.updateGenerators();
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
            loginController.clearUserData();
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
        if(manageGame.enughtCoffeCafeteria()){
            manageGame.restarCafe("cafetera");//restar cafe
            manageGame.startGeneratorCafetera();//encender generador
            manageGame.updatePriceCoffe("cafetera");
            manageGame.updateGame();
            manageGame.updateGenerators();

            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }

    /**
     * Compra una cafetera Cheta si hay suficiente café disponible.
     */
    public void compraCafeteraCheta() {
        if(manageGame.enughtCoffeCheta()){
            manageGame.restarCafe("CafeCheta");
            manageGame.startGeneratorCafeteraCheta();
            manageGame.updatePriceCoffe("CafeCheta");
            manageGame.updateGame();
            manageGame.updateGenerators();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }

    /**
     * Compra una cafetera God si hay suficiente café disponible.
     */
    public void compraCafeGod(){
        if(manageGame.enughtCoffeGod()){
            manageGame.startGeneratorCafeteraGod();
            manageGame.restarCafe("CafeGod");
            manageGame.updatePriceCoffe("CafeGod");
            manageGame.updateGame();
            manageGame.updateGenerators();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }

    /**
     * Cambia a la escena de gestión del juego, actualizando primero el estado del juego.
     */
    public void updateTablas(){
        ArrayList<Integer> quantitats= manageGame.getQuantitas();
        ArrayList<String> proudccioUnitat = manageGame.getProduccionsUnitat();//falla
        ArrayList<Integer> precioBase = manageGame.getPreciosBase();//

        //tabla millores
        ArrayList<Integer> costMultiplicadores = manageGame.getCostMultplicadors();//falla
        ArrayList<Integer> multiplicadores = manageGame.getMultplicadors();

        getScene().updateTablas(quantitats, proudccioUnitat,precioBase, costMultiplicadores, multiplicadores);
    }

    /**
     * Mejora las cafeteras normales si hay suficiente café.
     */
    public void cafeteraMejora() {
        if(manageGame.enoughtCoffeMejoraCafetera()){
            manageGame.restarCafeMejora("cafetera");
            manageGame.mejorarCafetera();
            manageGame.updateGame();
            manageGame.updateGenerators();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }

    }

    /**
     * Mejora las cafeteras Cheta si hay suficiente café.
     */
    public void chetaMejora() {
        if(manageGame.enoughtCoffeMejoraCheta()){
            manageGame.restarCafeMejora("CafeCheta");
            manageGame.mejorarCheta();
            manageGame.updateGame();
            updateTablas();
            manageGame.updateGenerators();
        }else{
            notEnoughtCoffe();
        }
    }

    /**
     * Mejora las cafeteras God si hay suficiente café.
     */
    public void godMejora() {
        if(manageGame.enoughtCoffeMejoraGod()){
            manageGame.restarCafeMejora("cafeGod");
            manageGame.mejorarGod();
            manageGame.updateGame();
            manageGame.updateGenerators();
            updateTablas();

        }else{
            notEnoughtCoffe();
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
           return manageGame.getGame().getNumCafes();
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
        manageGame.stopGenerators();
        updateGrafica.interrupt();
        updateGame.interrupt();
    }
}