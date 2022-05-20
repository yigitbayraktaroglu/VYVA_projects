package com.example.projetaslaks;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.projetaslaks.Iletisim.Connect;

public class UyeOl{
    @FXML
    AnchorPane root;
    @FXML
    Label label;
    @FXML
    TextField isimField;
    @FXML
    TextField soyisimField;
    @FXML
    TextField tcField;
    @FXML
    TextField telField;
    @FXML
    TextField sifreField;
    @FXML
    Button button1;
    public void Button() throws IOException {
        insert(tcField.getText(),telField.getText(),isimField.getText(),soyisimField.getText(),sifreField.getText());
        tcField.clear();
        telField.clear();
        isimField.clear();
        soyisimField.clear();
        sifreField.clear();
        label.setText("İşlem Başarılı.");

    }
    public void Donus() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("view.fxml"));
        root.getChildren().setAll(pane);
    }

    public void insert(String tcNo,String telNo,String isim,String soyisim,String sifre) {
        String sql = "INSERT INTO musteri(tcNo,telNo,isim,soyisim,sifre) VALUES(?,?,?,?,?)";
        try (Connection conn=Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tcNo);
            pstmt.setString(2, telNo);
            pstmt.setString(3, isim);
            pstmt.setString(4, soyisim);
            pstmt.setString(5, sifre);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {

        }
    }

}