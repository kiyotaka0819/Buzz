package model;

import java.io.Serializable;
import java.sql.Timestamp;

public record PostInfo (int postId,String userId,String comment,byte[] picture, String shopName, Timestamp postTime) implements Serializable{
	
}
