public class EmailTEstDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EmailAccount myAccount1 = new EmailAccount("Brutus", "Buckeye");
		System.out.println(myAccount1.getName());

		EmailAccount myAccount2 = new EmailAccount("Nathan", "Newman");
		System.out.println(myAccount2.getName());

		EmailAccount myAccount3 = new EmailAccount("Josie", "Rond");
		System.out.println(myAccount3.getName());

		EmailAccount myAccount4 = new EmailAccount("Brian", "Buckeye");
		System.out.println(myAccount4.getName());

		EmailAccount myAccount5 = new EmailAccount("Josie", "Newman");
		System.out.println(myAccount5.getName());

		EmailAccount myAccount6 = new EmailAccount("Josephine", "Rond");
		System.out.println(myAccount6.getName());

		EmailAccount myAccount7 = new EmailAccount("Nate", "Newman");
		System.out.println(myAccount7.getName());

		System.out.println(myAccount7.getEmailAddress());
		System.out.println(myAccount2.getEmailAddress());
		System.out.println(myAccount3.getEmailAddress());
		System.out.println(myAccount4.getEmailAddress());
		System.out.println(myAccount1.getEmailAddress());
		System.out.println(myAccount5.getEmailAddress());
		System.out.println(myAccount6.getEmailAddress());

	}

}
