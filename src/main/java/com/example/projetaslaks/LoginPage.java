package com.example.projetaslaks;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginPage extends Iletisim{
    private static Musteri musteri;

        @FXML
        AnchorPane root;
        @FXML
        Button loginButton;
        @FXML
        Button SigninButton;
        @FXML
        TextField userIDField;
        @FXML
        PasswordField userPasswordField;
        @FXML
        Label feedback;

        public  void GirisYap() throws IOException {
            String userID = userIDField.getText();
            String password = userPasswordField.getText();
            String x="admin";
            if ( x.equals(userID) & x.equals(password)){
                AnchorPane pane =FXMLLoader.load(getClass().getResource("admin.fxml"));
                root.getChildren().setAll(pane);
            }else{
            String sql = "SELECT sifre FROM musteri WHERE telNo = ?";
            try (Connection conn = this.Connect();
                 PreparedStatement pstmt  = conn.prepareStatement(sql)){
                pstmt.setString(1,userID);
                ResultSet rs  = pstmt.executeQuery();
                    if(Objects.equals(rs.getString("sifre"), password)){
                        online(userID);
                        AnaMenu();
                        feedback.setText("");
                    }else{
                        feedback.setText("Sifre Hatalı.");
                        userPasswordField.clear();
                    }
            } catch (SQLException | IOException e) {

                feedback.setText("Hatalı giris.");
                userIDField.clear();
                userPasswordField.clear();
            }
}

        }
    @FXML
    private void AnaMenu() throws IOException {
        AnchorPane pane =FXMLLoader.load(getClass().getResource("main.fxml"));
        root.getChildren().setAll(pane);
    }
    @FXML
    private void UyeOl() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("UyeOl.fxml"));
        root.getChildren().setAll(pane);
    }
    public void online(String telNo){
        String sql1 = "SELECT * FROM musteri WHERE telNo= ?";
        try (Connection conn1 = Connect();
             PreparedStatement pstmt1  = conn1.prepareStatement(sql1)){
            // set the value
            pstmt1.setString(1, telNo);
            ResultSet rs1  = pstmt1.executeQuery();
            String plaka=rs1.getString("arac");
            Musteri musteri2=new Musteri(rs1.getString("isim"),
                    rs1.getString("soyisim"),
                    rs1.getString("tcNo"),
                    rs1.getString("sifre"),
                    rs1.getString("telNo"),
                    AracSorgu(plaka));

            setMusteri(musteri2);
        } catch (SQLException e) {

        }

    }
    public static Musteri getMusteri(){
        return musteri;
}

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;

    }
    public static Arac AracSorgu(String plaka){
            String sql1 = "SELECT * FROM araclar WHERE plaka= ?";
        try (Connection conn1 = Connect();
             PreparedStatement pstmt1  = conn1.prepareStatement(sql1)){
            // set the value
            pstmt1.setString(1, plaka);
            ResultSet rs1  = pstmt1.executeQuery();
            Arac arac=new Arac(rs1.getInt("durum"),
                    rs1.getString("plaka"),
                    rs1.getString("vites"),
                    rs1.getInt("km"),
                    rs1.getString("renk"),
                    rs1.getString("marka"),
                    rs1.getString("model"));
            System.out.println(rs1.getString("plaka"));
            pstmt1.close();
            conn1.close();
            return arac;
        } catch (SQLException e) {

        }
    return null;}
}
