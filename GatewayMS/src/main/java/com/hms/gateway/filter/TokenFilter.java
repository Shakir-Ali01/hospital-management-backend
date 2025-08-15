package com.hms.gateway.filter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenFilter extends AbstractGatewayFilterFactory<TokenFilter.Config> {
    private static final String JWT_SECRET_KEY="37a2545de4851cbf6456f58f0e921cee546d3c3eb8ea29a14cc99fc8c54b82508e028b1d53bcd27849a7e4338eedd914252531a404d2bdd0bf384ef1fcdcd710";
    public TokenFilter(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config){
        return(exchange,chain)->{
           String path=exchange.getRequest().getPath().toString();
           if(path.equals("/user/login")|| path.equals("/user/register")){
            return chain.filter(exchange.mutate().request(r-> r.header("X-Secret-Key", "SECRET")).build());
           }
           HttpHeaders headers=exchange.getRequest().getHeaders();
           if(!headers.containsKey(HttpHeaders.AUTHORIZATION)){
            throw new RuntimeException("Authorization header is missing");
           }
           String authHeader=headers.getFirst(HttpHeaders.AUTHORIZATION);
           System.out.println("Authorization Header: " +authHeader);
           if(authHeader==null || !authHeader.startsWith("Bearer")){
              throw new RuntimeException("Authorization header is invalid");
           }
           String token=authHeader.substring(7);
           try{
               Claims claim=Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
               exchange=exchange.mutate().request(r-> r.header("X-Secret-Key", "SECRET")).build();
           }catch(Exception e){
            throw new RuntimeException("Token is Invalid");
           }
           return chain.filter(exchange);
        };
    }
public static class Config{

}
    
}
