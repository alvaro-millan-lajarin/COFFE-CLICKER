package Presenstation.Controller;

import Business.Entidades.Game;
import Business.Entidades.User;
import Business.ManageGame;
import Business.ManageUser;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Messages;
import Presenstation.View.Scenes.GameScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GameController extends Controller {
    private LoginController loginController;
    private SignUpController signUpController;
    private ManageGame manageGame;


    private Messages messages = new Messages();
    private ManageUser manageUser;


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
            mainController.resetGameManagement();

            mainController.nextScene(Scenes.GAME_MANAGEMENT);
        }else if (e.getActionCommand().equalsIgnoreCase("CafeteraMejora")) {
            cafeteraMejora();

        }else if (e.getActionCommand().equalsIgnoreCase("CafeChetaMejora")) {
            chetaMejora();

        } else if (e.getActionCommand().equalsIgnoreCase("CafeGodMejora")) {
            godMejora();

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
            manageGame.startGeneratorCafetera();
            manageGame.addCafetera("Cafetera");//añades la cantidad de cafetera
            manageGame.updatePriceCoffe("cafetera");

            manageGame.updateGame();
            updateTablas();


        }else{
            notEnoughtCoffe();
        }
    }
    public void compraCafeteraCheta() {
        if(manageGame.enughtCoffeCheta()){
            manageGame.startGeneratorCafeteraCheta();
            manageGame.restarCafe("CafeCheta");
            manageGame.addCafetera("CafeCheta");
            manageGame.updateGame();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }
    public void compraCafeGod(){
        if(manageGame.enughtCoffeGod()){
            manageGame.startGeneratorCafeteraGod();
            manageGame.restarCafe("CafeGod");
            manageGame.addCafetera("CafeGod");
            manageGame.updateGame();
            updateTablas();
        }else{
            notEnoughtCoffe();
        }
    }
    public void updateTablas(){
        ArrayList<Integer> quantitats= manageGame.getQuantitas();
        ArrayList<String> proudccioUnitat = manageGame.getProduccionsUnitat();//falla
        ArrayList<Integer> precioBase = manageGame.getPreciosBase();

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
        }else{
            notEnoughtCoffe();
        }
    }
    public void godMejora() {
        if(manageGame.enoughtCoffeMejoraGod()){
            manageGame.restarCafeMejora("cafeGod");
            manageGame.mejorarGod();
            manageGame.updateGame();
            updateTablas();

        }else{
            notEnoughtCoffe();
        }

    }

    public void deleteCurrentGame() {
        manageGame.deleteGame();
        manageGame.setGame(null);
    }

    public void goToGameManagementScene() {
        mainController.nextScene(Scenes.GAME_MANAGEMENT);
    }

}