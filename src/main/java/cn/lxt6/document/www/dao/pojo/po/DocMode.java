package cn.lxt6.document.www.dao.pojo.po;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 文档模块 doc_mode
 */
public class DocMode {
	private Integer id;

	/*'文档类别名称'*/
	private String name;

	/*'父类别'*/
	@JsonProperty("parent_id")
	private Integer parentId;

	/*'级别'*/
	private Integer level;

	/*'服务器地址.一级分类才有值，ip:端口'*/
	@JsonProperty("server_uri")
	private String serverUri;

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
	public Integer getParentId(){
		return parentId;
	}
	public void setParentId(Integer parentId){
		this.parentId=parentId;
	}
	public Integer getLevel(){
		return level;
	}
	public void setLevel(Integer level){
		this.level=level;
	}
	public String getServerUri(){
		return serverUri;
	}
	public void setServerUri(String serverUri){
		this.serverUri=serverUri;
	}
}