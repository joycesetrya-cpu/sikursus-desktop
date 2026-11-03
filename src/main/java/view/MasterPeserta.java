
package view;

import dao.PesertaDAO;
import model.Peserta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MasterPeserta extends JFrame {

    // ==============================
    // KOMPONEN FORM
    // ==============================

    private JTextField txtNim;
    private JTextField txtNama;
    private JComboBox<String> cmbJenisKelamin;
    private JComboBox<String> cmbProdi;
    private JTextField txtNoHp;
    private JTextField txtEmail;
    private JTextField txtCari;

    private JButton btnSimpan;
    private JButton btnUbah;
    private JButton btnHapus;
    private JButton btnReset;
    private JButton btnCetakSemua;
    private JButton btnCari;

    private JTable tblPeserta;
    private DefaultTableModel model;

    private PesertaDAO dao = new PesertaDAO();

    private int selectedId = -1;

    // ==============================
    // WARNA TEMA UNGU
    // ==============================

    private final Color unguTua =
            new Color(75, 40, 109);

    private final Color unguUtama =
            new Color(112, 64, 160);

    private final Color unguMuda =
            new Color(243, 237, 249);

    private final Color lavender =
            new Color(220, 205, 238);

    private final Color putih =
            Color.WHITE;

    private final Color teks =
            new Color(55, 40, 70);

    // ==============================
    // CONSTRUCTOR
    // ==============================

    public MasterPeserta() {

        setTitle("SIKURSUS Desktop - Master Data Peserta");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1250, 800);

        setMinimumSize(new Dimension(950, 650));

        setLocationRelativeTo(null);

        initComponents();

        configureTable();

        loadTable();

        clearForm();
    }

    // ==============================
    // MEMBUAT TAMPILAN
    // ==============================

    private void initComponents() {

        JPanel utama = new JPanel(new BorderLayout(12, 12));

        utama.setBackground(unguMuda);

        utama.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 18, 15, 18
                )
        );

        // HEADER

        JPanel header = new JPanel(new GridLayout(2, 1));

        header.setBackground(unguTua);

        header.setPreferredSize(
                new Dimension(1000, 90)
        );

        JLabel judul = new JLabel(
                "SISTEM INFORMASI KURSUS (SIKURSUS)",
                SwingConstants.CENTER
        );

        judul.setForeground(putih);

        judul.setFont(
                new Font("Arial", Font.BOLD, 23)
        );

        JLabel subJudul = new JLabel(
                "HALAMAN PENGELOLAAN DATA PESERTA KURSUS",
                SwingConstants.CENTER
        );

        subJudul.setForeground(lavender);

        subJudul.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        header.add(judul);
        header.add(subJudul);

        // FORM PESERTA

        JPanel panelForm = new JPanel(
                new GridBagLayout()
        );

        panelForm.setBackground(putih);

        panelForm.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(lavender),
                        "FORMULIR PENGELOLAAN DATA PESERTA",
                        0,
                        0,
                        new Font("Arial", Font.BOLD, 14),
                        unguTua
                )
        );

        txtNim = new JTextField();
        txtNama = new JTextField();

        cmbJenisKelamin = new JComboBox<>(
                new String[]{
                    "L", "P"
                }
        );

        cmbProdi = new JComboBox<>(
                new String[]{
                    "Informatika",
                    "Sistem Informasi",
                    "Pendidikan Teknik Informatika"
                }
        );

        txtNoHp = new JTextField();
        txtEmail = new JTextField();

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(7, 10, 7, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        tambahBaris(
                panelForm, gbc, 0,
                "Nomor Induk Mahasiswa (NIM)",
                txtNim
        );

        tambahBaris(
                panelForm, gbc, 1,
                "Nama Lengkap Peserta",
                txtNama
        );

        tambahBaris(
                panelForm, gbc, 2,
                "Jenis Kelamin",
                cmbJenisKelamin
        );

        tambahBaris(
                panelForm, gbc, 3,
                "Program Studi",
                cmbProdi
        );

        tambahBaris(
                panelForm, gbc, 4,
                "Nomor HP / WhatsApp",
                txtNoHp
        );

        tambahBaris(
                panelForm, gbc, 5,
                "Alamat Email",
                txtEmail
        );

        // TOMBOL FORM

        JPanel panelTombol = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER, 10, 10
                )
        );

        panelTombol.setBackground(putih);

        btnSimpan = new JButton("Simpan Data");
        btnUbah = new JButton("Perbarui Data");
        btnHapus = new JButton("Hapus Data");
        btnReset = new JButton("Kosongkan Form");
        btnCetakSemua = new JButton("Cetak Data");

        aturTombol(btnSimpan, unguTua);
        aturTombol(btnUbah, unguUtama);
        aturTombol(btnHapus, new Color(190, 75, 105));
        aturTombol(btnReset, new Color(130, 105, 155));
        aturTombol(btnCetakSemua, new Color(95, 125, 145));

        panelTombol.add(btnSimpan);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnReset);
        panelTombol.add(btnCetakSemua);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 1;

        panelForm.add(panelTombol, gbc);

        // PANEL DATA PESERTA

        JPanel panelData = new JPanel(
                new BorderLayout(8, 10)
        );

        panelData.setBackground(putih);

        panelData.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(lavender),
                        "DAFTAR DATA PESERTA KURSUS",
                        0,
                        0,
                        new Font("Arial", Font.BOLD, 14),
                        unguTua
                )
        );

        // PENCARIAN

        JPanel panelCari = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 10)
        );

        panelCari.setBackground(putih);

        JLabel lblCari = new JLabel(
                "Cari berdasarkan NIM atau Nama Peserta:"
        );

        lblCari.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        lblCari.setForeground(teks);

        txtCari = new JTextField(25);

        btnCari = new JButton("Cari Data");

        aturTombol(btnCari, unguUtama);

        panelCari.add(lblCari);
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // TABEL PESERTA

        tblPeserta = new JTable();

        tblPeserta.setRowHeight(30);

        tblPeserta.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        tblPeserta.setForeground(teks);

        tblPeserta.setBackground(putih);

        tblPeserta.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tblPeserta.setSelectionBackground(lavender);

        tblPeserta.setSelectionForeground(unguTua);

        tblPeserta.setGridColor(
                new Color(225, 215, 235)
        );

        tblPeserta.setShowGrid(true);

        JTableHeader tableHeader =
                tblPeserta.getTableHeader();

        tableHeader.setBackground(unguTua);

        tableHeader.setForeground(putih);

        tableHeader.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        tableHeader.setPreferredSize(
                new Dimension(100, 35)
        );

        JScrollPane scroll = new JScrollPane(tblPeserta);

        scroll.getViewport().setBackground(putih);

        panelData.add(panelCari, BorderLayout.NORTH);

        panelData.add(scroll, BorderLayout.CENTER);

        // PANEL TENGAH

        JPanel isi = new JPanel(
                new BorderLayout(12, 12)
        );

        isi.setBackground(unguMuda);

        isi.add(panelForm, BorderLayout.NORTH);

        isi.add(panelData, BorderLayout.CENTER);

        utama.add(header, BorderLayout.NORTH);

        utama.add(isi, BorderLayout.CENTER);

        setContentPane(utama);

        // ==============================
        // EVENT TOMBOL
        // ==============================

        btnSimpan.addActionListener(
                e -> simpanData()
        );

        btnUbah.addActionListener(
                e -> ubahData()
        );

        btnHapus.addActionListener(
                e -> hapusData()
        );

        btnReset.addActionListener(
                e -> resetData()
        );

        btnCari.addActionListener(
                e -> cariData()
        );

        btnCetakSemua.addActionListener(
                e -> cetakData()
        );

        txtCari.addActionListener(
                e -> cariData()
        );

        // EVENT KLIK TABEL

        tblPeserta.addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                pilihData();
            }
        });
    }

    // ==============================
    // MENAMBAHKAN BARIS FORM
    // ==============================

    private void tambahBaris(
            JPanel panel,
            GridBagConstraints gbc,
            int baris,
            String label,
            JComponent komponen) {

        gbc.gridy = baris;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.30;

        JLabel lbl = new JLabel(label);

        lbl.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        lbl.setForeground(unguTua);

        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.70;

        komponen.setPreferredSize(
                new Dimension(400, 34)
        );

        panel.add(komponen, gbc);
    }

    // ==============================
    // MENGATUR TOMBOL
    // ==============================

    private void aturTombol(
            JButton tombol,
            Color warna) {

        tombol.setBackground(warna);

        tombol.setForeground(putih);

        tombol.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        tombol.setFocusPainted(false);

        tombol.setBorderPainted(false);

        tombol.setOpaque(true);

        tombol.setPreferredSize(
                new Dimension(145, 38)
        );
    }

    // ==============================
    // MODEL TABEL
    // ==============================

    private void configureTable() {

        model = new DefaultTableModel(
                new Object[]{
                    "ID",
                    "NIM",
                    "Nama Lengkap",
                    "Jenis Kelamin",
                    "Program Studi",
                    "Nomor HP",
                    "Alamat Email"
                }, 0
        ) {

            @Override
            public boolean isCellEditable(
                    int row, int column) {

                return false;
            }
        };

        tblPeserta.setModel(model);
    }

    // ==============================
    // MENAMPILKAN DATA
    // ==============================

    private void loadTable() {

        model.setRowCount(0);

        List<Peserta> data = dao.findAll();

        for (Peserta p : data) {

            model.addRow(new Object[]{
                p.getId(),
                p.getNim(),
                p.getNama(),
                p.getJenisKelamin(),
                p.getProdi(),
                p.getNoHp(),
                p.getEmail()
            });
        }
    }

    // ==============================
    // MENGAMBIL DATA FORM
    // ==============================

    private Peserta ambilDataForm() {

        Peserta p = new Peserta();

        p.setNim(txtNim.getText().trim());

        p.setNama(txtNama.getText().trim());

        p.setJenisKelamin(
                cmbJenisKelamin.getSelectedItem().toString()
        );

        p.setProdi(
                cmbProdi.getSelectedItem().toString()
        );

        p.setNoHp(txtNoHp.getText().trim());

        p.setEmail(txtEmail.getText().trim());

        return p;
    }

    // ==============================
    // VALIDASI FORM
    // ==============================

    private boolean validasiForm() {

        if (txtNim.getText().trim().isEmpty()
                || txtNama.getText().trim().isEmpty()
                || txtNoHp.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Mohon lengkapi seluruh data peserta terlebih dahulu!"
            );

            return false;
        }

        if (!txtEmail.getText().trim().contains("@")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Alamat email belum valid!"
            );

            return false;
        }

        return true;
    }

    // ==============================
    // SIMPAN DATA
    // ==============================

    private void simpanData() {

        if (!validasiForm()) {
            return;
        }

        Peserta p = ambilDataForm();

        if (dao.insert(p)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Data peserta berhasil disimpan!"
            );

            loadTable();

            clearForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Data gagal disimpan. Periksa koneksi database atau data NIM."
            );
        }
    }

    // ==============================
    // MEMILIH DATA TABEL
    // ==============================

    private void pilihData() {

        int row = tblPeserta.getSelectedRow();

        if (row == -1) {
            return;
        }

        selectedId = Integer.parseInt(
                model.getValueAt(row, 0).toString()
        );

        txtNim.setText(
                model.getValueAt(row, 1).toString()
        );

        txtNama.setText(
                model.getValueAt(row, 2).toString()
        );

        cmbJenisKelamin.setSelectedItem(
                model.getValueAt(row, 3).toString()
        );

        cmbProdi.setSelectedItem(
                model.getValueAt(row, 4).toString()
        );

        txtNoHp.setText(
                model.getValueAt(row, 5).toString()
        );

        txtEmail.setText(
                model.getValueAt(row, 6).toString()
        );

        btnSimpan.setEnabled(false);

        btnUbah.setEnabled(true);

        btnHapus.setEnabled(true);
    }

    // ==============================
    // UBAH DATA
    // ==============================

    private void ubahData() {

        if (selectedId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih data peserta pada tabel terlebih dahulu!"
            );

            return;
        }

        if (!validasiForm()) {
            return;
        }

        Peserta p = ambilDataForm();

        p.setId(selectedId);

        if (dao.update(p)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Data peserta berhasil diperbarui!"
            );

            loadTable();

            clearForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Data peserta gagal diperbarui!"
            );
        }
    }

    // ==============================
    // HAPUS DATA
    // ==============================

    private void hapusData() {

        if (selectedId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih data peserta yang ingin dihapus!"
            );

            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Apakah kamu yakin ingin menghapus data peserta ini?",
                "Konfirmasi Penghapusan Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {

            if (dao.delete(selectedId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Data peserta berhasil dihapus!"
                );

                loadTable();

                clearForm();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Data peserta gagal dihapus!"
                );
            }
        }
    }

    // ==============================
    // PENCARIAN DATA
    // ==============================

    private void cariData() {

        String keyword = txtCari.getText().trim();

        model.setRowCount(0);

        List<Peserta> data = dao.search(keyword);

        for (Peserta p : data) {

            model.addRow(new Object[]{
                p.getId(),
                p.getNim(),
                p.getNama(),
                p.getJenisKelamin(),
                p.getProdi(),
                p.getNoHp(),
                p.getEmail()
            });
        }

        clearForm();
    }

    // ==============================
    // RESET DAN REFRESH
    // ==============================

    private void resetData() {

        txtCari.setText("");

        clearForm();

        loadTable();
    }

    private void clearForm() {

        selectedId = -1;

        txtNim.setText("");
        txtNama.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");

        cmbJenisKelamin.setSelectedIndex(0);
        cmbProdi.setSelectedIndex(0);

        tblPeserta.clearSelection();

        btnSimpan.setEnabled(true);

        btnUbah.setEnabled(false);

        btnHapus.setEnabled(false);
    }

    // ==============================
    // CETAK DATA
    // ==============================

    private void cetakData() {

        try {

            boolean selesai = tblPeserta.print(
                    JTable.PrintMode.FIT_WIDTH,

                    new java.text.MessageFormat(
                            "DAFTAR DATA PESERTA SIKURSUS"
                    ),

                    new java.text.MessageFormat(
                            "Halaman {0}"
                    )
            );

            if (selesai) {

                JOptionPane.showMessageDialog(
                        this,
                        "Proses pencetakan data berhasil!"
                );
            }

        } catch (java.awt.print.PrinterException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Gagal mencetak data: " + e.getMessage()
            );
        }
    }

    // ==============================
    // MAIN PROGRAM
    // ==============================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

            @Override
            public void run() {

                try {

                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName()
                    );

                } catch (Exception e) {

                    e.printStackTrace();
                }

                new MasterPeserta().setVisible(true);
            }
        });
    }
}
