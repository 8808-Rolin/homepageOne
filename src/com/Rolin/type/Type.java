package com.Rolin.type;

import java.io.Serializable;

public class Type {
    public static class UserReg implements Serializable {
        public String Name;
        public String Tele;
        public String Email;

        public UserReg(String name,String tele,String email){
            Name = name;
            Tele = tele;
            Email = email;
        }
    }
}
