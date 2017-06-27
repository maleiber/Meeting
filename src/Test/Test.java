package Test;

import Utils.HibernateUtils;
import com.sun.AdministratorEntity;
import com.sun.UserEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Test {

    public static void main(String[] args) {



//    Session session= HibernateUtils.getSession();
//    Transaction transaction=session.beginTransaction();
//    AdministratorEntity administratorEntity=new AdministratorEntity();
//    administratorEntity.setAdmId(2222);
//    administratorEntity.setAdmName("zxr");
//    session.save(administratorEntity);
//    transaction.commit();
//    session.close();
//    System.out.println(1111);

//        Session session1=HibernateUtils.getSession();
//        Transaction transaction1=session1.beginTransaction();
//        AdministratorEntity administratorEntity=session1.load(AdministratorEntity.class,1);
//        transaction1.commit();
//        session1.close();
//        System.out.println(administratorEntity.getAdmId());
//        System.out.println(administratorEntity.getAdmName());
//        JSONArray jsonArray=new JSONArray();
//        JSONObject jsonObject=JSONObject.fromObject(administratorEntity);
//        AdministratorEntity administratorEntity1=new AdministratorEntity();
//        administratorEntity1.setAdmName("zxr");
//        administratorEntity1.setAdmId(23);
//        jsonArray.add(administratorEntity);
//        jsonArray.add(administratorEntity1);
//
//        System.out.println(jsonArray);
    }
}
