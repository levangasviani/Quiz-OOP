package User;

import java.sql.SQLException;
import java.util.ArrayList;

import friend.FriendManager;

public class app {

	public static void main(String[] args) throws SQLException {
		AccountManager am = new AccountManager();
		FriendManager fm = new FriendManager();

		System.out.println(fm.getAllFriends("SIUS25").toString());

		am.addAccount("levangasviani", "qwerty", "Levani", "Gasviani", "lgasv15@freeuni.edu.ge", 2);
		am.addAccount("shotakobaxidze", "123456", "Shota", "Kobaxidze", "skoba15@freeuni.edu.ge", 1);
		am.addAccount("levankaranadze", "asdasd", "Levani", "Karanadze", "lkara15@freeuni.edu.ge", 1);
		am.addAccount("lashakharshiladze", "cubismama", "Lasha", "Kharshiladze", "lkhar15@freeuni.edu.ge", 1);
		am.addAccount("sandrotsiskadze", "koloriti777", "Sandro", "Tsiskadze", "stsis15@freeuni.edu.ge", 1);

		Account acc1 = am.getAccount("levangasviani");
		Account acc2 = am.getAccount("shotakobaxidze");
		Account acc3 = am.getAccount("levankaranadze");
		Account acc4 = am.getAccount("lashakharshiladze");
		Account acc5 = am.getAccount("sandrotsiskadze");

		System.out.println(acc1.toString());
		System.out.println(acc2.toString());
		System.out.println(acc3.toString());
		System.out.println(acc4.toString());
		System.out.println(acc5.toString());

		am.deleteAccount("levangasviani", "sandrotsiskadze");
		am.deleteAccount("shotakobaxidze", "levangasviani");

		ArrayList<Account> al = am.getAccountsList();
		for (Account a : al) {
			System.out.println(a.toString());
			System.out.println(am.getAccountId(a.getUserName()) + " ");
		}

	}

}
