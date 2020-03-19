package cn.lxt6.document.www.dao.pojo.po;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 
 */
public class ResponseLevel {
	private Integer id;

	/*'响应级别'*/
	private String code;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code=code;
	}
}