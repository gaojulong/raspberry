
## 树莓派
### 1.  开发环境:   	 
	1. android studio   
### 2.  使用说明
- 首先在raspberry上搭建webiopi环境  

### 3.  个人笔记
- Authorization验证  
- 注意：Base64.encodeBase64导入包必须    
	>import org.apache.commons.codec.binary.Base64;
	
		authString = "name"+":"+"pass";
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
    	conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
    	conn.setRequestProperty("User-Agent", "MSIE 7.0");
	
