package a.b;

import org.hibernate.Session;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        HibernateUtil.getSessionFactory();
        
        Session session = HibernateUtil.openSession();
        
        session.beginTransaction();
        
        Account a = (Account)session.get(Account.class, 16335);
        
        System.out.println(a);
        System.out.println(a.getAcctNum());
        System.out.println(a.getAcctFullName());
        
        /*Account anAccount1 = new Account();
        anAccount1.setAcctNum(new BigDecimal(1001));
        anAccount1.setAcctName("Account A");
        
        AccountAddress anAccountAddress1 = new AccountAddress(new BigInteger("1001"), new BigInteger("1"));
        anAccountAddress1.setAcctAddrName("Account A Address1");
        
        AccountAddress anAccountAddress2 = new AccountAddress(new BigInteger("1001"), new BigInteger("2"));
        anAccountAddress2.setAcctAddrName("Account A Address2");
        
        Collection<AccountAddress> accountAddressCollection = new ArrayList<AccountAddress>();
        accountAddressCollection.add(anAccountAddress1);
        accountAddressCollection.add(anAccountAddress2);
        anAccount1.setAccountAddressCollection(accountAddressCollection);
        
        AccountContact anAccountContact1 = new AccountContact(new BigInteger("1001"), new BigInteger("1"));
        anAccountContact1.setAcctContName("Account A Contact1");
        anAccountContact1.setAccountAddress(anAccountAddress1);
        
        AccountContact anAccountContact2 = new AccountContact(new BigInteger("1001"), new BigInteger("2"));
        anAccountContact2.setAcctContName("Account A Contact2");
        anAccountContact2.setAccountAddress(anAccountAddress2);
        
        Collection<AccountContact> accountContactCollection = new ArrayList<AccountContact>();
        accountContactCollection.add(anAccountContact1);
        accountContactCollection.add(anAccountContact2);
        anAccount1.setAccountContactCollection(accountContactCollection);
        
        session.save(anAccount1);
        session.getTransaction().commit();*/
        
        System.out.println( "Completed");
        System.exit(0);
    }
}