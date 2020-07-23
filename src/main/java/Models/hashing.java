package Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hashing {
	
	       private String passwordToHash = "password";
	       private String generatedPassword = null;
	        
	     public hashing() {
				super();
				// TODO Auto-generated constructor stub
			}

		public hashing(String passwordToHash) {
				super();
				this.passwordToHash = passwordToHash;
			}

		public String hash(String hash) {
	    	this.setPasswordToHash(hash); 
			try {
	            // Create MessageDigest instance for MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            //Add password bytes to digest
	            md.update(passwordToHash.getBytes());
	            //Get the hash's bytes 
	            byte[] bytes = md.digest();
	            //This bytes[] has bytes in decimal format;
	            //Convert it to hexadecimal format
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            generatedPassword = sb.toString();
	            return generatedPassword;
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
	       return null;
	}

		public String getPasswordToHash() {
			return passwordToHash;
		}

		public void setPasswordToHash(String passwordToHash) {
			this.passwordToHash = passwordToHash;
		}

		public String getGeneratedPassword() {
			return generatedPassword;
		}

		public void setGeneratedPassword(String generatedPassword) {
			this.generatedPassword = generatedPassword;
		}
		
}

