package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import model.Peserta;
import dao.PesertaDAO;

public class FormPeserta extends JFrame {

    // Kombinasi Warna Biru (Kontras & Tulisan Jelas)
    private final Color warnaLatar = new Color(227, 242, 253);     // Biru Muda Lembut
    private final Color warnaHeader = new Color(21, 101, 192);     // Biru Tua
    private final Color warnaSimpan = new Color(21, 101, 192);     // Biru Tua Kontras untuk Tombol Simpan
    private final Color warnaReset = new Color(255, 255, 255);     // Putih (Tombol Reset)

    private JPanel panelUtama;
    private JPanel panelHeader;
    private JPanel panelForm;
    private JPanel panelJenis;
    private JPanel panelFasilitas;
    private JPanel panelTombol;

    private JLabel lblJudul, lblNim, lblNama, lblJenisKelamin, lblProdi, lblNoHp, lblEmail;
    private JLabel lblKodeAkses, lblKursus, lblFasilitas, lblRingkasan;

    private JTextField txtNim;
    private JTextField txtNama;
    private JRadioButton rbLaki;
    private JRadioButton rbPerempuan;
    private ButtonGroup buttonGroupJenisKelamin;
    private JTextField txtProdi;
    private JTextField txtNoHp;
    private JTextField txtEmail;

    private JPasswordField txtKodeAkses;
    private JComboBox<String> cmbKursus;
    private JCheckBox cbModul;
    private JCheckBox cbSertifikat;
    private JCheckBox cbRekaman;
    private JTextArea txtRingkasan;
    private JScrollPane scrollRingkasan;

    private JButton btnSimpan;
    private JButton btnReset;

