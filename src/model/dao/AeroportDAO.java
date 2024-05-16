package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.data.Aeroport;
import model.data.Departement;

public class AeroportDAO extends DAO<Aeroport> {

    @Override
    public ArrayList<Aeroport> findAll() {
        ArrayList<Aeroport> aeroports = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Aeroport");
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                long leDepartementId = rs.getLong("leDepartement");
                DepartementDAO departementDAO = new DepartementDAO();
                Departement departement = departementDAO.findByID(leDepartementId);
                Aeroport aeroport = new Aeroport(nom, adresse, departement);
                aeroports.add(aeroport);
            }   
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aeroports;
    }

    @Override
    public Aeroport findByName(String name) {
        Aeroport aeroport = null;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Aeroport WHERE nom = ?")) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    String adresse = rs.getString("adresse");
                    long leDepartementId = rs.getLong("leDepartement");
                    DepartementDAO departementDAO = new DepartementDAO();
                    Departement departement = departementDAO.findByID(leDepartementId);
                    aeroport = new Aeroport(nom, adresse, departement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aeroport;
    }

    @Override
    public int update(Aeroport aeroport) {
        int rowsUpdated = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE Aeroport SET nom = ? WHERE adresse = ?")) {
            statement.setString(1, aeroport.getNom());
            statement.setString(2, aeroport.getAdresse()); 
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    @Override
    public int delete(Aeroport aeroport) {
        int rowsDeleted = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Aeroport WHERE nom = ?")) {
            statement.setString(1, aeroport.getNom()); 
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;
    }

    @Override
    public int create(Aeroport aeroport) {
        int id = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Aeroport (nom, adresse, leDepartement) VALUES (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, aeroport.getNom());
            statement.setString(2, aeroport.getAdresse());
            statement.setLong(3, aeroport.getDepartement().getIdDep());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Aeroport findByID(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }
}
