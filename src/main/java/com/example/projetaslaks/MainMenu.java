package com.example.projetaslaks;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static com.example.projetaslaks.Iletisim.AracSelectAll;
import static com.example.projetaslaks.Iletisim.Connect;



public class MainMenu  implements Initializable {
    ArrayList<Arac> listarray= (ArrayList<Arac>) AracSelectAll();
    @FXML
    private Label fiyatLabel;
    @FXML
    private Button kiraButton;
    @FXML
    private Label label;
    @FXML
    private Label hglabel;
    @FXML
    private Label teslabel;
    @FXML
    private AnchorPane root;
    @FXML
    private ListView<String> myListView;
    @FXML
    private DatePicker datePicker1;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private Button teslimButton;
    @FXML
    public void fiyatHesapla(){
        LocalDate from = LocalDate.of(datePicker1.getValue().getYear(),datePicker1.getValue().getMonth(),datePicker1.getValue().getDayOfMonth() );
        LocalDate to = LocalDate.of(datePicker2.getValue().getYear(),datePicker2.getValue().getMonth(),datePicker2.getValue().getDayOfMonth() );
        Period period = Period.between(from, to);
        Integer selected=myListView.getSelectionModel().getSelectedIndex();
        ArrayList<Arac> array= (ArrayList<Arac>) AracSelectAll();
        int num=1000000/array.get(selected).getKm();
        int m=period.getMonths() ;
        int g =period.getDays() ;
        g=m*30+g;
        fiyatLabel.setText(String.valueOf(num*g)+" tl");

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0;i<listarray.size();i++){
            arrayList.add(listarray.get(i).getDurum()+" "+listarray.get(i).getPlaka()+" "+listarray.get(i).getMarka()+" "+
                    listarray.get(i).getModel()+" "+listarray.get(i).getVites()+" "+
                    listarray.get(i).getRenk()+" "+listarray.get(i).getKm());
        }
        myListView.getItems().setAll(arrayList);
        setLabel();
}
    public void setLabel(){
        if(LoginPage.getMusteri().getArac()==null){
            teslimButton.setDisable(true);
        teslabel.setText("");}else{
            kiraButton.setDisable(true);
            teslabel.setText(LoginPage.getMusteri().getArac().getPlaka()+" plakalı aracı:");
        }

        hglabel.setText("MERHABA "+ LoginPage.getMusteri().getIsim());
    }
public void kirala(){
        String sql = "UPDATE araclar SET durum = ? WHERE plaka = ?";
        try (Connection conn = Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            Integer selected=myListView.getSelectionModel().getSelectedIndex();
            ArrayList<Arac> array= (ArrayList<Arac>) AracSelectAll();
            if (array.get(selected).getDurum()==0){
                eslestir(array.get(selected),LoginPage.getMusteri());
                pstmt.setString(2, array.get(selected).getPlaka());
                pstmt.setInt(1,1);
                pstmt.executeUpdate();
                array.get(selected).setDurum(1);
            pstmt.close();
            conn.close();
            label.setText(array.get(selected).getPlaka()+" plakalı araç kiralanmıştır.");

            }
            else{
                label.setText("Araç Müsait Değil.");
            }
        } catch (SQLException  e) {

        }
    }

    public void eslestir(Arac arac,Musteri musteri){
        musteri.setArac(arac);
        String sql = "UPDATE musteri SET arac = ? WHERE telNo = ?";
        try (Connection conn = Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,musteri.getArac().getPlaka());
            pstmt.setString(2, musteri.getTelNo());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {

        }
    }
    public void Donus() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("view.fxml"));
        root.getChildren().setAll(pane);
    }
    public void teslimEt(){
       Musteri musteri= LoginPage.getMusteri();

       if(musteri.getArac()==null){
           label.setText("Teslim edilecek araç bulunamadı.");
       }else{
           String sql1 = "UPDATE araclar SET durum = ? WHERE plaka = ?";
           try (Connection conn = Connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql1)) {

                   pstmt.setInt(1,0);
                   pstmt.setString(2, musteri.getArac().getPlaka());
                    pstmt.executeUpdate();
                   label.setText(musteri.getArac().getPlaka()+" plakalı araç teslim edilmiştir.");
               pstmt.close();
               conn.close();}
               catch (SQLException e) {

               }
           String sql = "UPDATE musteri SET arac = ? WHERE telNo = ?";
           try (Connection conn = Connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               musteri.setArac(null);
               pstmt.setString(2, musteri.getTelNo());
               pstmt.setString(1,null);
               pstmt.executeUpdate();

               pstmt.close();
               conn.close();
           } catch (SQLException e) {

           }

       }
         }
}
