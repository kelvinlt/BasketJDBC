package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import obj.Player;
import obj.Team;

/**
 *
 * @author dawm
 */
public class BasketDao {

    private Connection conexion;

    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/basket";
        String user = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, user, pass);
    }
    
    public void desconectar()throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    public void insertarTeam(Team t) throws SQLException {
        String insert = "insert into team values (?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, t.getName());
        ps.setString(2, t.getCity());
        Date date = Date.valueOf(t.getCreation());
        ps.setDate(3, date);
        ps.executeUpdate();
        ps.close();
    }

    public void insertPlayer(Player p) throws SQLException {
        String insert = "insert into player values (?,?,?,?,?,?;?)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, p.getName());
        Date date = Date.valueOf(p.getBirth());
        ps.setDate(2, date);
        ps.setInt(3, p.getNbaskets());
        ps.setInt(4, p.getNassists());
        ps.setInt(5, p.getNrebounds());
        ps.setString(6, p.getPosition());
        ps.setString(7, p.getTeam().getName());
        ps.executeUpdate();
        ps.close();
    }
}
