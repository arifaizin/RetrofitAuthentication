package id.co.imastudio.retrofitauthentication.model;

import com.google.gson.annotations.SerializedName;

public class TokenResponse{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("token_type")
	private String tokenType;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	@Override
 	public String toString(){
		return 
			"TokenResponse{" + 
			"access_token = '" + accessToken + '\'' + 
			",token_type = '" + tokenType + '\'' + 
			"}";
		}
}