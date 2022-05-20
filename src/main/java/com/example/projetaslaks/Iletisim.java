package com.example.projetaslaks;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Iletisim {


    public static Connection Connect()  {
    Connection conn=null;
    try{
    String url="jdbc:sqlite:database.sqlite";
    conn=DriverManager.getConnection(url);
        }
    catch(SQLException e){
        System.out.println("sorun var");
    }
    return conn;

    }
    public static List<Arac> AracSelectAll(){
        List<Arac> aracList=new ArrayList<>();
        String sql ="SELECT * FROM araclar";
        try(Connection conn=Connect();
            Statement stmt=conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql))
        {while (rs.next()){
            Arac arac=new Arac(rs.getInt("durum"), rs.getString("plaka"),rs.getString("vites"),rs.getInt("km"),rs.getString("renk"),rs.getString("marka"),rs.getString("model"));
            aracList.add(arac);
        }
            stmt.close();
            conn.close();
    }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return aracList;
}
    public static List<Musteri> MusteriSelectAll(){
        List<Musteri> musteriList=new ArrayList<>();
        String sql ="SELECT * FROM musteri";
        try(Connection conn=Connect();
            Statement stmt=conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql))
        {while (rs.next()){

            Musteri musteri=new Musteri(rs.getString("isim"), rs.getString("soyisim"),
                    rs.getString("tcNo"), rs.getString("sifre"),
                    rs.getString("telNo"), LoginPage.AracSorgu(rs.getString(5)));
            musteriList.add(musteri);

        }
            stmt.close();
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return musteriList;
    }
}
