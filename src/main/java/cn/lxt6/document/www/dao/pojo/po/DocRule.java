package cn.lxt6.document.www.dao.pojo.po;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 文档规则
 */
public class DocRule {
	private Integer id;

	/*'规则名称'*/
	private String name;

	/*'规则描述'*/
	private String des;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
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
}