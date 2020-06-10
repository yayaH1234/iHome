package com.example.ihome;

public class AllUrls {

    //users (Custommers)
  //  private static String iphoste="192.168.1.108";Ù
    private static String iphoste="192.168.1.108";

    public static String sign1Url =  "http://"+iphoste+":8080/custommer/signup1";
    public static String sign2Url ="http://"+iphoste+":8080/custommer/signup2";
    public static String sign3Url ="http://"+iphoste+":8080/custommer/signup3";

//[{"id":"5ec2e63a283bbe57a09183d7","nom_mais":"jvjgj","nom_prop":"jfjfjf","nom_loc":null,"type_serv":"jfjfjf","adress":"jvjv","attitude":"null","longiture":"null","prix_serv":"66","imagedp"
 //   id nom_mais , nom_prop , nom_loc , type_serv , adress ,attitude  , longitude , prix_serv , imagedp
    //maison


    //{"id":"441495","prenom":"ta","email":"grub@gmail.com","password":"grub","numeroTel":null,"imagedp":{"type":0,"data":"Ur/9k="},"repsec":null,"questSec":null,"nom":"yahya"}

    public static String addMaisonUrl = "http://"+iphoste+":8080/maison/addM";
    public static String listMaisonUrl = "http://"+iphoste+":8080/maison/get";

    public static String listNearbyhomeMForRent="http://"+iphoste+":8080/maison/listmbyserv/rent";
    public static String listNearbyhomeMForSell="http://"+iphoste+":8080/maison/listmbyserv/sell";


    public static String getclientinformation="http://"+iphoste+":8080/maison/listm/";


    public static String getlistemail="http://"+iphoste+":8080/custommer/listemail";

    public static String gettingPassword="http://"+iphoste+":8080/custommer/getPassword2/";

    public static String achatMs="http://"+iphoste+":8080/maison/achat/";

    public static String userinformationtool2="http://"+iphoste+":8080/custommer/userpro/";
    public static String userinformationtool="http://"+iphoste+":8080/custommer/listuser/";


    public static String userinfDell="http://"+iphoste+":8080/maison/delete/";

    public static String userlogin="http://"+iphoste+":8080/custommer/listuser/authontif/";


    public static String getmsJSON="http://"+iphoste+":8080/maison/listmaisJSON/";


    public static String maistestprop="http://"+iphoste+":8080/maison/verifieMs/";


    public static String getpropformod1="http://"+iphoste+":8080/custommer/modiff1/get/";
    public static String getpropformod2="http://"+iphoste+":8080/custommer/modiff2/get/";


    public static String setpropformod1="http://"+iphoste+":8080/custommer/modiff1";
    public static String setpropformod2="http://"+iphoste+":8080/custommer/modiff2";
    public static String setpropformod3="http://"+iphoste+":8080/custommer/modiff3";
}