
package view;

import dao.PesertaDAO;
import model.Peserta;

import javax.swing.*;
import java.awt.*;

public class FormPeserta extends JFrame {

    private JTextField txtNim;
    private JTextField txtNama;
    private JComboBox<String> cmbJenisKelamin;
    private JComboBox<String> cmbProdi;
    private JTextField txtNoHp;
    private JTextField txtEmail;

    private JButton btnSimpan;
    private JButton btnReset;
    private JButton btnKembali;

    private PesertaDAO dao;

    public FormPeserta() {

        dao = new PesertaDAO();

        setTitle("Form Pendaftaran Peserta - SIKURSUS Desktop");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        buatTampilan();
    }

    private void buatTampilan() {

        JPanel panelUtama = new JPanel(new BorderLayout());
        panelUtama.setBackground(new Color(245, 240, 250));

        // HEADER
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(35, 75, 180));
        panelHeader.setPreferredSize(new Dimension(650, 75));

        JLabel lblJudul = new JLabel("FORM PENDAFTARAN PESERTA");

        lblJudul.setForeground(Color.WHITE);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 21));

        panelHeader.add(lblJudul);

        // FORM
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);

        panelForm.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        txtNim = new JTextField(25);
        txtNama = new JTextField(25);

        cmbJenisKelamin = new JComboBox<>(
                new String[]{"L", "P"}
        );

        cmbProdi = new JComboBox<>(
                new String[]{
                    "Informatika",
                    "Sistem Informasi",
                    "Pendidikan Teknik Informatika"
                }
        );

        txtNoHp = new JTextField(25);
        txtEmail = new JTextField(25);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(9, 8, 9, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tambahBaris(panelForm, gbc, 0, "NIM", txtNim);
        tambahBaris(panelForm, gbc, 1, "Nama Peserta", txtNama);
        tambahBaris(panelForm, gbc, 2, "Jenis Kelamin", cmbJenisKelamin);
        tambahBaris(panelForm, gbc, 3, "Program Studi", cmbProdi);
        tambahBaris(panelForm, gbc, 4, "Nomor HP", txtNoHp);
        tambahBaris(panelForm, gbc, 5, "Email", txtEmail);

        // TOMBOL
        JPanel panelTombol = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 15)
        );

        panelTombol.setBackground(Color.WHITE);

        btnSimpan = new JButton("Simpan");
        btnReset = new JButton("Reset");
        btnKembali = new JButton("Kembali");

        aturTombol(btnSimpan, new Color(25, 150, 100));
        aturTombol(btnReset, new Color(100, 120, 150));
        aturTombol(btnKembali, new Color(35, 75, 180));

        panelTombol.add(btnSimpan);
        panelTombol.add(btnReset);
        panelTombol.add(btnKembali);

        JPanel panelIsi = new JPanel(new BorderLayout());
        panelIsi.setBackground(Color.WHITE);

        panelIsi.add(panelForm, BorderLayout.CENTER);
        panelIsi.add(panelTombol, BorderLayout.SOUTH);

        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(panelIsi, BorderLayout.CENTER);

        setContentPane(panelUtama);

        // EVENT TOMBOL
        btnSimpan.addActionListener(e -> simpanData());
        btnReset.addActionListener(e -> resetForm());
        btnKembali.addActionListener(e -> dispose());
    }

    private void tambahBaris(
            JPanel panel,
            GridBagConstraints gbc,
            int baris,
            String label,
            JComponent komponen) {

        gbc.gridy = baris;
        gbc.gridx = 0;
        gbc.weightx = 0.3;

        JLabel lbl = new JLabel(label);

        lbl.setFont(new Font("Arial", Font.BOLD, 13));

        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;

        komponen.setPreferredSize(
                new Dimension(300, 32)
        );

        panel.add(komponen, gbc);
    }

    private void aturTombol(JButton tombol, Color warna) {

        tombol.setBackground(warna);
        tombol.setForeground(Color.WHITE);

        tombol.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        tombol.setFocusPainted(false);

        tombol.setPreferredSize(
                new Dimension(110, 38)
        );
    }

    // VALIDASI FORM
    private boolean validasiForm() {

        if (txtNim.getText().trim().isEmpty()
                || txtNama.getText().trim().isEmpty()
                || txtNoHp.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Semua data harus diisi!"
            );

            return false;
        }

        if (!txtEmail.getText().trim().contains("@")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Format email tidak valid!"
            );

            return false;
        }

        return true;
    }

    // SIMPAN DATA PESERTA
    private void simpanData() {

        if (!validasiForm()) {
            return;
        }

        Peserta peserta = new Peserta();

        peserta.setNim(txtNim.getText().trim());
        peserta.setNama(txtNama.getText().trim());

        peserta.setJenisKelamin(
                cmbJenisKelamin.getSelectedItem().toString()
        );

        peserta.setProdi(
                cmbProdi.getSelectedItem().toString()
        );

        peserta.setNoHp(txtNoHp.getText().trim());
        peserta.setEmail(txtEmail.getText().trim());

        boolean berhasil = dao.insert(peserta);

        if (berhasil) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pendaftaran peserta berhasil disimpan!"
            );

            resetForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Data gagal disimpan. Periksa koneksi database!"
            );
        }
    }

    // RESET FORM
    private void resetForm() {

        txtNim.setText("");
        txtNama.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");

        cmbJenisKelamin.setSelectedIndex(0);
        cmbProdi.setSelectedIndex(0);

        txtNim.requestFocus();
    }

    // MAIN PROGRAM
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new FormPeserta().setVisible(true);

            }
        });
    }
}
