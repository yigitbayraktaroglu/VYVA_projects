package com.example.projetaslaks;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.projetaslaks.Iletisim.Connect;

public class Admin {
    @FXML
    AnchorPane root;
    @FXML
    Label label;
    @FXML
    TextField plakaField;
    @FXML
    TextField markaField;
    @FXML
    TextField modelField;
    @FXML
    TextField kmField;
    @FXML
    TextField vitesField;
    @FXML
    TextField renkField;
    @FXML
    TextField aracSilField;


    public void Button() throws IOException {
        insert(plakaField.getText(), vitesField.getText(), renkField.getText(), markaField.getText(),modelField.getText(),Integer.parseInt(kmField.getText()) );
        modelField.clear();
        vitesField.clear();
        plakaField.clear();
        kmField.clear();
        renkField.clear();
        markaField.clear();
        label.setText("İşlem Başarılı.");

    }
    public void aracSil(){
        delete();
        aracSilField.clear();
        label.setText("Araç silindi.");
    }
    @FXML
    public void Donus() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("view.fxml"));
        root.getChildren().setAll(pane);
    }
    public void insert(String plaka,String vites,String renk,String marka,String model,Integer km) {
        String sql = "INSERT INTO araclar( plaka, vites, km, renk, marka, model,durum) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn=Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plaka);
            pstmt.setString(2, vites);
            pstmt.setInt(3, km);
            pstmt.setString(4, renk);
            pstmt.setString(5, marka);
            pstmt.setString(6, model);
            pstmt.setInt(7, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void delete() {
        String sql = "DELETE FROM araclar WHERE plaka = ?";

        try (Connection conn = Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1,aracSilField.getText() );
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
