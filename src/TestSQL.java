import java.sql.*;


public class TestSQL {

    public static void main(String[] args) throws SQLException{

        String url = "jdbc:mysql://localhost:3306/onlineStore_database";
        String userName = "root";
        String password = "password;";

        // establish connection object
        Connection connection = DriverManager.getConnection(url,userName,password);


        // create statement object to send to the database
        Statement statement = connection.createStatement();

        // execute the statement object
        ResultSet resultSetPersoane = statement.executeQuery("SELECT * FROM persoane_tbl");

        // process the result
        System.out.println("Lista persoane:");
        while (resultSetPersoane.next()){
            System.out.print(resultSetPersoane.getInt("id") + " ");
            System.out.print(resultSetPersoane.getString("nume") + " ");
            System.out.print(resultSetPersoane.getString("adresa") + " ");
            System.out.print(resultSetPersoane.getString("limba"));
            System.out.println();
        }

        System.out.println();

        ResultSet resultSetProduse = statement.executeQuery("SELECT * FROM produse_tbl");

        // process the result
        System.out.println("Lista produse:");
        while (resultSetProduse.next()){
            System.out.print(resultSetProduse.getInt("id") + " ");
            System.out.print(resultSetProduse.getString("numeProdus") + " ");
            System.out.print(resultSetProduse.getInt("pret") + " ");
            System.out.print(resultSetProduse.getInt("garantie") + " ");
            System.out.print(resultSetProduse.getString("specificatii"));
            System.out.println();
        }

        System.out.println();

        /*ResultSet resultSetTranzactii = statement.executeQuery("SELECT * FROM tranzactii_tbl");

        // process the result
        System.out.println("Lista tranzactii:");
        while (resultSetTranzactii.next()){
            System.out.print(resultSetTranzactii.getInt("id") + " ");
            System.out.print(resultSetTranzactii.getInt("client") + " ");
            System.out.print(resultSetTranzactii.getInt("produs") + " ");
            System.out.print(resultSetTranzactii.getInt("cantitate") + " ");
            System.out.print(resultSetTranzactii.getDate("data"));
            System.out.println();
        }*/

        ResultSet resultSetTranzactii2 = statement.executeQuery("SELECT tranzactii_tbl.id," +
                " persoane_tbl.nume, " +
                "produse_tbl.numeProdus, " +
                "tranzactii_tbl.cantitate, " +
                "tranzactii_tbl.data " +
                "FROM tranzactii_tbl JOIN persoane_tbl ON tranzactii_tbl.client=persoane_tbl.id " +
                "JOIN produse_tbl ON tranzactii_tbl.produs=produse_tbl.id");

        // process the result
        System.out.println("Lista tranzactii:");
        while (resultSetTranzactii2.next()){
            System.out.print(resultSetTranzactii2.getInt("id") + " ");
            System.out.print(resultSetTranzactii2.getString("nume") + " ");
            System.out.print(resultSetTranzactii2.getString("numeProdus") + " ");
            System.out.print(resultSetTranzactii2.getInt("cantitate") + " ");
            System.out.print(resultSetTranzactii2.getDate("data"));
            System.out.println();
        }

        // execute update on persoane_tbl
        int rowsAffected;
        System.out.println();

        // insert a new person
        /*rowsAffected = statement.executeUpdate("INSERT INTO persoane_tbl VALUES (7,'Cristina P.','Bucuresti','RO')");
        System.out.println("Executed an Insert Statement - Rows Affected: " + rowsAffected);*/

        // delete a person
        /*rowsAffected = statement.executeUpdate("DELETE FROM persoane_tbl WHERE id = 7");
        System.out.println("Executed a Delete Statement - Rows Affected: " + rowsAffected);*/

        // update a person data
        rowsAffected = statement.executeUpdate("UPDATE persoane_tbl SET limba = 'RO' WHERE id = 6");
        System.out.println("Executed an Update Statement - Rows Affected: " + rowsAffected);

        System.out.println();
        ResultSet resultSetPersoane2 = statement.executeQuery("SELECT * FROM persoane_tbl");
        while (resultSetPersoane2.next()){
            System.out.print(resultSetPersoane2.getInt("id") + " ");
            System.out.print(resultSetPersoane2.getString("nume") + " ");
            System.out.print(resultSetPersoane2.getString("adresa") + " ");
            System.out.print(resultSetPersoane2.getString("limba"));
            System.out.println();
        }
    }
}
