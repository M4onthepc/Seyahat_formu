package com.example.mehmetakifersoy211130012;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> cmbKalkis;

    @FXML
    private ComboBox<String> cmbTuru;

    @FXML
    private ComboBox<String > cmbVaris;

    @FXML
    private ComboBox<String> cmbYolcu;

    @FXML
    private DatePicker dtpTarih;

    @FXML
    private Spinner<Integer> spnrAdet;

    @FXML
    private TextField txtAdSoyad;

    @FXML
    private TextField txtAdet;

    @FXML
    private TextField txtBiletNo;

    @FXML
    private TextField txtBiletNo2;

    @FXML
    private TextField txtKalkis;

    @FXML
    private TextField txtTarih;

    @FXML
    private TextField txtTuru;

    @FXML
    private TextField txtVaris;

    private ArrayList<Seyahat> musteriler=new ArrayList<Seyahat>();

    @FXML
    void kalkisGoster(ActionEvent event) {

    }

    @FXML
    void sehayatKayit(ActionEvent event) {

        String adSoyad=txtAdSoyad.getText();
        String kalkis=cmbKalkis.getValue();
        String varis=cmbVaris.getValue();
        String otobusTuru=cmbTuru.getValue();
        Integer rezervasyonAdet=spnrAdet.getValue();

        if (txtBiletNo.getText().isEmpty() || txtAdSoyad.getText().isEmpty()){
            Alert musterimesaji=new Alert(Alert.AlertType.ERROR);
            musterimesaji.setTitle("Hata");
            musterimesaji.setHeaderText("Ad Soyad veya bilet numarası girilmedi ! ");
            musterimesaji.show();
            return;
        }
        int biletNo=Integer.parseInt(txtBiletNo.getText());

        for (int i = 0; i < musteriler.size() ; i++) {
            if (biletNo==musteriler.get(i).getBiletNo()){
                Alert hatamesaji=new Alert(Alert.AlertType.ERROR);
                hatamesaji.setTitle("Hata");
                hatamesaji.setHeaderText(musteriler.get(i).getBiletNo()+" numaralı müşteri kayıtlı ! ");
                hatamesaji.show();
                return;
            }

        }

        LocalDate seyahatTarih=dtpTarih.getValue();

        Seyahat musteri=new Seyahat(adSoyad,kalkis,varis,otobusTuru,biletNo,rezervasyonAdet,seyahatTarih);
        musteriler.add(musteri);

        try {
            FileOutputStream fos=new FileOutputStream("bilgiler.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(musteriler);
            fos.close();
            oos.close();

            Alert bilgilendirmemesaji=new Alert(Alert.AlertType.CONFIRMATION);
            bilgilendirmemesaji.setTitle("Bilgi");
            bilgilendirmemesaji.setHeaderText(musteri.getAdSoyad()+" kişisi kaydedildi");
            bilgilendirmemesaji.show();

            cmbYolcu.getItems().add(musteri.getAdSoyad());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void seyahatSil(ActionEvent event) {

        int secilen=cmbYolcu.getSelectionModel().getSelectedIndex();

        if (secilen<0){
            Alert silmesaji=new Alert(Alert.AlertType.ERROR);
            silmesaji.setTitle("Hata");
            silmesaji.setHeaderText("Silinecek kayıt bulunamadı !");
            silmesaji.show();
            return;
        }
        String adsoyad=musteriler.get(secilen).getAdSoyad();
        cmbYolcu.getItems().remove(secilen);
        txtBiletNo2.clear();
        txtKalkis.clear();
        txtVaris.clear();
        txtAdet.clear();
        txtTarih.clear();
        txtTuru.clear();

        musteriler.remove(secilen);

        try {
            FileOutputStream fos=new FileOutputStream("bilgiler.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(musteriler);
            fos.close();
            oos.close();
            Alert bilmesaji=new Alert(Alert.AlertType.INFORMATION);
            bilmesaji.setTitle("Bilgi");
            bilmesaji.setHeaderText(adsoyad + " kişisi silindi .");
            bilmesaji.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void turuSecim(ActionEvent event) {

    }

    @FXML
    void varisGoster(ActionEvent event) {

    }

    @FXML
    void yolcuGoster(ActionEvent event) {
        int secilen=cmbYolcu.getSelectionModel().getSelectedIndex();

        if (secilen<0)
            return;

        txtBiletNo2.setText(String.valueOf(musteriler.get(secilen).getBiletNo()));
        txtKalkis.setText(musteriler.get(secilen).getKalkis());
        txtVaris.setText(musteriler.get(secilen).getVaris());
        txtAdet.setText(String.valueOf(musteriler.get(secilen).getRezervasyonAdet()));
        txtTarih.setText(String.valueOf(musteriler.get(secilen).getSeyahatTarih()));
        txtTuru.setText(musteriler.get(secilen).getOtobusTuru());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbKalkis.setItems(FXCollections.observableArrayList("İstanbul","Trabzon","Ankara","Erzurum","Eskişehir","Aydın","Bursa"));
        cmbVaris.setItems(FXCollections.observableArrayList("Manisa","Bitlis","İzmir","Van","Balıkesir","Ordu","Yozgat"));
        spnrAdet.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,35,0,1));
        cmbTuru.setItems(FXCollections.observableArrayList("Tourismo","Travego","Setra","Man","Neoplan"));

        try {
            FileInputStream fis=new FileInputStream("bilgiler.dat");
            ObjectInputStream ois=new ObjectInputStream(fis);
            musteriler=(ArrayList<Seyahat>) ois.readObject();
            ois.close();

            for (int i = 0; i <musteriler.size() ; i++) {
                cmbYolcu.getItems().add(musteriler.get(i).getAdSoyad());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
