package cn.lxt6.document.www.dao.pojo.po;
import cn.lxt6.document.www.dao.enums.DocTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 文档
 */
public class Doc {
	private Integer id;

	/*'文档名称'*/
	private String name;

	/*'文档说明'*/
	private String des;

	/*'请求地址'*/
	private String url;

	/*'请求参数实例'*/
	@JsonProperty("quest_example")
	private String questExample;

	/*'返回数据实例1'*/
	@JsonProperty("result_example1")
	private String resultExample1;

	/*'返回数据实例2'*/
	@JsonProperty("result_example2")
	private String resultExample2;

	/*文档类型*/
	@JsonProperty("doc_type")
	private DocTypeEnum docType;

	/*文档模块id*/
	@JsonProperty("doc_mode_id")
	private Integer docModeId;


	private List<DocPar> questParList;

	private List<DocPar> resultParList;

	public Integer getDocModeId() {
		return docModeId;
	}

	public void setDocModeId(Integer docModeId) {
		this.docModeId = docModeId;
	}

	public List<DocPar> getQuestParList() {
		return questParList;
	}

	public void setQuestParList(List<DocPar> questParList) {
		this.questParList = questParList;
	}

	public List<DocPar> getResultParList() {
		return resultParList;
	}

	public void setResultParList(List<DocPar> resultParList) {
		this.resultParList = resultParList;
	}

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
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getQuestExample(){
		return questExample;
	}
	public void setQuestExample(String questExample){
		this.questExample=questExample;
	}
	public String getResultExample1(){
		return resultExample1;
	}
	public void setResultExample1(String resultExample1){
		this.resultExample1=resultExample1;
	}
	public String getResultExample2(){
		return resultExample2;
	}
	public void setResultExample2(String resultExample2){
		this.resultExample2=resultExample2;
	}

	public DocTypeEnum getDocType() {
		return docType;
	}

	public void setDocType(DocTypeEnum docType) {
		this.docType = docType;
	}
}
