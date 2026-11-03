
package dao;

import config.Database;
import model.Peserta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class PesertaDAO {

    // MENGUBAH RESULTSET MENJADI OBJECT PESERTA
    private Peserta mapRow(ResultSet rs)
            throws SQLException {

        Peserta p = new Peserta();

        p.setId(rs.getInt("id_peserta"));
        p.setNim(rs.getString("nim"));
        p.setNama(rs.getString("nama"));
        p.setJenisKelamin(rs.getString("jenis_kelamin"));
        p.setProdi(rs.getString("prodi"));
        p.setNoHp(rs.getString("no_hp"));
        p.setEmail(rs.getString("email"));

        return p;
    }

    // READ: MENAMPILKAN SEMUA DATA
    public List<Peserta> findAll() {

        List<Peserta> data = new ArrayList<>();

        String sql = "SELECT * FROM peserta "
                + "ORDER BY id_peserta DESC";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.err.println("Gagal membaca data: "
                    + e.getMessage());
        }

        return data;
    }

    // CREATE: MENYIMPAN DATA
    public boolean insert(Peserta p) {

        String sql = "INSERT INTO peserta "
                + "(nim, nama, jenis_kelamin, prodi, no_hp, email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getProdi());
            ps.setString(5, p.getNoHp());
            ps.setString(6, p.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Gagal menyimpan: "
                    + e.getMessage());
            return false;
        }
    }

    // UPDATE: MENGUBAH DATA
    public boolean update(Peserta p) {

        String sql = "UPDATE peserta SET "
                + "nim=?, nama=?, jenis_kelamin=?, "
                + "prodi=?, no_hp=?, email=? "
                + "WHERE id_peserta=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getProdi());
            ps.setString(5, p.getNoHp());
            ps.setString(6, p.getEmail());
            ps.setInt(7, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Gagal mengubah: "
                    + e.getMessage());
            return false;
        }
    }

    // DELETE: MENGHAPUS DATA
    public boolean delete(int id) {

        String sql = "DELETE FROM peserta "
                + "WHERE id_peserta=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Gagal menghapus: "
                    + e.getMessage());
            return false;
        }
    }

    // SEARCH: MENCARI BERDASARKAN NIM ATAU NAMA
    public List<Peserta> search(String keyword) {

        List<Peserta> data = new ArrayList<>();

        String sql = "SELECT * FROM peserta "
                + "WHERE nim LIKE ? OR nama LIKE ? "
                + "ORDER BY id_peserta DESC";

        String pattern = "%" + keyword.trim() + "%";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    data.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Gagal mencari: "
                    + e.getMessage());
        }

        return data;
    }
}
