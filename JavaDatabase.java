/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatabase;

import java.io.*;
import java.time.*;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class JavaDatabase  {

     
     public static void main(String args[]) throws Exception {
     
     Scanner s = new Scanner(System.in);

        int c, usr;
        store ob = new store();

        do {
            System.out.println("::::MENU::::");
            System.out.println("ENTER 1 FOR ADDING DETAIL");
            System.out.println("ENTER 2 FOR SEARCHING");
            System.out.println("ENTER 3 FOR DELETION");
            System.out.println("ENTER 4 FOR UPDATION");
            System.out.println("ENTER 5 TO EXIT");
            int ch = s.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("enter  the no of entries");
                    usr = s.nextInt();
                    ob.add(usr);
                    break;
                case 2:
                    ob.search();
                    break;
                case 3:
                    ob.delete();
                    break;
                case 4:
                    ob.update();
                    break;
                case 5:
                    System.exit(0);
            }

            System.out.println("press any key other than 5 to do more operations else press 5 to exit");
            c = s.nextInt();
        } while (c != 5);

    }
}

abstract class student  {

    protected String name[];
    protected int roll[];
 
    
    abstract void display();
}




class store extends student                //Inheriting the abstract class using single Inheritance
{             

   int usr;

    Scanner s = new Scanner(System.in);
    int i = 0, c = 0, r = 0;

    store() {
        roll = new int[999];
        name = new String[999];
    }
    
    public boolean Found(int r,String name[])    //Utility function used for Exception Handling below 
    {
        for(int i=0;i<roll.length;i++){
            if(roll[i]==r){
                return false;
            }
        }
    return true;
    }
    
    public void add(int usr)  throws Exception         //Exception Handling
 {
    BufferedReader r=new BufferedReader(new FileReader("C:/Users/Anshi/Documents/java  programs/DATA ADDED.txt"));
   
    FileWriter f=new FileWriter("C:/Users/Anshi/Documents/java programs/DATA ADDED.txt",true);
     DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
     LocalDateTime now=LocalDateTime.now();
        
     this.usr = usr;
        for (i = 0; i < usr; i++) {
            System.out.println("Enter name and roll no ");
            name[i] = s.next();
            roll[i] = s.nextInt();
           
            f.write("DATA NUMBER"+i+"\t"+name[i]+"\t"+roll[i]+"\t"+dtf.format(now)+"\t");  
            
        }
        f.close();
        display();

        
        System.out.println("Do u wish to see the Information stored in the Database");
        String a=s.next();
        if(a.equalsIgnoreCase("yes"))
        {
       String line=r.readLine();
            while(line!=null)
        {
            System.out.println(line);
            line=r.readLine();
     
        }
        r.close();   
        }
        
    }

    public void search()  throws Exception          //ExceptionHandling
    {
 FileWriter f=new FileWriter("C:/Users/Anshi/Documents/java programs/SEARCH HISTORY.txt",true);
     DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
     LocalDateTime now=LocalDateTime.now();
        System.out.println("Enter the roll no you want to search");
        r = s.nextInt();
       try                                    //Explicit try catch mechanism
       {
    
           if(r<=0 || Found(r,name))
           {
               throw new ArithmeticException("INVALID INPUT!! or the details you searched cannot be found");
           }
           for (i = 0; i < roll.length; i++) {
            if (roll[i] == r) {
                break;
            }
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("---DETAILS OF THE SPECIFIED STUDENT IS---");
        System.out.println("NAME        ROLL NO");
        System.out.print(name[i]+"         ");
        System.out.print(roll[i]);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");    
        f.write("DATA NUMBER"+i+"\t"+name[i]+"\t"+roll[i]+"\t"+dtf.format(now)+"\t");  
        f.close();
    }
     
    catch(ArithmeticException e)                   //Explicit try catch mechanism
    {
        System.out.println("EXCEPTION:"+e.getMessage());
    }
    
    
    }
    
    
    
    public void delete() throws Exception{
      FileWriter f=new FileWriter("C:/Users/Anshi/Documents/java programs/DATA DELETED.txt",true);
     DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
     LocalDateTime now=LocalDateTime.now();
        System.out.println("Enter the rollno to be deleted");
        r = s.nextInt();

      
        
        try                              //Explicit try catch mechanism
{
    if(r<=0 || Found(r,name) )    
    {
        
        throw new ArithmeticException("INVALID INPUT!! or THE ELEMENT IS NOT FOUND");
    }
    for (i = 0; i < roll.length; i++) {
            if (roll[i] == r) {
                roll[i] = 0;
                name[i] = " ";
             f.write("DATA NUMBER"+i+"\t"+name[i]+"\t"+roll[i]+"\t"+dtf.format(now)+"\t");  
            
            }
        }
       f.close();
        display();

    }
    
    catch(ArithmeticException e)                   //Explicit try catch mechanism
    {
        
      System.out.println("EXCEPTION:"+e.getMessage());   
    }
    
    
    }
    
    
    public void update() throws Exception {
    
         
         FileWriter f=new FileWriter("C:/Users/Anshi/Documents/java programs/DATA UPDATED.txt",true);
     DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
     LocalDateTime now=LocalDateTime.now();
        
        System.out.println("Enter the rollno to be updated");
        r = s.nextInt();
 try                                         //Explicit try catch mechanism
     {
      if(r<=0 || Found(r,name) )    
    {
        
        throw new ArithmeticException("INVALID INPUT!! or THE ELEMENT YOU SEARCHED CANNOT BE FOUND!!");
    }
        for (i = 0; i < roll.length; i++) {
            if (roll[i] == r) {
                System.out.println("Enter the new name and roll no");
                name[i] = s.next();
                roll[i] = s.nextInt();
                 f.write("DATA NUMBER"+i+"\t"+name[i]+"\t"+roll[i]+"\t"+dtf.format(now)+"\t");  

         }
        }
        f.close();
        display();
        
        
    }
catch(ArithmeticException e)                                        //Explicit try catch mechanism
{
      System.out.println("EXCEPTION:"+e.getMessage()); 
}
    }
    
    
    
    @Override
    void display()                      //overriden from the abstract class
    {
        System.out.println("----THE UPDATED RECORDS OF STUDENTS ARE----");
        System.out.println("NAME        ROLL NO");
        for (i = 0; i < roll.length; i++) {
            if(roll[i]!=0)
            {               
            System.out.print(name[i]+"         ");
            System.out.print(roll[i]);
            System.out.println(" ");
            }
        }
    }
}
    
