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
import java.util.ArrayList;

public class GameController extends Controller {
    private LoginController loginController;
    private SignUpController signUpController;
    private ManageGame manageGame;
    private UpdateGame updateGame;

    private Messages messages = new Messages();
    private ManageUser manageUser;

    private UpdateGrafica updateGrafica;

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
    @Override
    public GameScene getView() {
        return (GameScene) super.getView();
    }
    public void finishGame() {
        manageGame.getGame().setFinished();
        manageGame.setFinish();
        mainController.nextScene(Scenes.GAME_MANAGEMENT);
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



            manageGame.updatePriceCoffe("CafeCheta");


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


            manageGame.updatePriceCoffe("CafeGod");


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

   public void setupdateGame(UpdateGame updateGame) {
        this.updateGame = updateGame;
   }
    public int getNumCoffesDisponibles(){
           return manageGame.getGame().getNumCafes();
    }
    public void setUpdateGrafica(UpdateGrafica updateGrafica){
        this.updateGrafica = updateGrafica;
    }
    public void stopThreads(){
        manageGame.stopGenerators();
        updateGrafica.interrupt();
        updateGame.interrupt();
    }
}