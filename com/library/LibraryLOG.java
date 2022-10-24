package com.library;

import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;

public class LibraryLOG extends LibDataBase {
    public static void putLog(String studentLibraryId){

        System.out.println("ID is passed to LibraryLOG");

        double ran=Math.random()*10000;
        Integer mapkey=(int)ran;
        String currentDay = String.valueOf(java.time.LocalDate.now());
        System.out.println(currentDay);
        String inputQuery;

        System.out.println("Initilization completed");
        if(map.containsKey(studentLibraryId)){
            System.out.println(map);
            map.remove(studentLibraryId);
            System.out.println(map);

            Timestamp logout=Timestamp.from(Instant.now());
            System.out.println(logout);
            inputQuery="Update LibraryLog set log_out_time='"+logout+"' where student_Library_Id='"+ studentLibraryId +"'";
            System.out.println("updated");
            no_of_stu_inLIb-=1;
        }
        else
        {
            inputQuery="insert into LibraryLog(LOG_Date,student_library_Id) values('"+currentDay+"'"+",'"+ studentLibraryId +"');";
            map.put(studentLibraryId,mapkey);
            System.out.println(studentLibraryId+" "+mapkey);
            System.out.println(map);
            no_of_stu_inLIb+=1;
        }
        try {
            Statement inputLog= connection.createStatement();
            inputLog.executeUpdate(inputQuery);
            System.out.println("inserted into librarylog table");
            System.out.println("number of students in the library "+no_of_stu_inLIb);
            return;
        } catch (Exception e) {
            System.out.println("The LibraryLOG Exception");
            e.printStackTrace();
            System.out.println("Exception handled");
        }
    }
}

/*this class inserts into library log and inserts library id and date and timestamp in mysql datebase
used to hash map to the keep track of students who are inside the library
to create map key random number is used to create different key
this class extends LibDataBase
 */