    public FormPeserta() {
        initComponents();

        buttonGroupJenisKelamin.add(rbLaki);
        buttonGroupJenisKelamin.add(rbPerempuan);

        txtRingkasan.setEditable(false);
        getRootPane().setDefaultButton(btnSimpan);

        // Ukuran jendela besar dan proporsional
        setMinimumSize(new Dimension(600, 450));
        setSize(new Dimension(850, 680));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelUtama = new JPanel(new BorderLayout());
        panelHeader = new JPanel(new BorderLayout());
        panelForm = new JPanel(new GridBagLayout());
        panelJenis = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        panelFasilitas = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 0));

        // Teks Judul disesuaikan menjadi Form Data Peserta Kursus
        lblJudul = new JLabel("Form Data Peserta Kursus", SwingConstants.CENTER);
        lblNim = new JLabel("Nim");
        lblNama = new JLabel("Nama Peserta");
        lblJenisKelamin = new JLabel("Jenis Kelamin");
        lblProdi = new JLabel("Program Studi");
        lblNoHp = new JLabel("Nomor Hp");
        lblEmail = new JLabel("Email");
        lblKodeAkses = new JLabel("Kode Akses");
        lblKursus = new JLabel("Kursus");
        lblFasilitas = new JLabel("Fasilitas");
        lblRingkasan = new JLabel("Ringkasan");

        txtNim = new JTextField();
        txtNama = new JTextField();
        rbLaki = new JRadioButton("Laki-Laki");
        rbPerempuan = new JRadioButton("Perempuan");
        buttonGroupJenisKelamin = new ButtonGroup();
        txtProdi = new JTextField();
        txtNoHp = new JTextField();
        txtEmail = new JTextField();

        txtKodeAkses = new JPasswordField();
        cmbKursus = new JComboBox<>(new String[] {
            "-Pilih Kursus-",
            "Java Desktop",
            "Data Science",
            "Web Programming",
            "UI/UX Design"
        });

        cbModul = new JCheckBox("Modul Cetak");
        cbSertifikat = new JCheckBox("Sertifikat");
        cbRekaman = new JCheckBox("Akses Rekaman");

        txtRingkasan = new JTextArea(8, 20);
        scrollRingkasan = new JScrollPane(txtRingkasan);

        btnSimpan = new JButton("Simpan ke Database");
        btnReset = new JButton("RESET");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop - Form Data Peserta");

        // Mewarnai Latar Belakang Komponen
        panelUtama.setBackground(warnaLatar);
        panelHeader.setBackground(warnaHeader);
        panelForm.setBackground(warnaLatar);
        panelJenis.setBackground(warnaLatar);
        panelFasilitas.setBackground(warnaLatar);
        panelTombol.setBackground(warnaLatar);

        // Header Style
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setPreferredSize(new Dimension(100, 70));
        panelHeader.add(lblJudul, BorderLayout.CENTER);

        // Label Font Style
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Color warnaTeksLabel = new Color(13, 71, 161); // Biru gelap untuk teks samping

        JLabel[] allLabels = {lblNim, lblNama, lblJenisKelamin, lblProdi, lblNoHp, lblEmail, lblKodeAkses, lblKursus, lblFasilitas, lblRingkasan};
        for (JLabel lbl : allLabels) {
            lbl.setFont(fontLabel);
            lbl.setForeground(warnaTeksLabel);
        }

        rbLaki.setBackground(warnaLatar);
        rbPerempuan.setBackground(warnaLatar);
        cbModul.setBackground(warnaLatar);
        cbSertifikat.setBackground(warnaLatar);
        cbRekaman.setBackground(warnaLatar);

        panelJenis.add(rbLaki);
        panelJenis.add(rbPerempuan);

        panelFasilitas.add(cbModul);
        panelFasilitas.add(cbSertifikat);
        panelFasilitas.add(cbRekaman);

        // Tombol Simpan ke Database Style (Biru tua + teks putih kontras)
        btnSimpan.setBackground(warnaSimpan);
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSimpan.setFocusPainted(false);
        btnSimpan.setPreferredSize(new Dimension(170, 36));
        btnSimpan.setBorder(BorderFactory.createEmptyBorder());
        btnSimpan.addActionListener(evt -> btnSimpanActionPerformed());

        // Tombol RESET Style
        btnReset.setBackground(warnaReset);
        btnReset.setForeground(Color.BLACK);
        btnReset.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(130, 36));
        btnReset.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnReset.addActionListener(evt -> btnResetActionPerformed());

        panelTombol.add(btnSimpan);
        panelTombol.add(btnReset);

        // Mengatur Dimensi Input Field
        Dimension dimField = new Dimension(540, 34);
        txtNim.setPreferredSize(dimField);
        txtNama.setPreferredSize(dimField);
        txtProdi.setPreferredSize(dimField);
        txtNoHp.setPreferredSize(dimField);
        txtEmail.setPreferredSize(dimField);
        txtKodeAkses.setPreferredSize(dimField);
        cmbKursus.setPreferredSize(dimField);

        scrollRingkasan.setPreferredSize(new Dimension(540, 180));

        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        // Menyusun secara berurutan ke bawah menggunakan GridBagLayout
        addRow(0, lblNim, txtNim);
        addRow(1, lblNama, txtNama);
        addRow(2, lblJenisKelamin, panelJenis);
        addRow(3, lblProdi, txtProdi);
        addRow(4, lblNoHp, txtNoHp);
        addRow(5, lblEmail, txtEmail);
        addRow(6, lblKodeAkses, txtKodeAkses);
        addRow(7, lblKursus, cmbKursus);
        addRow(8, lblFasilitas, panelFasilitas);
        addRow(9, lblRingkasan, scrollRingkasan);

        // Menempatkan panel tombol di baris paling bawah grid
        GridBagConstraints tombolConstraints = new GridBagConstraints();
        tombolConstraints.gridx = 1;
        tombolConstraints.gridy = 10;
        tombolConstraints.weightx = 1.0;
        tombolConstraints.fill = GridBagConstraints.HORIZONTAL;
        tombolConstraints.anchor = GridBagConstraints.CENTER;
        tombolConstraints.insets = new Insets(24, 8, 0, 0);
        panelForm.add(panelTombol, tombolConstraints);

        // Pengisi baris terakhir supaya isi form menempel di atas (tidak melar ke tengah)
        GridBagConstraints fillerConstraints = new GridBagConstraints();
        fillerConstraints.gridx = 0;
        fillerConstraints.gridy = 11;
        fillerConstraints.weighty = 1.0;
        fillerConstraints.fill = GridBagConstraints.VERTICAL;
        JPanel filler = new JPanel();
        filler.setOpaque(false);
        panelForm.add(filler, fillerConstraints);

        // Bungkus form dengan JScrollPane agar bisa di-scroll kalau layar kecil
        JScrollPane scrollForm = new JScrollPane(panelForm);
        scrollForm.setBorder(BorderFactory.createEmptyBorder());
        scrollForm.getViewport().setBackground(warnaLatar);
        scrollForm.getVerticalScrollBar().setUnitIncrement(16);
        scrollForm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollForm.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(scrollForm, BorderLayout.CENTER);
        setContentPane(panelUtama);
    }

    private void addRow(int row, JLabel label, java.awt.Component component) {
        GridBagConstraints cLabel = new GridBagConstraints();
        cLabel.gridx = 0;
        cLabel.gridy = row;
        cLabel.anchor = GridBagConstraints.NORTHWEST;
        cLabel.insets = new Insets(10, 0, 10, 20);
        panelForm.add(label, cLabel);

        GridBagConstraints cField = new GridBagConstraints();
        cField.gridx = 1;
        cField.gridy = row;
        cField.weightx = 1.0;
        cField.fill = GridBagConstraints.HORIZONTAL;
        cField.anchor = GridBagConstraints.NORTHWEST;
        cField.insets = new Insets(6, 0, 6, 0);
        panelForm.add(component, cField);
    }

    private String getJenisKelamin() {
        if (rbLaki.isSelected()) return "L";
        if (rbPerempuan.isSelected()) return "P";
        return "";
    }

    private void clearForm() {
        txtNim.setText("");
        txtNama.setText("");
        buttonGroupJenisKelamin.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtKodeAkses.setText("");
        cmbKursus.setSelectedIndex(0);
        cbModul.setSelected(false);
        cbSertifikat.setSelected(false);
        cbRekaman.setSelected(false);
        txtRingkasan.setText("");
        txtNim.requestFocus();
    }

    // =========================================================================
    // LOGIKA PROSES & SIMPAN KE DATABASE (DUAL ACTION)
    // =========================================================================
    private void btnSimpanActionPerformed() {
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();
        String jk = getJenisKelamin();

        // 1. Validasi Input Dasar
        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || jk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM, nama, jenis kelamin, dan prodi wajib diisi.", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cmbKursus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih kursus terlebih dahulu.", "Validasi Kursus", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Bungkus ke Objek Model & Proses Database XAMPP
        Peserta peserta = new Peserta(nim, nama, jk, prodi, noHp, email);
        PesertaDAO dao = new PesertaDAO();

        try {
            boolean berhasilSimpan = dao.insert(peserta);

            if (berhasilSimpan) {
                // 3. Jika Database Sukses, Cetak Ringkasan ke JTextArea
                String kursusPilihan = cmbKursus.getSelectedItem().toString();
                String jkLengkap = jk.equals("L") ? "Laki-Laki" : "Perempuan";

                StringBuilder sb = new StringBuilder();
                sb.append("===== DATA PESERTA BERHASIL DISIMPAN =====\n");
                sb.append("NIM             : ").append(nim).append("\n");
                sb.append("Nama Peserta    : ").append(nama).append("\n");
                sb.append("Jenis Kelamin   : ").append(jkLengkap).append("\n");
                sb.append("Program Studi   : ").append(prodi).append("\n");
                sb.append("Nomor HP        : ").append(noHp).append("\n");
                sb.append("Email           : ").append(email).append("\n");
                sb.append("Kursus Diikuti  : ").append(kursusPilihan).append("\n");
                sb.append("Fasilitas Tambahan:\n");

                if (cbModul.isSelected()) sb.append(" - Modul Cetak\n");
                if (cbSertifikat.isSelected()) sb.append(" - Sertifikat\n");
                if (cbRekaman.isSelected()) sb.append(" - Akses Rekaman\n");

                if (!cbModul.isSelected() && !cbSertifikat.isSelected() && !cbRekaman.isSelected()) {
                    sb.append(" - Tanpa Fasilitas Tambahan\n");
                }

                sb.append("==============================================\n");
                txtRingkasan.setText(sb.toString());

                // Dialog Sukses
                JOptionPane.showMessageDialog(this, "Data peserta berhasil disimpan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak tersimpan.", "Informasi", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void btnResetActionPerformed() {
        int jawab = JOptionPane.showConfirmDialog(this, "Kosongkan seluruh data form?", "Konfirmasi Reset", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            clearForm();
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FormPeserta().setVisible(true);
        });
    }
}