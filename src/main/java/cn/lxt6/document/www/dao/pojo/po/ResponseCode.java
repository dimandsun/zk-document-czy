package cn.lxt6.document.www.dao.pojo.po;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 响应码
 */
public class ResponseCode {
	private Integer id;

	/*'响应码'*/
	private String code;

	/*'响应码名称'*/
	private String name;

	/*'响应码描述'*/
	private String des;

	/*'响应码级别'*/
	@JsonProperty("level_code")
	private String levelCode;

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
	public String getDes(){
		return des;
	}
	public void setDes(String des){
		this.des=des;
	}
	public String getLevelCode(){
		return levelCode;
	}
	public void setLevelCode(String levelCode){
		this.levelCode=levelCode;
	}
}