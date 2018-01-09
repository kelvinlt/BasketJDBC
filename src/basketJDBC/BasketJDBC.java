package basketJDBC;

import dao.BasketDao;
import java.sql.SQLException;
import java.time.LocalDate;
import obj.Player;
import obj.Team;

public class BasketJDBC {

    public static void main(String[] args) {
        System.out.println("Conectado con la base datos...");
        BasketDao basketDAO = new BasketDao();
        try {
            //iniciar conexion con base de datos
            basketDAO.conectar();
            //-------------------------------------------------------------------------------------------------//
            //creacion de un nuevo team
            Team t1 = new Team("Zetakar", "Barcelona", LocalDate.of(2016, 1, 27));
            System.out.println("Insertando equipo: " + t1.getName());
            try {
                //{{01}}insersion de un team a la base de datos
                basketDAO.insertarTeam(t1);
                System.out.println("Equipo insertado: " + t1.getName());
            } catch (SQLException ex) {
                System.out.println("Error en el alta del equipo: " + ex.getMessage());
            }
            //-------------------------------------------------------------------------------------------------//
            //creacion de un nuevo team
            Team t2 = new Team("Stuco", "Barcelona", LocalDate.of(2016, 1, 27));
            System.out.println("Insertando equipo: " + t2.getName());
            try {
                //insersion de un team a la base de datos
                basketDAO.insertarTeam(t2);
                System.out.println("Equipo insertado: " + t2.getName());
            } catch (SQLException ex) {
                System.out.println("Error en el alta del equipo: " + ex.getMessage());
            }
            //-------------------------------------------------------------------------------------------------//
            //creacion de un nuevo jugador
            Player p1 = new Player("Kelvin", LocalDate.of(1996, 1, 27), 0, 20, 5, "defensa", t1);
            System.out.println("Insertando jugador: " + p1.getName());
            try {
                //{{02}}insersion de un jugador a la base de datos
                basketDAO.insertPlayer(p1);
                System.out.println("Jugador insertado: " + p1.getName() + "| En equipo: " + p1.getTeam().getName());
            } catch (SQLException ex) {
                System.out.println("Error en el alta del jugador: " + ex.getMessage());
            }
                        //-------------------------------------------------------------------------------------------------//
            //creacion de un nuevo jugador
            Player p2 = new Player("Josep", LocalDate.of(1996, 5, 21), 10, 10, 5, "ataque", t1);
            System.out.println("Insertando jugador: " + p2.getName());
            try {
                //insersion de un jugador a la base de datos
                basketDAO.insertPlayer(p2);
                System.out.println("Jugador insertado: " + p2.getName() + "| En equipo: " + p2.getTeam().getName());
            } catch (SQLException ex) {
                System.out.println("Error en el alta del jugador: " + ex.getMessage());
            }
            //-------------------------------------------------------------------------------------------------//
            Player p1a = new Player("Kelvin", 100, 1000, 10);
            try {
                //{{03}}Objeto jugador con nombre de un jugador existente y numeros a modificar
                basketDAO.modifyPlayerBAR(p1a);
                System.out.println("Se ha modificado los baskets,assistencias y rebotes del jugador: " + p1a.getName());
            } catch (SQLException ex) {
                System.out.println("Error en la modificacion 1: " + ex.getMessage());
            }
            //-------------------------------------------------------------------------------------------------//
            Player p1b = new Player("Kelvin", t2);
            p1b = new Player("Algo",t1);
            try {
                //{{04}}Objeto jugador con nombre de un jugador existente y equipo a modificar
                basketDAO.modifyPlayerTeam(p1b);
                System.out.println("Se ha modificado el equipo del jugador: " + p1b.getName());
            } catch (SQLException ex) {
                System.out.println("Error en la modificacion 2: " + ex.getMessage());
            }
            //-------------------------------------------------------------------------------------------------//
            String borrarKelvin = "Kelvin";
            try {
                //{{05}}Borrar jugador con nombre X
                basketDAO.removePlayerFromTable(borrarKelvin);
                System.out.println("Se ha borrado el jugador Kelvin");
            } catch (SQLException ex) {
                System.out.println("Error en la eliminacion:"+ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("Error al entrar en la base de datos: " + ex.getMessage());
        }
    }

}
