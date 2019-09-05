/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dal;

import com.context.DBContext;
import com.entity.MessageDetail;
import java.sql.Connection;
import com.context.DBContext;
import com.entity.MessageType;
import java.sql.PreparedStatement;
import java.sql.Date;
/**
 *
 * @author ADMIN
 */
public class MessageDAO {

   
    //add a new message detail
    public void addMessageDetail(MessageDetail u) throws Exception { 
        /*insert code for inserting a new MessageDetail to table MessageDetail*/
        String insert = "insert into MessageDetail(FromUser, ToUser, DateCreated, [Content], MessageType) values(?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setString(1,u.getFromUser());
        ps.setString(2,u.getToUser());
        ps.setDate(3,new Date(u.getDateCreated().getTime()));
        ps.setString(4,u.getContent());
        if(u.getMessageType() == MessageType.LOGIN){
            ps.setString(5,"Login");
        }else if(u.getMessageType() == MessageType.LOGOUT){
            ps.setString(5,"Logout");
        }else{
            ps.setString(5,"Message");
        }
        ps.executeUpdate();
        ps.close();
        conn.close();
        
    }
    

}
