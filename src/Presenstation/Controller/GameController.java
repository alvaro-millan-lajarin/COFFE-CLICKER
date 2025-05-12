package Presenstation.Controller;

import Business.Entidades.Game;
import Business.Entidades.Pair;
import Business.Entidades.User;
import Business.ManageGame;
import Business.ManageUser;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Messages;
import Presenstation.View.Grafica.Grafica;
import Presenstation.View.Scenes.GameScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GameController extends Controller {
    private LoginController loginController;
    private SignUpController signUpController;
    private ManageGame manageGame;


    private Messages messages = new Messages();
    private ManageUser manageUser;

    private Timer historicoTimer;
    private LocalDateTime sessionStartTime;


    public GameController(Scene view, MainController mainController, LoginController loginController, SignUpController signUpController, ManageGame manageGame, ManageUser manageUser) {
        super(view, mainController);
        this.loginController = loginController;
        this.signUpController = signUpController;
        this.manageGame = manageGame;

        this.manageUser = manageUser;
    }

    public GameScene getScene() {

        return (GameScene) super.getView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("MORE_COFFE")) {
            manageGame.increaseNumCafes();
            manageGame.updateGame();
            getScene().addCoffe(manageGame.getGame().getNumCafes());
        }else if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
            mainController.resetLogin();
            mainController.nextScene(Scenes.MENU);
        }else if (e.getActionCommand().equalsIgnoreCase("DELETE")) {
            mainController.resetLogin();
            loginController.clearUserData();
            deleteUser();

        }else if (e.getActionCommand().equalsIgnoreCase("Cafetera")) {
            compraCafetera();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeCheta")) {
            compraCafeteraCheta();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeGod")) {
            compraCafeGod();

        }else if(e.getActionCommand().equalsIgnoreCase("GAME_MANAGEMENT")){
            detenerRegistroCafes();
            manageGame.updateGame();
            manageGame.updateGenerators();
            mainController.resetGameManagement();

            mainController.nextScene(Scenes.GAME_MANAGEMENT);
        }else if (e.getActionCommand().equalsIgnoreCase("CafeteraMejora")) {
            cafeteraMejora();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeChetaMejora")) {
            chetaMejora();

        } else if (e.getActionCommand().equalsIgnoreCase("CafeGodMejora")) {
            godMejora();

        }else if (e.getActionCommand().equalsIgnoreCase("FINISHGAME")) {
            int confirm = messages.deleteGame();

            if (confirm == JOptionPane.YES_OPTION) {
                deleteCurrentGame();
            }
        }
    }
    @Override
    public GameScene getView() {
        return (GameScene) super.getView();
    }

    public void deleteUser() {
        int confirm = messages.confirmDelete();

        if (confirm == JOptionPane.YES_OPTION) {
            manageUser.deleteUser();
            messages.deleteUser();
            loginController.clearUserData();
            mainController.nextScene(Scenes.MENU);
        }
    }
    public ManageGame getManageGame() {
        return manageGame;

    }
    public void notEnoughtCoffe() {
        messages.needCoffe();
    }
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
    public void compraCafeteraCheta() {
        if(manageGame.enughtCoffeCheta()){
            manageGame.restarCafe("CafeCheta");
            manageGame.startGeneratorCafeteraCheta();


            //manageGame.addCafetera("CafeCheta");
            manageGame.updatePriceCoffe("CafeCheta");
            //manageGame.updatePriceCoffeChetaBaseDatos("CafeCheta");

            manageGame.updateGame();
            manageGame.updateGenerators();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }
    public void compraCafeGod(){
        if(manageGame.enughtCoffeGod()){
            manageGame.startGeneratorCafeteraGod();
            manageGame.restarCafe("CafeGod");

            //manageGame.addCafetera("CafeGod");
            manageGame.updatePriceCoffe("CafeGod");
            //manageGame.updatePriceCoffeGodBaseDatos("CafeGod");

            manageGame.updateGame();
            manageGame.updateGenerators();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }
    public void updateTablas(){


        ArrayList<Integer> quantitats= manageGame.getQuantitas();
        ArrayList<String> proudccioUnitat = manageGame.getProduccionsUnitat();//falla
        ArrayList<Integer> precioBase = manageGame.getPreciosBase();//

        //tabla millores
        ArrayList<Integer> costMultiplicadores = manageGame.getCostMultplicadors();//falla
        ArrayList<Integer> multiplicadores = manageGame.getMultplicadors();



        getScene().updateTablas(quantitats, proudccioUnitat,precioBase, costMultiplicadores, multiplicadores);
    }


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

    public void deleteCurrentGame() {
        manageGame.deleteGameSelected(manageGame.getGame());
        mainController.resetGameManagement();
        mainController.nextScene(Scenes.GAME_MANAGEMENT);
    }

    public void detenerRegistroCafes() {

        if (historicoTimer != null && historicoTimer.isRunning()) {
            System.out.println("Deteniendo el timer...");
            historicoTimer.stop();
            historicoTimer = null;
        }else {

            System.out.println("Timer ya detenido o nulo");
        }
    }
    public void iniciarRegistroCafes(Game partidaActual, Grafica grafica) {
        System.out.println("Iniciando timer");

        sessionStartTime = LocalDateTime.now();

        historicoTimer = new Timer(60_000, e -> {
            int cafesActuales = partidaActual.getNumCafes();
            LocalDateTime ahora = LocalDateTime.now();


            manageGame.logCafeHistorico(partidaActual.getId(), cafesActuales);


            grafica.getHistorico().add(new Pair<>(ahora, cafesActuales));
            grafica.repaint();
        });

        //grafica.setStartTime(sessionStartTime);
        historicoTimer.start();
    }
    public void updateGameScene() {
        getScene().updateGameScene();
    }
}