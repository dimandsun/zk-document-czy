package cn.lxt6.document.www.dao.pojo.po;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 
 */
public class User {
	private Integer id;

	/*'用户code'*/
	private String code;

	/*'用户名称'*/
	private String name;

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
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
}