package at.Cracki.DinoWorld.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyAPI {
    public static int getMoney(String uuid) {
        try {
            PreparedStatement st = MySQL.con.prepareStatement("SELECT money FROM moneyTable WHERE UUID = ?");
            st.setString(1, uuid);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt("money");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void setMoney(String uuid, int coins) {
        if (getMoney(uuid) == 0) {
            try {
                PreparedStatement st = MySQL.con.prepareStatement("INSERT INTO moneyTable (UUID,money) VALUES (?,?)");
                st.setString(1, uuid);
                st.setInt(2, coins);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            PreparedStatement st = null;
            try {
                st = MySQL.con.prepareStatement("UPDATE moneyTable SET money = ? WHERE UUID = ?");
                st.setString(2, uuid);
                st.setInt(1, coins);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addMoney(String uuid, int coins) {
        int current = getMoney(uuid);
        setMoney(uuid, coins + current);
    }

    public static void takeMoney(String uuid, int coins) {
        setMoney(uuid, getMoney(uuid) - coins);
    }
}