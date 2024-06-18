package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.data.CommuneBase;

public class VoisinageDAO extends DAO<CommuneBase>{
    /**
     * &nbsp;
     */
    public VoisinageDAO() {
        super();
    }

    /**
     * &nbsp;
     * 
     * @param username &nbsp;
     * @param password &nbsp;
     */
    public VoisinageDAO(String username, String password) {
        super(username, password);
    }



    public int create(int commune, int communeVoisine) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO Voisinage (commune, communeVoisine) VALUES (?, ?)")) {
            statement.setInt(1, commune);
            statement.setDouble(2 ,communeVoisine);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int delete(int commune, int communeVoisine){
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Voisinage WHERE commune = ? AND communeVoisine = ?")) {
            statement.setInt(1, commune);
            statement.setInt(2, communeVoisine);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    

        return result;
    }


    @Override
    public CommuneBase findByID(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findByID'");
    }

    @Override
    public int create(CommuneBase user) {
        throw new UnsupportedOperationException("An other methode create with 2 parameters is implemented (int commune, int communeVoisine)");
    }
    
    @Override
    public int update(CommuneBase user) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(CommuneBase user) {
        throw new UnsupportedOperationException("An other methode delete with 2 parameters is implemented (int commune, int communeVoisine)");
    }
    
}
