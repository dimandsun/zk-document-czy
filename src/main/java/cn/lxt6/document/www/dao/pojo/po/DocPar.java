package cn.lxt6.document.www.dao.pojo.po;

import cn.lxt6.document.www.dao.enums.JavaTypeEnum;
import cn.lxt6.document.www.dao.enums.ParTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 文档参数
 */
public class DocPar {
	private Integer id;

	/*'文档id'*/
	@JsonProperty("doc_id")
	private Integer docId;

	/*'参数名称'*/
	private String name;

	/*'参数说明'*/
	private String des;

	/*'备注'*/
	private String notes;

	/*'参数类型：'*/

	@JsonProperty("data_type")
	private JavaTypeEnum dataType;

	/*'参数长度'*/
	private Integer length;

	/*'是否必填：0*/
	@JsonProperty("is_must")
	private Boolean isMust;

	/*'参数类型：1 请求参数，2请求返回数据，3 数据库表字段*/
	@JsonProperty("par_type")
	private ParTypeEnum parType;

	public JavaTypeEnum getDataType() {
		return dataType;
	}

	public void setDataType(JavaTypeEnum dataType) {
		this.dataType = dataType;
	}


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getDocId(){
		return docId;
	}
	public void setDocId(Integer docId){
		this.docId=docId;
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
	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	public Integer getLength(){
		return length;
	}
	public void setLength(Integer length){
		this.length=length;
	}

	public Boolean getIsMust(){
		return isMust;
	}
	public void setIsMust(Boolean isMust){
		this.isMust=isMust;
	}
	public ParTypeEnum getParType(){
		return parType;
	}
	public void setParType(ParTypeEnum parType){
		this.parType=parType;
	}
}