package com.GUI;

//Jusni johan
//22116069

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
    private JPanel panelMain;
    private JList JlistMahasiswa;
    private JButton filterButton;
    private JTextField textFieldFilter;
    private JTextField textFieldNama;
    private JTextField textFieldNim;
    private JTextField textFieldIpk;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;

    private List<Mahasiswa> arraydata = new ArrayList<>();

    private DefaultListModel defaultListModel = new DefaultListModel<>();

    class Mahasiswa{
        private String nama;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public Float getIpk() {
            return ipk;
        }

        public void setIpk(Float ipk) {
            this.ipk = ipk;
        }

        private String nim;
        private Float ipk;
    }

    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nama = textFieldNama.getText();
                String nim = textFieldNim.getText();
                Float ipk = Float.valueOf(textFieldIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();

                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                arraydata.add(mahasiswa);

                setClearButton();
                fromMahasiswaTOListModel();
            }
        });
        JlistMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = JlistMahasiswa.getSelectedIndex();

                if (index < 0 )
                    return;

                String nama = JlistMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().equals(nama)){
                        Mahasiswa mahasiswa = arraydata.get(i);
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                        break;
                    }
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClearButton();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = JlistMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = JlistMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().equals(nama)){
                        arraydata.remove(i);
                        break;
                    }
                }

                setClearButton();
                fromMahasiswaTOListModel();
            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyWord = textFieldFilter.getText();

                List<String> filtered = new ArrayList<>();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().contains(keyWord)){
                        filtered.add(arraydata.get(i).getNama());
                    }
                }
                refreshListModel(filtered);
            }
        });
    }

    private void fromMahasiswaTOListModel(){

        List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arraydata.size(); i++) {
            listNamaMahasiswa.add(arraydata.get(i).getNama());
        }
        refreshListModel(listNamaMahasiswa);
    }

    private void setClearButton(){
        textFieldNama.setText("");
        textFieldNim.setText("");
        textFieldIpk.setText("");
    }

    private void refreshListModel(List<String> list){
        defaultListModel.clear();
        defaultListModel.addAll(list);
        JlistMahasiswa.setModel(defaultListModel);
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
