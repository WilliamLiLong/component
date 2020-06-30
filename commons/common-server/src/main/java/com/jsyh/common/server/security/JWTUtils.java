package com.jsyh.common.server.security;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.jsyh.common.core.constants.TokenConstants;
import com.jsyh.common.server.configuration.CustomerProperties;
import com.jsyh.common.server.constants.SysRetCodeConstants;
import com.jsyh.common.server.exception.ValidateException;

/**
 * @author WilliamLi
 *
 */
public class JWTUtils {
 
	private final  String secret="324iu23094u598ndsofhsiufhaf_+0wq-42q421jiosadiusadiasd";
	
	@Autowired
	private CustomerProperties customerProperties;

	//通过用户信息创建token
    public String creatJwtToken (String msg) throws Exception {
    	
        msg = new AESUtil(msg).encrypt(); //先对信息进行aes加密(防止被破解）
        String token = null;
  
        token = JWT.create()
                    .withIssuer("wlgzs").withExpiresAt(DateTime.now().plusMinutes(getTime()).toDate())
                    .withClaim("user", msg)
                    .sign(Algorithm.HMAC256(secret));
  
        return token;
    }
    
    //解密jwt并验证是否正确
    public String freeJwt (String token) throws ValidateException{
    	
        DecodedJWT decodedJWT = null;
       
        //使用hmac256加密算法
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
             .withIssuer("wlgzs")
             .build();
        decodedJWT = verifier.verify(token);
               
        String [] headPayload = token.split("\\.");	  //获得token的头部，载荷和签名，只对比头部和载荷      
        String header = decodedJWT.getHeader();	   //获得jwt解密后头部 
        String payload = decodedJWT.getPayload();	 //获得jwt解密后载荷
        
        if(!header.equals(headPayload[0]) && !payload.equals(headPayload[1])){
            throw new ValidateException(SysRetCodeConstants.TOKEN_VALID_FAILED.getCode(),SysRetCodeConstants.TOKEN_VALID_FAILED.getMessage());
        }
        
        return new AESUtil(decodedJWT.getClaim("user").asString()).decrypt();
               
    }
    
  //获取默认token过期时间
  	public int getTime() {
  		
  		long time = 0L;
  		
  		if(customerProperties.getExpiredTimeMinutes() == 0) {
  			time = TokenConstants.TOKEN_EXPIRED_TIME_MINUTES;
  		}else {
  			time = customerProperties.getExpiredTimeMinutes();
  		}
  		
  		return Integer.parseInt(String.valueOf(time));
  	}
    
}
