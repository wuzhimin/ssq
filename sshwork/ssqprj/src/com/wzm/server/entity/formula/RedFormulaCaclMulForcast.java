package com.wzm.server.entity.formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：红球公式计算值正确性多期预测表
 * 
 */

@Entity
@Table(name = "t_redfulcaclmulfst", catalog = "ssq")
public class RedFormulaCaclMulForcast extends BaseEntity {

	private static final long serialVersionUID = -955282653890508744L;

	public RedFormulaCaclMulForcast() {

	}

	private int fromSsqIndex;     // 开始期
	private int toSsqIndex;       // 结束期
	private int targetSsqIndex;   // 目标开奖期号，即预测的那期双色球

	private int spaceNum; // 间隔期数
	private int forcastSpaceNum;  // 预测间隔期数
	
	private int formula1KillRightMaxCount;
	private int formula1KillRightMinCount=100;

	private int formula2KillRightMaxCount;
	private int formula2KillRightMinCount=100;

	private int formula3KillRightMaxCount;
	private int formula3KillRightMinCount=100;

	private int formula4KillRightMaxCount;
	private int formula4KillRightMinCount=100;

	private int formula5KillRightMaxCount;
	private int formula5KillRightMinCount=100;

	private int formula6KillRightMaxCount;
	private int formula6KillRightMinCount=100;

	private int formula7KillRightMaxCount;
	private int formula7KillRightMinCount=100;

	private int formula8KillRightMaxCount;
	private int formula8KillRightMinCount=100;

	private int formula9KillRightMaxCount;
	private int formula9KillRightMinCount=100;

	private int formula10KillRightMaxCount;
	private int formula10KillRightMinCount=100;

	private int formula11KillRightMaxCount;
	private int formula11KillRightMinCount=100;

	private int formula12KillRightMaxCount;
	private int formula12KillRightMinCount=100;

	private int formula13KillRightMaxCount;
	private int formula13KillRightMinCount=100;

	private int formula14KillRightMaxCount;
	private int formula14KillRightMinCount=100;

	private int formula15KillRightMaxCount;
	private int formula15KillRightMinCount=100;

	private int formula16KillRightMaxCount;
	private int formula16KillRightMinCount=100;

	private int formula17KillRightMaxCount;
	private int formula17KillRightMinCount=100;

	private int formula18KillRightMaxCount;
	private int formula18KillRightMinCount=100;

	private int formula19KillRightMaxCount;
	private int formula19KillRightMinCount=100;

	private int formula20KillRightMaxCount;
	private int formula20KillRightMinCount=100;

	private int formula21KillRightMaxCount;
	private int formula21KillRightMinCount=100;

	private int formula22KillRightMaxCount;
	private int formula22KillRightMinCount=100;

	private int formula23KillRightMaxCount;
	private int formula23KillRightMinCount=100;

	private int formula24KillRightMaxCount;
	private int formula24KillRightMinCount=100;

	private int formula25KillRightMaxCount;
	private int formula25KillRightMinCount=100;

	private int formula26KillRightMaxCount;
	private int formula26KillRightMinCount=100;

	private int formula27KillRightMaxCount;
	private int formula27KillRightMinCount=100;

	private int formula28KillRightMaxCount;
	private int formula28KillRightMinCount=100;

	private int formula29KillRightMaxCount;
	private int formula29KillRightMinCount=100;

	private int formula30KillRightMaxCount;
	private int formula30KillRightMinCount=100;

	private int formula31KillRightMaxCount;
	private int formula31KillRightMinCount=100;

	private int formula32KillRightMaxCount;
	private int formula32KillRightMinCount=100;

	private int formula33KillRightMaxCount;
	private int formula33KillRightMinCount=100;

	private int formula34KillRightMaxCount;
	private int formula34KillRightMinCount=100;

	private int formula35KillRightMaxCount;
	private int formula35KillRightMinCount=100;

	private int formula36KillRightMaxCount;
	private int formula36KillRightMinCount=100;

	private int formula37KillRightMaxCount;
	private int formula37KillRightMinCount=100;

	private int formula38KillRightMaxCount;
	private int formula38KillRightMinCount=100;

	private int formula39KillRightMaxCount;
	private int formula39KillRightMinCount=100;

	private int formula40KillRightMaxCount;
	private int formula40KillRightMinCount=100;

	private int formula41KillRightMaxCount;
	private int formula41KillRightMinCount=100;

	private int formula42KillRightMaxCount;
	private int formula42KillRightMinCount=100;

	private int formula43KillRightMaxCount;
	private int formula43KillRightMinCount=100;

	private int formula44KillRightMaxCount;
	private int formula44KillRightMinCount=100;

	private int formula45KillRightMaxCount;
	private int formula45KillRightMinCount=100;

	private int formula46KillRightMaxCount;
	private int formula46KillRightMinCount=100;

	private int formula47KillRightMaxCount;
	private int formula47KillRightMinCount=100;

	private int formula48KillRightMaxCount;
	private int formula48KillRightMinCount=100;

	private int formula49KillRightMaxCount;
	private int formula49KillRightMinCount=100;

	private int formula50KillRightMaxCount;
	private int formula50KillRightMinCount=100;

	private int formula51KillRightMaxCount;
	private int formula51KillRightMinCount=100;

	private int formula52KillRightMaxCount;
	private int formula52KillRightMinCount=100;

	private int formula53KillRightMaxCount;
	private int formula53KillRightMinCount=100;

	private int formula54KillRightMaxCount;
	private int formula54KillRightMinCount=100;

	private int formula55KillRightMaxCount;
	private int formula55KillRightMinCount=100;

	private int formula56KillRightMaxCount;
	private int formula56KillRightMinCount=100;

	private int formula57KillRightMaxCount;
	private int formula57KillRightMinCount=100;

	private int formula58KillRightMaxCount;
	private int formula58KillRightMinCount=100;

	private int formula59KillRightMaxCount;
	private int formula59KillRightMinCount=100;

	private int formula60KillRightMaxCount;
	private int formula60KillRightMinCount=100;

	private int formula61KillRightMaxCount;
	private int formula61KillRightMinCount=100;

	private int formula62KillRightMaxCount;
	private int formula62KillRightMinCount=100;

	private int formula63KillRightMaxCount;
	private int formula63KillRightMinCount=100;

	private int formula64KillRightMaxCount;
	private int formula64KillRightMinCount=100;

	private int formula65KillRightMaxCount;
	private int formula65KillRightMinCount=100;

	private int formula66KillRightMaxCount;
	private int formula66KillRightMinCount=100;

	private int formula67KillRightMaxCount;
	private int formula67KillRightMinCount=100;

	private int formula68KillRightMaxCount;
	private int formula68KillRightMinCount=100;

	private int formula69KillRightMaxCount;
	private int formula69KillRightMinCount=100;

	private int formula70KillRightMaxCount;
	private int formula70KillRightMinCount=100;

	private int formula71KillRightMaxCount;
	private int formula71KillRightMinCount=100;

	private int formula72KillRightMaxCount;
	private int formula72KillRightMinCount=100;

	private int formula73KillRightMaxCount;
	private int formula73KillRightMinCount=100;

	private int formula74KillRightMaxCount;
	private int formula74KillRightMinCount=100;

	private int formula75KillRightMaxCount;
	private int formula75KillRightMinCount=100;

	private int formula76KillRightMaxCount;
	private int formula76KillRightMinCount=100;

	private int formula77KillRightMaxCount;
	private int formula77KillRightMinCount=100;

	private int formula78KillRightMaxCount;
	private int formula78KillRightMinCount=100;

	private int formula79KillRightMaxCount;
	private int formula79KillRightMinCount=100;

	private int formula80KillRightMaxCount;
	private int formula80KillRightMinCount=100;

	private int formula81KillRightMaxCount;
	private int formula81KillRightMinCount=100;

	private int formula82KillRightMaxCount;
	private int formula82KillRightMinCount=100;

	private int formula83KillRightMaxCount;
	private int formula83KillRightMinCount=100;

	private int formula84KillRightMaxCount;
	private int formula84KillRightMinCount=100;

	private int formula85KillRightMaxCount;
	private int formula85KillRightMinCount=100;

	private int formula86KillRightMaxCount;
	private int formula86KillRightMinCount=100;

	private int formula87KillRightMaxCount;
	private int formula87KillRightMinCount=100;

	private int formula88KillRightMaxCount;
	private int formula88KillRightMinCount=100;

	private int formula89KillRightMaxCount;
	private int formula89KillRightMinCount=100;

	private int formula90KillRightMaxCount;
	private int formula90KillRightMinCount=100;

	private int formula91KillRightMaxCount;
	private int formula91KillRightMinCount=100;

	private String careNums;   // 关注的
	private String careFormulas;   // 关注的公式名
	
	private String careNumsResult;   // 开奖后，关注的正确性
	
	private String killNums;   // 杀的
	private String killFormulas;   // 杀的公式名
	private String killNumsResult;   // 开奖后，杀的正确性
	private String killErrorFormulas;
	
	private String selectNums;   // 定选的
	private String selectFormulas;   // 选的公式名
	private String selectNumsResult;   // 开奖后,定选的正确性
	private String selectRightFormulas;
	
	private boolean verified = false;
	
	
	@Column(name = "ffromssqindex", nullable = false)
	public int getFromSsqIndex() {
		return fromSsqIndex;
	}

	public void setFromSsqIndex(int fromSsqIndex) {
		this.fromSsqIndex = fromSsqIndex;
	}

	@Column(name = "ftossqindex", nullable = false)
	public int getToSsqIndex() {
		return toSsqIndex;
	}

	public void setToSsqIndex(int toSsqIndex) {
		this.toSsqIndex = toSsqIndex;
	}

	@Column(name = "ftargetssqindex", nullable = false)
	public int getTargetSsqIndex() {
		return targetSsqIndex;
	}

	public void setTargetSsqIndex(int targetSsqIndex) {
		this.targetSsqIndex = targetSsqIndex;
	}

	@Column(name = "fforcastspacenum", nullable = false)
	public int getForcastSpaceNum() {
		return forcastSpaceNum;
	}

	public void setForcastSpaceNum(int forcastSpaceNum) {
		this.forcastSpaceNum = forcastSpaceNum;
	}
	
	@Column(name = "fspacenum", nullable = false)
	public int getSpaceNum() {
		return spaceNum;
	}

	public void setSpaceNum(int spaceNum) {
		this.spaceNum = spaceNum;
	}

	@Column(name = "fformula1KRMaxCount", nullable = false)
	public int getFormula1KillRightMaxCount() {
	return formula1KillRightMaxCount;
	}

	public void setFormula1KillRightMaxCount(int formula1KillRightMaxCount) {
	this.formula1KillRightMaxCount = formula1KillRightMaxCount;
	}


	@Column(name = "fformula1KRMinCount", nullable = false)
	public int getFormula1KillRightMinCount() {
	return formula1KillRightMinCount;
	}

	public void setFormula1KillRightMinCount(int formula1KillRightMinCount) {
	this.formula1KillRightMinCount = formula1KillRightMinCount;
	}

	@Column(name = "fformula2KRMaxCount", nullable = false)
	public int getFormula2KillRightMaxCount() {
	return formula2KillRightMaxCount;
	}

	public void setFormula2KillRightMaxCount(int formula2KillRightMaxCount) {
	this.formula2KillRightMaxCount = formula2KillRightMaxCount;
	}


	@Column(name = "fformula2KRMinCount", nullable = false)
	public int getFormula2KillRightMinCount() {
	return formula2KillRightMinCount;
	}

	public void setFormula2KillRightMinCount(int formula2KillRightMinCount) {
	this.formula2KillRightMinCount = formula2KillRightMinCount;
	}

	@Column(name = "fformula3KRMaxCount", nullable = false)
	public int getFormula3KillRightMaxCount() {
	return formula3KillRightMaxCount;
	}

	public void setFormula3KillRightMaxCount(int formula3KillRightMaxCount) {
	this.formula3KillRightMaxCount = formula3KillRightMaxCount;
	}


	@Column(name = "fformula3KRMinCount", nullable = false)
	public int getFormula3KillRightMinCount() {
	return formula3KillRightMinCount;
	}

	public void setFormula3KillRightMinCount(int formula3KillRightMinCount) {
	this.formula3KillRightMinCount = formula3KillRightMinCount;
	}

	@Column(name = "fformula4KRMaxCount", nullable = false)
	public int getFormula4KillRightMaxCount() {
	return formula4KillRightMaxCount;
	}

	public void setFormula4KillRightMaxCount(int formula4KillRightMaxCount) {
	this.formula4KillRightMaxCount = formula4KillRightMaxCount;
	}


	@Column(name = "fformula4KRMinCount", nullable = false)
	public int getFormula4KillRightMinCount() {
	return formula4KillRightMinCount;
	}

	public void setFormula4KillRightMinCount(int formula4KillRightMinCount) {
	this.formula4KillRightMinCount = formula4KillRightMinCount;
	}

	@Column(name = "fformula5KRMaxCount", nullable = false)
	public int getFormula5KillRightMaxCount() {
	return formula5KillRightMaxCount;
	}

	public void setFormula5KillRightMaxCount(int formula5KillRightMaxCount) {
	this.formula5KillRightMaxCount = formula5KillRightMaxCount;
	}


	@Column(name = "fformula5KRMinCount", nullable = false)
	public int getFormula5KillRightMinCount() {
	return formula5KillRightMinCount;
	}

	public void setFormula5KillRightMinCount(int formula5KillRightMinCount) {
	this.formula5KillRightMinCount = formula5KillRightMinCount;
	}

	@Column(name = "fformula6KRMaxCount", nullable = false)
	public int getFormula6KillRightMaxCount() {
	return formula6KillRightMaxCount;
	}

	public void setFormula6KillRightMaxCount(int formula6KillRightMaxCount) {
	this.formula6KillRightMaxCount = formula6KillRightMaxCount;
	}


	@Column(name = "fformula6KRMinCount", nullable = false)
	public int getFormula6KillRightMinCount() {
	return formula6KillRightMinCount;
	}

	public void setFormula6KillRightMinCount(int formula6KillRightMinCount) {
	this.formula6KillRightMinCount = formula6KillRightMinCount;
	}

	@Column(name = "fformula7KRMaxCount", nullable = false)
	public int getFormula7KillRightMaxCount() {
	return formula7KillRightMaxCount;
	}

	public void setFormula7KillRightMaxCount(int formula7KillRightMaxCount) {
	this.formula7KillRightMaxCount = formula7KillRightMaxCount;
	}


	@Column(name = "fformula7KRMinCount", nullable = false)
	public int getFormula7KillRightMinCount() {
	return formula7KillRightMinCount;
	}

	public void setFormula7KillRightMinCount(int formula7KillRightMinCount) {
	this.formula7KillRightMinCount = formula7KillRightMinCount;
	}

	@Column(name = "fformula8KRMaxCount", nullable = false)
	public int getFormula8KillRightMaxCount() {
	return formula8KillRightMaxCount;
	}

	public void setFormula8KillRightMaxCount(int formula8KillRightMaxCount) {
	this.formula8KillRightMaxCount = formula8KillRightMaxCount;
	}


	@Column(name = "fformula8KRMinCount", nullable = false)
	public int getFormula8KillRightMinCount() {
	return formula8KillRightMinCount;
	}

	public void setFormula8KillRightMinCount(int formula8KillRightMinCount) {
	this.formula8KillRightMinCount = formula8KillRightMinCount;
	}

	@Column(name = "fformula9KRMaxCount", nullable = false)
	public int getFormula9KillRightMaxCount() {
	return formula9KillRightMaxCount;
	}

	public void setFormula9KillRightMaxCount(int formula9KillRightMaxCount) {
	this.formula9KillRightMaxCount = formula9KillRightMaxCount;
	}


	@Column(name = "fformula9KRMinCount", nullable = false)
	public int getFormula9KillRightMinCount() {
	return formula9KillRightMinCount;
	}

	public void setFormula9KillRightMinCount(int formula9KillRightMinCount) {
	this.formula9KillRightMinCount = formula9KillRightMinCount;
	}

	@Column(name = "fformula10KRMaxCount", nullable = false)
	public int getFormula10KillRightMaxCount() {
	return formula10KillRightMaxCount;
	}

	public void setFormula10KillRightMaxCount(int formula10KillRightMaxCount) {
	this.formula10KillRightMaxCount = formula10KillRightMaxCount;
	}


	@Column(name = "fformula10KRMinCount", nullable = false)
	public int getFormula10KillRightMinCount() {
	return formula10KillRightMinCount;
	}

	public void setFormula10KillRightMinCount(int formula10KillRightMinCount) {
	this.formula10KillRightMinCount = formula10KillRightMinCount;
	}

	@Column(name = "fformula11KRMaxCount", nullable = false)
	public int getFormula11KillRightMaxCount() {
	return formula11KillRightMaxCount;
	}

	public void setFormula11KillRightMaxCount(int formula11KillRightMaxCount) {
	this.formula11KillRightMaxCount = formula11KillRightMaxCount;
	}


	@Column(name = "fformula11KRMinCount", nullable = false)
	public int getFormula11KillRightMinCount() {
	return formula11KillRightMinCount;
	}

	public void setFormula11KillRightMinCount(int formula11KillRightMinCount) {
	this.formula11KillRightMinCount = formula11KillRightMinCount;
	}

	@Column(name = "fformula12KRMaxCount", nullable = false)
	public int getFormula12KillRightMaxCount() {
	return formula12KillRightMaxCount;
	}

	public void setFormula12KillRightMaxCount(int formula12KillRightMaxCount) {
	this.formula12KillRightMaxCount = formula12KillRightMaxCount;
	}


	@Column(name = "fformula12KRMinCount", nullable = false)
	public int getFormula12KillRightMinCount() {
	return formula12KillRightMinCount;
	}

	public void setFormula12KillRightMinCount(int formula12KillRightMinCount) {
	this.formula12KillRightMinCount = formula12KillRightMinCount;
	}

	@Column(name = "fformula13KRMaxCount", nullable = false)
	public int getFormula13KillRightMaxCount() {
	return formula13KillRightMaxCount;
	}

	public void setFormula13KillRightMaxCount(int formula13KillRightMaxCount) {
	this.formula13KillRightMaxCount = formula13KillRightMaxCount;
	}


	@Column(name = "fformula13KRMinCount", nullable = false)
	public int getFormula13KillRightMinCount() {
	return formula13KillRightMinCount;
	}

	public void setFormula13KillRightMinCount(int formula13KillRightMinCount) {
	this.formula13KillRightMinCount = formula13KillRightMinCount;
	}

	@Column(name = "fformula14KRMaxCount", nullable = false)
	public int getFormula14KillRightMaxCount() {
	return formula14KillRightMaxCount;
	}

	public void setFormula14KillRightMaxCount(int formula14KillRightMaxCount) {
	this.formula14KillRightMaxCount = formula14KillRightMaxCount;
	}


	@Column(name = "fformula14KRMinCount", nullable = false)
	public int getFormula14KillRightMinCount() {
	return formula14KillRightMinCount;
	}

	public void setFormula14KillRightMinCount(int formula14KillRightMinCount) {
	this.formula14KillRightMinCount = formula14KillRightMinCount;
	}

	@Column(name = "fformula15KRMaxCount", nullable = false)
	public int getFormula15KillRightMaxCount() {
	return formula15KillRightMaxCount;
	}

	public void setFormula15KillRightMaxCount(int formula15KillRightMaxCount) {
	this.formula15KillRightMaxCount = formula15KillRightMaxCount;
	}


	@Column(name = "fformula15KRMinCount", nullable = false)
	public int getFormula15KillRightMinCount() {
	return formula15KillRightMinCount;
	}

	public void setFormula15KillRightMinCount(int formula15KillRightMinCount) {
	this.formula15KillRightMinCount = formula15KillRightMinCount;
	}

	@Column(name = "fformula16KRMaxCount", nullable = false)
	public int getFormula16KillRightMaxCount() {
	return formula16KillRightMaxCount;
	}

	public void setFormula16KillRightMaxCount(int formula16KillRightMaxCount) {
	this.formula16KillRightMaxCount = formula16KillRightMaxCount;
	}


	@Column(name = "fformula16KRMinCount", nullable = false)
	public int getFormula16KillRightMinCount() {
	return formula16KillRightMinCount;
	}

	public void setFormula16KillRightMinCount(int formula16KillRightMinCount) {
	this.formula16KillRightMinCount = formula16KillRightMinCount;
	}

	@Column(name = "fformula17KRMaxCount", nullable = false)
	public int getFormula17KillRightMaxCount() {
	return formula17KillRightMaxCount;
	}

	public void setFormula17KillRightMaxCount(int formula17KillRightMaxCount) {
	this.formula17KillRightMaxCount = formula17KillRightMaxCount;
	}


	@Column(name = "fformula17KRMinCount", nullable = false)
	public int getFormula17KillRightMinCount() {
	return formula17KillRightMinCount;
	}

	public void setFormula17KillRightMinCount(int formula17KillRightMinCount) {
	this.formula17KillRightMinCount = formula17KillRightMinCount;
	}

	@Column(name = "fformula18KRMaxCount", nullable = false)
	public int getFormula18KillRightMaxCount() {
	return formula18KillRightMaxCount;
	}

	public void setFormula18KillRightMaxCount(int formula18KillRightMaxCount) {
	this.formula18KillRightMaxCount = formula18KillRightMaxCount;
	}


	@Column(name = "fformula18KRMinCount", nullable = false)
	public int getFormula18KillRightMinCount() {
	return formula18KillRightMinCount;
	}

	public void setFormula18KillRightMinCount(int formula18KillRightMinCount) {
	this.formula18KillRightMinCount = formula18KillRightMinCount;
	}

	@Column(name = "fformula19KRMaxCount", nullable = false)
	public int getFormula19KillRightMaxCount() {
	return formula19KillRightMaxCount;
	}

	public void setFormula19KillRightMaxCount(int formula19KillRightMaxCount) {
	this.formula19KillRightMaxCount = formula19KillRightMaxCount;
	}


	@Column(name = "fformula19KRMinCount", nullable = false)
	public int getFormula19KillRightMinCount() {
	return formula19KillRightMinCount;
	}

	public void setFormula19KillRightMinCount(int formula19KillRightMinCount) {
	this.formula19KillRightMinCount = formula19KillRightMinCount;
	}

	@Column(name = "fformula20KRMaxCount", nullable = false)
	public int getFormula20KillRightMaxCount() {
	return formula20KillRightMaxCount;
	}

	public void setFormula20KillRightMaxCount(int formula20KillRightMaxCount) {
	this.formula20KillRightMaxCount = formula20KillRightMaxCount;
	}


	@Column(name = "fformula20KRMinCount", nullable = false)
	public int getFormula20KillRightMinCount() {
	return formula20KillRightMinCount;
	}

	public void setFormula20KillRightMinCount(int formula20KillRightMinCount) {
	this.formula20KillRightMinCount = formula20KillRightMinCount;
	}

	@Column(name = "fformula21KRMaxCount", nullable = false)
	public int getFormula21KillRightMaxCount() {
	return formula21KillRightMaxCount;
	}

	public void setFormula21KillRightMaxCount(int formula21KillRightMaxCount) {
	this.formula21KillRightMaxCount = formula21KillRightMaxCount;
	}


	@Column(name = "fformula21KRMinCount", nullable = false)
	public int getFormula21KillRightMinCount() {
	return formula21KillRightMinCount;
	}

	public void setFormula21KillRightMinCount(int formula21KillRightMinCount) {
	this.formula21KillRightMinCount = formula21KillRightMinCount;
	}

	@Column(name = "fformula22KRMaxCount", nullable = false)
	public int getFormula22KillRightMaxCount() {
	return formula22KillRightMaxCount;
	}

	public void setFormula22KillRightMaxCount(int formula22KillRightMaxCount) {
	this.formula22KillRightMaxCount = formula22KillRightMaxCount;
	}


	@Column(name = "fformula22KRMinCount", nullable = false)
	public int getFormula22KillRightMinCount() {
	return formula22KillRightMinCount;
	}

	public void setFormula22KillRightMinCount(int formula22KillRightMinCount) {
	this.formula22KillRightMinCount = formula22KillRightMinCount;
	}

	@Column(name = "fformula23KRMaxCount", nullable = false)
	public int getFormula23KillRightMaxCount() {
	return formula23KillRightMaxCount;
	}

	public void setFormula23KillRightMaxCount(int formula23KillRightMaxCount) {
	this.formula23KillRightMaxCount = formula23KillRightMaxCount;
	}


	@Column(name = "fformula23KRMinCount", nullable = false)
	public int getFormula23KillRightMinCount() {
	return formula23KillRightMinCount;
	}

	public void setFormula23KillRightMinCount(int formula23KillRightMinCount) {
	this.formula23KillRightMinCount = formula23KillRightMinCount;
	}

	@Column(name = "fformula24KRMaxCount", nullable = false)
	public int getFormula24KillRightMaxCount() {
	return formula24KillRightMaxCount;
	}

	public void setFormula24KillRightMaxCount(int formula24KillRightMaxCount) {
	this.formula24KillRightMaxCount = formula24KillRightMaxCount;
	}


	@Column(name = "fformula24KRMinCount", nullable = false)
	public int getFormula24KillRightMinCount() {
	return formula24KillRightMinCount;
	}

	public void setFormula24KillRightMinCount(int formula24KillRightMinCount) {
	this.formula24KillRightMinCount = formula24KillRightMinCount;
	}

	@Column(name = "fformula25KRMaxCount", nullable = false)
	public int getFormula25KillRightMaxCount() {
	return formula25KillRightMaxCount;
	}

	public void setFormula25KillRightMaxCount(int formula25KillRightMaxCount) {
	this.formula25KillRightMaxCount = formula25KillRightMaxCount;
	}


	@Column(name = "fformula25KRMinCount", nullable = false)
	public int getFormula25KillRightMinCount() {
	return formula25KillRightMinCount;
	}

	public void setFormula25KillRightMinCount(int formula25KillRightMinCount) {
	this.formula25KillRightMinCount = formula25KillRightMinCount;
	}

	@Column(name = "fformula26KRMaxCount", nullable = false)
	public int getFormula26KillRightMaxCount() {
	return formula26KillRightMaxCount;
	}

	public void setFormula26KillRightMaxCount(int formula26KillRightMaxCount) {
	this.formula26KillRightMaxCount = formula26KillRightMaxCount;
	}


	@Column(name = "fformula26KRMinCount", nullable = false)
	public int getFormula26KillRightMinCount() {
	return formula26KillRightMinCount;
	}

	public void setFormula26KillRightMinCount(int formula26KillRightMinCount) {
	this.formula26KillRightMinCount = formula26KillRightMinCount;
	}

	@Column(name = "fformula27KRMaxCount", nullable = false)
	public int getFormula27KillRightMaxCount() {
	return formula27KillRightMaxCount;
	}

	public void setFormula27KillRightMaxCount(int formula27KillRightMaxCount) {
	this.formula27KillRightMaxCount = formula27KillRightMaxCount;
	}


	@Column(name = "fformula27KRMinCount", nullable = false)
	public int getFormula27KillRightMinCount() {
	return formula27KillRightMinCount;
	}

	public void setFormula27KillRightMinCount(int formula27KillRightMinCount) {
	this.formula27KillRightMinCount = formula27KillRightMinCount;
	}

	@Column(name = "fformula28KRMaxCount", nullable = false)
	public int getFormula28KillRightMaxCount() {
	return formula28KillRightMaxCount;
	}

	public void setFormula28KillRightMaxCount(int formula28KillRightMaxCount) {
	this.formula28KillRightMaxCount = formula28KillRightMaxCount;
	}


	@Column(name = "fformula28KRMinCount", nullable = false)
	public int getFormula28KillRightMinCount() {
	return formula28KillRightMinCount;
	}

	public void setFormula28KillRightMinCount(int formula28KillRightMinCount) {
	this.formula28KillRightMinCount = formula28KillRightMinCount;
	}

	@Column(name = "fformula29KRMaxCount", nullable = false)
	public int getFormula29KillRightMaxCount() {
	return formula29KillRightMaxCount;
	}

	public void setFormula29KillRightMaxCount(int formula29KillRightMaxCount) {
	this.formula29KillRightMaxCount = formula29KillRightMaxCount;
	}


	@Column(name = "fformula29KRMinCount", nullable = false)
	public int getFormula29KillRightMinCount() {
	return formula29KillRightMinCount;
	}

	public void setFormula29KillRightMinCount(int formula29KillRightMinCount) {
	this.formula29KillRightMinCount = formula29KillRightMinCount;
	}

	@Column(name = "fformula30KRMaxCount", nullable = false)
	public int getFormula30KillRightMaxCount() {
	return formula30KillRightMaxCount;
	}

	public void setFormula30KillRightMaxCount(int formula30KillRightMaxCount) {
	this.formula30KillRightMaxCount = formula30KillRightMaxCount;
	}


	@Column(name = "fformula30KRMinCount", nullable = false)
	public int getFormula30KillRightMinCount() {
	return formula30KillRightMinCount;
	}

	public void setFormula30KillRightMinCount(int formula30KillRightMinCount) {
	this.formula30KillRightMinCount = formula30KillRightMinCount;
	}

	@Column(name = "fformula31KRMaxCount", nullable = false)
	public int getFormula31KillRightMaxCount() {
	return formula31KillRightMaxCount;
	}

	public void setFormula31KillRightMaxCount(int formula31KillRightMaxCount) {
	this.formula31KillRightMaxCount = formula31KillRightMaxCount;
	}


	@Column(name = "fformula31KRMinCount", nullable = false)
	public int getFormula31KillRightMinCount() {
	return formula31KillRightMinCount;
	}

	public void setFormula31KillRightMinCount(int formula31KillRightMinCount) {
	this.formula31KillRightMinCount = formula31KillRightMinCount;
	}

	@Column(name = "fformula32KRMaxCount", nullable = false)
	public int getFormula32KillRightMaxCount() {
	return formula32KillRightMaxCount;
	}

	public void setFormula32KillRightMaxCount(int formula32KillRightMaxCount) {
	this.formula32KillRightMaxCount = formula32KillRightMaxCount;
	}


	@Column(name = "fformula32KRMinCount", nullable = false)
	public int getFormula32KillRightMinCount() {
	return formula32KillRightMinCount;
	}

	public void setFormula32KillRightMinCount(int formula32KillRightMinCount) {
	this.formula32KillRightMinCount = formula32KillRightMinCount;
	}

	@Column(name = "fformula33KRMaxCount", nullable = false)
	public int getFormula33KillRightMaxCount() {
	return formula33KillRightMaxCount;
	}

	public void setFormula33KillRightMaxCount(int formula33KillRightMaxCount) {
	this.formula33KillRightMaxCount = formula33KillRightMaxCount;
	}


	@Column(name = "fformula33KRMinCount", nullable = false)
	public int getFormula33KillRightMinCount() {
	return formula33KillRightMinCount;
	}

	public void setFormula33KillRightMinCount(int formula33KillRightMinCount) {
	this.formula33KillRightMinCount = formula33KillRightMinCount;
	}

	@Column(name = "fformula34KRMaxCount", nullable = false)
	public int getFormula34KillRightMaxCount() {
	return formula34KillRightMaxCount;
	}

	public void setFormula34KillRightMaxCount(int formula34KillRightMaxCount) {
	this.formula34KillRightMaxCount = formula34KillRightMaxCount;
	}


	@Column(name = "fformula34KRMinCount", nullable = false)
	public int getFormula34KillRightMinCount() {
	return formula34KillRightMinCount;
	}

	public void setFormula34KillRightMinCount(int formula34KillRightMinCount) {
	this.formula34KillRightMinCount = formula34KillRightMinCount;
	}

	@Column(name = "fformula35KRMaxCount", nullable = false)
	public int getFormula35KillRightMaxCount() {
	return formula35KillRightMaxCount;
	}

	public void setFormula35KillRightMaxCount(int formula35KillRightMaxCount) {
	this.formula35KillRightMaxCount = formula35KillRightMaxCount;
	}


	@Column(name = "fformula35KRMinCount", nullable = false)
	public int getFormula35KillRightMinCount() {
	return formula35KillRightMinCount;
	}

	public void setFormula35KillRightMinCount(int formula35KillRightMinCount) {
	this.formula35KillRightMinCount = formula35KillRightMinCount;
	}

	@Column(name = "fformula36KRMaxCount", nullable = false)
	public int getFormula36KillRightMaxCount() {
	return formula36KillRightMaxCount;
	}

	public void setFormula36KillRightMaxCount(int formula36KillRightMaxCount) {
	this.formula36KillRightMaxCount = formula36KillRightMaxCount;
	}


	@Column(name = "fformula36KRMinCount", nullable = false)
	public int getFormula36KillRightMinCount() {
	return formula36KillRightMinCount;
	}

	public void setFormula36KillRightMinCount(int formula36KillRightMinCount) {
	this.formula36KillRightMinCount = formula36KillRightMinCount;
	}

	@Column(name = "fformula37KRMaxCount", nullable = false)
	public int getFormula37KillRightMaxCount() {
	return formula37KillRightMaxCount;
	}

	public void setFormula37KillRightMaxCount(int formula37KillRightMaxCount) {
	this.formula37KillRightMaxCount = formula37KillRightMaxCount;
	}


	@Column(name = "fformula37KRMinCount", nullable = false)
	public int getFormula37KillRightMinCount() {
	return formula37KillRightMinCount;
	}

	public void setFormula37KillRightMinCount(int formula37KillRightMinCount) {
	this.formula37KillRightMinCount = formula37KillRightMinCount;
	}

	@Column(name = "fformula38KRMaxCount", nullable = false)
	public int getFormula38KillRightMaxCount() {
	return formula38KillRightMaxCount;
	}

	public void setFormula38KillRightMaxCount(int formula38KillRightMaxCount) {
	this.formula38KillRightMaxCount = formula38KillRightMaxCount;
	}


	@Column(name = "fformula38KRMinCount", nullable = false)
	public int getFormula38KillRightMinCount() {
	return formula38KillRightMinCount;
	}

	public void setFormula38KillRightMinCount(int formula38KillRightMinCount) {
	this.formula38KillRightMinCount = formula38KillRightMinCount;
	}

	@Column(name = "fformula39KRMaxCount", nullable = false)
	public int getFormula39KillRightMaxCount() {
	return formula39KillRightMaxCount;
	}

	public void setFormula39KillRightMaxCount(int formula39KillRightMaxCount) {
	this.formula39KillRightMaxCount = formula39KillRightMaxCount;
	}


	@Column(name = "fformula39KRMinCount", nullable = false)
	public int getFormula39KillRightMinCount() {
	return formula39KillRightMinCount;
	}

	public void setFormula39KillRightMinCount(int formula39KillRightMinCount) {
	this.formula39KillRightMinCount = formula39KillRightMinCount;
	}

	@Column(name = "fformula40KRMaxCount", nullable = false)
	public int getFormula40KillRightMaxCount() {
	return formula40KillRightMaxCount;
	}

	public void setFormula40KillRightMaxCount(int formula40KillRightMaxCount) {
	this.formula40KillRightMaxCount = formula40KillRightMaxCount;
	}


	@Column(name = "fformula40KRMinCount", nullable = false)
	public int getFormula40KillRightMinCount() {
	return formula40KillRightMinCount;
	}

	public void setFormula40KillRightMinCount(int formula40KillRightMinCount) {
	this.formula40KillRightMinCount = formula40KillRightMinCount;
	}

	@Column(name = "fformula41KRMaxCount", nullable = false)
	public int getFormula41KillRightMaxCount() {
	return formula41KillRightMaxCount;
	}

	public void setFormula41KillRightMaxCount(int formula41KillRightMaxCount) {
	this.formula41KillRightMaxCount = formula41KillRightMaxCount;
	}


	@Column(name = "fformula41KRMinCount", nullable = false)
	public int getFormula41KillRightMinCount() {
	return formula41KillRightMinCount;
	}

	public void setFormula41KillRightMinCount(int formula41KillRightMinCount) {
	this.formula41KillRightMinCount = formula41KillRightMinCount;
	}

	@Column(name = "fformula42KRMaxCount", nullable = false)
	public int getFormula42KillRightMaxCount() {
	return formula42KillRightMaxCount;
	}

	public void setFormula42KillRightMaxCount(int formula42KillRightMaxCount) {
	this.formula42KillRightMaxCount = formula42KillRightMaxCount;
	}


	@Column(name = "fformula42KRMinCount", nullable = false)
	public int getFormula42KillRightMinCount() {
	return formula42KillRightMinCount;
	}

	public void setFormula42KillRightMinCount(int formula42KillRightMinCount) {
	this.formula42KillRightMinCount = formula42KillRightMinCount;
	}

	@Column(name = "fformula43KRMaxCount", nullable = false)
	public int getFormula43KillRightMaxCount() {
	return formula43KillRightMaxCount;
	}

	public void setFormula43KillRightMaxCount(int formula43KillRightMaxCount) {
	this.formula43KillRightMaxCount = formula43KillRightMaxCount;
	}


	@Column(name = "fformula43KRMinCount", nullable = false)
	public int getFormula43KillRightMinCount() {
	return formula43KillRightMinCount;
	}

	public void setFormula43KillRightMinCount(int formula43KillRightMinCount) {
	this.formula43KillRightMinCount = formula43KillRightMinCount;
	}

	@Column(name = "fformula44KRMaxCount", nullable = false)
	public int getFormula44KillRightMaxCount() {
	return formula44KillRightMaxCount;
	}

	public void setFormula44KillRightMaxCount(int formula44KillRightMaxCount) {
	this.formula44KillRightMaxCount = formula44KillRightMaxCount;
	}


	@Column(name = "fformula44KRMinCount", nullable = false)
	public int getFormula44KillRightMinCount() {
	return formula44KillRightMinCount;
	}

	public void setFormula44KillRightMinCount(int formula44KillRightMinCount) {
	this.formula44KillRightMinCount = formula44KillRightMinCount;
	}

	@Column(name = "fformula45KRMaxCount", nullable = false)
	public int getFormula45KillRightMaxCount() {
	return formula45KillRightMaxCount;
	}

	public void setFormula45KillRightMaxCount(int formula45KillRightMaxCount) {
	this.formula45KillRightMaxCount = formula45KillRightMaxCount;
	}


	@Column(name = "fformula45KRMinCount", nullable = false)
	public int getFormula45KillRightMinCount() {
	return formula45KillRightMinCount;
	}

	public void setFormula45KillRightMinCount(int formula45KillRightMinCount) {
	this.formula45KillRightMinCount = formula45KillRightMinCount;
	}

	@Column(name = "fformula46KRMaxCount", nullable = false)
	public int getFormula46KillRightMaxCount() {
	return formula46KillRightMaxCount;
	}

	public void setFormula46KillRightMaxCount(int formula46KillRightMaxCount) {
	this.formula46KillRightMaxCount = formula46KillRightMaxCount;
	}


	@Column(name = "fformula46KRMinCount", nullable = false)
	public int getFormula46KillRightMinCount() {
	return formula46KillRightMinCount;
	}

	public void setFormula46KillRightMinCount(int formula46KillRightMinCount) {
	this.formula46KillRightMinCount = formula46KillRightMinCount;
	}

	@Column(name = "fformula47KRMaxCount", nullable = false)
	public int getFormula47KillRightMaxCount() {
	return formula47KillRightMaxCount;
	}

	public void setFormula47KillRightMaxCount(int formula47KillRightMaxCount) {
	this.formula47KillRightMaxCount = formula47KillRightMaxCount;
	}


	@Column(name = "fformula47KRMinCount", nullable = false)
	public int getFormula47KillRightMinCount() {
	return formula47KillRightMinCount;
	}

	public void setFormula47KillRightMinCount(int formula47KillRightMinCount) {
	this.formula47KillRightMinCount = formula47KillRightMinCount;
	}

	@Column(name = "fformula48KRMaxCount", nullable = false)
	public int getFormula48KillRightMaxCount() {
	return formula48KillRightMaxCount;
	}

	public void setFormula48KillRightMaxCount(int formula48KillRightMaxCount) {
	this.formula48KillRightMaxCount = formula48KillRightMaxCount;
	}


	@Column(name = "fformula48KRMinCount", nullable = false)
	public int getFormula48KillRightMinCount() {
	return formula48KillRightMinCount;
	}

	public void setFormula48KillRightMinCount(int formula48KillRightMinCount) {
	this.formula48KillRightMinCount = formula48KillRightMinCount;
	}

	@Column(name = "fformula49KRMaxCount", nullable = false)
	public int getFormula49KillRightMaxCount() {
	return formula49KillRightMaxCount;
	}

	public void setFormula49KillRightMaxCount(int formula49KillRightMaxCount) {
	this.formula49KillRightMaxCount = formula49KillRightMaxCount;
	}


	@Column(name = "fformula49KRMinCount", nullable = false)
	public int getFormula49KillRightMinCount() {
	return formula49KillRightMinCount;
	}

	public void setFormula49KillRightMinCount(int formula49KillRightMinCount) {
	this.formula49KillRightMinCount = formula49KillRightMinCount;
	}

	@Column(name = "fformula50KRMaxCount", nullable = false)
	public int getFormula50KillRightMaxCount() {
	return formula50KillRightMaxCount;
	}

	public void setFormula50KillRightMaxCount(int formula50KillRightMaxCount) {
	this.formula50KillRightMaxCount = formula50KillRightMaxCount;
	}


	@Column(name = "fformula50KRMinCount", nullable = false)
	public int getFormula50KillRightMinCount() {
	return formula50KillRightMinCount;
	}

	public void setFormula50KillRightMinCount(int formula50KillRightMinCount) {
	this.formula50KillRightMinCount = formula50KillRightMinCount;
	}

	@Column(name = "fformula51KRMaxCount", nullable = false)
	public int getFormula51KillRightMaxCount() {
	return formula51KillRightMaxCount;
	}

	public void setFormula51KillRightMaxCount(int formula51KillRightMaxCount) {
	this.formula51KillRightMaxCount = formula51KillRightMaxCount;
	}


	@Column(name = "fformula51KRMinCount", nullable = false)
	public int getFormula51KillRightMinCount() {
	return formula51KillRightMinCount;
	}

	public void setFormula51KillRightMinCount(int formula51KillRightMinCount) {
	this.formula51KillRightMinCount = formula51KillRightMinCount;
	}

	@Column(name = "fformula52KRMaxCount", nullable = false)
	public int getFormula52KillRightMaxCount() {
	return formula52KillRightMaxCount;
	}

	public void setFormula52KillRightMaxCount(int formula52KillRightMaxCount) {
	this.formula52KillRightMaxCount = formula52KillRightMaxCount;
	}


	@Column(name = "fformula52KRMinCount", nullable = false)
	public int getFormula52KillRightMinCount() {
	return formula52KillRightMinCount;
	}

	public void setFormula52KillRightMinCount(int formula52KillRightMinCount) {
	this.formula52KillRightMinCount = formula52KillRightMinCount;
	}

	@Column(name = "fformula53KRMaxCount", nullable = false)
	public int getFormula53KillRightMaxCount() {
	return formula53KillRightMaxCount;
	}

	public void setFormula53KillRightMaxCount(int formula53KillRightMaxCount) {
	this.formula53KillRightMaxCount = formula53KillRightMaxCount;
	}


	@Column(name = "fformula53KRMinCount", nullable = false)
	public int getFormula53KillRightMinCount() {
	return formula53KillRightMinCount;
	}

	public void setFormula53KillRightMinCount(int formula53KillRightMinCount) {
	this.formula53KillRightMinCount = formula53KillRightMinCount;
	}

	@Column(name = "fformula54KRMaxCount", nullable = false)
	public int getFormula54KillRightMaxCount() {
	return formula54KillRightMaxCount;
	}

	public void setFormula54KillRightMaxCount(int formula54KillRightMaxCount) {
	this.formula54KillRightMaxCount = formula54KillRightMaxCount;
	}


	@Column(name = "fformula54KRMinCount", nullable = false)
	public int getFormula54KillRightMinCount() {
	return formula54KillRightMinCount;
	}

	public void setFormula54KillRightMinCount(int formula54KillRightMinCount) {
	this.formula54KillRightMinCount = formula54KillRightMinCount;
	}

	@Column(name = "fformula55KRMaxCount", nullable = false)
	public int getFormula55KillRightMaxCount() {
	return formula55KillRightMaxCount;
	}

	public void setFormula55KillRightMaxCount(int formula55KillRightMaxCount) {
	this.formula55KillRightMaxCount = formula55KillRightMaxCount;
	}


	@Column(name = "fformula55KRMinCount", nullable = false)
	public int getFormula55KillRightMinCount() {
	return formula55KillRightMinCount;
	}

	public void setFormula55KillRightMinCount(int formula55KillRightMinCount) {
	this.formula55KillRightMinCount = formula55KillRightMinCount;
	}

	@Column(name = "fformula56KRMaxCount", nullable = false)
	public int getFormula56KillRightMaxCount() {
	return formula56KillRightMaxCount;
	}

	public void setFormula56KillRightMaxCount(int formula56KillRightMaxCount) {
	this.formula56KillRightMaxCount = formula56KillRightMaxCount;
	}


	@Column(name = "fformula56KRMinCount", nullable = false)
	public int getFormula56KillRightMinCount() {
	return formula56KillRightMinCount;
	}

	public void setFormula56KillRightMinCount(int formula56KillRightMinCount) {
	this.formula56KillRightMinCount = formula56KillRightMinCount;
	}

	@Column(name = "fformula57KRMaxCount", nullable = false)
	public int getFormula57KillRightMaxCount() {
	return formula57KillRightMaxCount;
	}

	public void setFormula57KillRightMaxCount(int formula57KillRightMaxCount) {
	this.formula57KillRightMaxCount = formula57KillRightMaxCount;
	}


	@Column(name = "fformula57KRMinCount", nullable = false)
	public int getFormula57KillRightMinCount() {
	return formula57KillRightMinCount;
	}

	public void setFormula57KillRightMinCount(int formula57KillRightMinCount) {
	this.formula57KillRightMinCount = formula57KillRightMinCount;
	}

	@Column(name = "fformula58KRMaxCount", nullable = false)
	public int getFormula58KillRightMaxCount() {
	return formula58KillRightMaxCount;
	}

	public void setFormula58KillRightMaxCount(int formula58KillRightMaxCount) {
	this.formula58KillRightMaxCount = formula58KillRightMaxCount;
	}


	@Column(name = "fformula58KRMinCount", nullable = false)
	public int getFormula58KillRightMinCount() {
	return formula58KillRightMinCount;
	}

	public void setFormula58KillRightMinCount(int formula58KillRightMinCount) {
	this.formula58KillRightMinCount = formula58KillRightMinCount;
	}

	@Column(name = "fformula59KRMaxCount", nullable = false)
	public int getFormula59KillRightMaxCount() {
	return formula59KillRightMaxCount;
	}

	public void setFormula59KillRightMaxCount(int formula59KillRightMaxCount) {
	this.formula59KillRightMaxCount = formula59KillRightMaxCount;
	}


	@Column(name = "fformula59KRMinCount", nullable = false)
	public int getFormula59KillRightMinCount() {
	return formula59KillRightMinCount;
	}

	public void setFormula59KillRightMinCount(int formula59KillRightMinCount) {
	this.formula59KillRightMinCount = formula59KillRightMinCount;
	}

	@Column(name = "fformula60KRMaxCount", nullable = false)
	public int getFormula60KillRightMaxCount() {
	return formula60KillRightMaxCount;
	}

	public void setFormula60KillRightMaxCount(int formula60KillRightMaxCount) {
	this.formula60KillRightMaxCount = formula60KillRightMaxCount;
	}


	@Column(name = "fformula60KRMinCount", nullable = false)
	public int getFormula60KillRightMinCount() {
	return formula60KillRightMinCount;
	}

	public void setFormula60KillRightMinCount(int formula60KillRightMinCount) {
	this.formula60KillRightMinCount = formula60KillRightMinCount;
	}

	@Column(name = "fformula61KRMaxCount", nullable = false)
	public int getFormula61KillRightMaxCount() {
	return formula61KillRightMaxCount;
	}

	public void setFormula61KillRightMaxCount(int formula61KillRightMaxCount) {
	this.formula61KillRightMaxCount = formula61KillRightMaxCount;
	}


	@Column(name = "fformula61KRMinCount", nullable = false)
	public int getFormula61KillRightMinCount() {
	return formula61KillRightMinCount;
	}

	public void setFormula61KillRightMinCount(int formula61KillRightMinCount) {
	this.formula61KillRightMinCount = formula61KillRightMinCount;
	}

	@Column(name = "fformula62KRMaxCount", nullable = false)
	public int getFormula62KillRightMaxCount() {
	return formula62KillRightMaxCount;
	}

	public void setFormula62KillRightMaxCount(int formula62KillRightMaxCount) {
	this.formula62KillRightMaxCount = formula62KillRightMaxCount;
	}


	@Column(name = "fformula62KRMinCount", nullable = false)
	public int getFormula62KillRightMinCount() {
	return formula62KillRightMinCount;
	}

	public void setFormula62KillRightMinCount(int formula62KillRightMinCount) {
	this.formula62KillRightMinCount = formula62KillRightMinCount;
	}

	@Column(name = "fformula63KRMaxCount", nullable = false)
	public int getFormula63KillRightMaxCount() {
	return formula63KillRightMaxCount;
	}

	public void setFormula63KillRightMaxCount(int formula63KillRightMaxCount) {
	this.formula63KillRightMaxCount = formula63KillRightMaxCount;
	}


	@Column(name = "fformula63KRMinCount", nullable = false)
	public int getFormula63KillRightMinCount() {
	return formula63KillRightMinCount;
	}

	public void setFormula63KillRightMinCount(int formula63KillRightMinCount) {
	this.formula63KillRightMinCount = formula63KillRightMinCount;
	}

	@Column(name = "fformula64KRMaxCount", nullable = false)
	public int getFormula64KillRightMaxCount() {
	return formula64KillRightMaxCount;
	}

	public void setFormula64KillRightMaxCount(int formula64KillRightMaxCount) {
	this.formula64KillRightMaxCount = formula64KillRightMaxCount;
	}


	@Column(name = "fformula64KRMinCount", nullable = false)
	public int getFormula64KillRightMinCount() {
	return formula64KillRightMinCount;
	}

	public void setFormula64KillRightMinCount(int formula64KillRightMinCount) {
	this.formula64KillRightMinCount = formula64KillRightMinCount;
	}

	@Column(name = "fformula65KRMaxCount", nullable = false)
	public int getFormula65KillRightMaxCount() {
	return formula65KillRightMaxCount;
	}

	public void setFormula65KillRightMaxCount(int formula65KillRightMaxCount) {
	this.formula65KillRightMaxCount = formula65KillRightMaxCount;
	}


	@Column(name = "fformula65KRMinCount", nullable = false)
	public int getFormula65KillRightMinCount() {
	return formula65KillRightMinCount;
	}

	public void setFormula65KillRightMinCount(int formula65KillRightMinCount) {
	this.formula65KillRightMinCount = formula65KillRightMinCount;
	}

	@Column(name = "fformula66KRMaxCount", nullable = false)
	public int getFormula66KillRightMaxCount() {
	return formula66KillRightMaxCount;
	}

	public void setFormula66KillRightMaxCount(int formula66KillRightMaxCount) {
	this.formula66KillRightMaxCount = formula66KillRightMaxCount;
	}


	@Column(name = "fformula66KRMinCount", nullable = false)
	public int getFormula66KillRightMinCount() {
	return formula66KillRightMinCount;
	}

	public void setFormula66KillRightMinCount(int formula66KillRightMinCount) {
	this.formula66KillRightMinCount = formula66KillRightMinCount;
	}

	@Column(name = "fformula67KRMaxCount", nullable = false)
	public int getFormula67KillRightMaxCount() {
	return formula67KillRightMaxCount;
	}

	public void setFormula67KillRightMaxCount(int formula67KillRightMaxCount) {
	this.formula67KillRightMaxCount = formula67KillRightMaxCount;
	}


	@Column(name = "fformula67KRMinCount", nullable = false)
	public int getFormula67KillRightMinCount() {
	return formula67KillRightMinCount;
	}

	public void setFormula67KillRightMinCount(int formula67KillRightMinCount) {
	this.formula67KillRightMinCount = formula67KillRightMinCount;
	}

	@Column(name = "fformula68KRMaxCount", nullable = false)
	public int getFormula68KillRightMaxCount() {
	return formula68KillRightMaxCount;
	}

	public void setFormula68KillRightMaxCount(int formula68KillRightMaxCount) {
	this.formula68KillRightMaxCount = formula68KillRightMaxCount;
	}


	@Column(name = "fformula68KRMinCount", nullable = false)
	public int getFormula68KillRightMinCount() {
	return formula68KillRightMinCount;
	}

	public void setFormula68KillRightMinCount(int formula68KillRightMinCount) {
	this.formula68KillRightMinCount = formula68KillRightMinCount;
	}

	@Column(name = "fformula69KRMaxCount", nullable = false)
	public int getFormula69KillRightMaxCount() {
	return formula69KillRightMaxCount;
	}

	public void setFormula69KillRightMaxCount(int formula69KillRightMaxCount) {
	this.formula69KillRightMaxCount = formula69KillRightMaxCount;
	}


	@Column(name = "fformula69KRMinCount", nullable = false)
	public int getFormula69KillRightMinCount() {
	return formula69KillRightMinCount;
	}

	public void setFormula69KillRightMinCount(int formula69KillRightMinCount) {
	this.formula69KillRightMinCount = formula69KillRightMinCount;
	}

	@Column(name = "fformula70KRMaxCount", nullable = false)
	public int getFormula70KillRightMaxCount() {
	return formula70KillRightMaxCount;
	}

	public void setFormula70KillRightMaxCount(int formula70KillRightMaxCount) {
	this.formula70KillRightMaxCount = formula70KillRightMaxCount;
	}


	@Column(name = "fformula70KRMinCount", nullable = false)
	public int getFormula70KillRightMinCount() {
	return formula70KillRightMinCount;
	}

	public void setFormula70KillRightMinCount(int formula70KillRightMinCount) {
	this.formula70KillRightMinCount = formula70KillRightMinCount;
	}

	@Column(name = "fformula71KRMaxCount", nullable = false)
	public int getFormula71KillRightMaxCount() {
	return formula71KillRightMaxCount;
	}

	public void setFormula71KillRightMaxCount(int formula71KillRightMaxCount) {
	this.formula71KillRightMaxCount = formula71KillRightMaxCount;
	}


	@Column(name = "fformula71KRMinCount", nullable = false)
	public int getFormula71KillRightMinCount() {
	return formula71KillRightMinCount;
	}

	public void setFormula71KillRightMinCount(int formula71KillRightMinCount) {
	this.formula71KillRightMinCount = formula71KillRightMinCount;
	}

	@Column(name = "fformula72KRMaxCount", nullable = false)
	public int getFormula72KillRightMaxCount() {
	return formula72KillRightMaxCount;
	}

	public void setFormula72KillRightMaxCount(int formula72KillRightMaxCount) {
	this.formula72KillRightMaxCount = formula72KillRightMaxCount;
	}


	@Column(name = "fformula72KRMinCount", nullable = false)
	public int getFormula72KillRightMinCount() {
	return formula72KillRightMinCount;
	}

	public void setFormula72KillRightMinCount(int formula72KillRightMinCount) {
	this.formula72KillRightMinCount = formula72KillRightMinCount;
	}

	@Column(name = "fformula73KRMaxCount", nullable = false)
	public int getFormula73KillRightMaxCount() {
	return formula73KillRightMaxCount;
	}

	public void setFormula73KillRightMaxCount(int formula73KillRightMaxCount) {
	this.formula73KillRightMaxCount = formula73KillRightMaxCount;
	}


	@Column(name = "fformula73KRMinCount", nullable = false)
	public int getFormula73KillRightMinCount() {
	return formula73KillRightMinCount;
	}

	public void setFormula73KillRightMinCount(int formula73KillRightMinCount) {
	this.formula73KillRightMinCount = formula73KillRightMinCount;
	}

	@Column(name = "fformula74KRMaxCount", nullable = false)
	public int getFormula74KillRightMaxCount() {
	return formula74KillRightMaxCount;
	}

	public void setFormula74KillRightMaxCount(int formula74KillRightMaxCount) {
	this.formula74KillRightMaxCount = formula74KillRightMaxCount;
	}


	@Column(name = "fformula74KRMinCount", nullable = false)
	public int getFormula74KillRightMinCount() {
	return formula74KillRightMinCount;
	}

	public void setFormula74KillRightMinCount(int formula74KillRightMinCount) {
	this.formula74KillRightMinCount = formula74KillRightMinCount;
	}

	@Column(name = "fformula75KRMaxCount", nullable = false)
	public int getFormula75KillRightMaxCount() {
	return formula75KillRightMaxCount;
	}

	public void setFormula75KillRightMaxCount(int formula75KillRightMaxCount) {
	this.formula75KillRightMaxCount = formula75KillRightMaxCount;
	}


	@Column(name = "fformula75KRMinCount", nullable = false)
	public int getFormula75KillRightMinCount() {
	return formula75KillRightMinCount;
	}

	public void setFormula75KillRightMinCount(int formula75KillRightMinCount) {
	this.formula75KillRightMinCount = formula75KillRightMinCount;
	}

	@Column(name = "fformula76KRMaxCount", nullable = false)
	public int getFormula76KillRightMaxCount() {
	return formula76KillRightMaxCount;
	}

	public void setFormula76KillRightMaxCount(int formula76KillRightMaxCount) {
	this.formula76KillRightMaxCount = formula76KillRightMaxCount;
	}


	@Column(name = "fformula76KRMinCount", nullable = false)
	public int getFormula76KillRightMinCount() {
	return formula76KillRightMinCount;
	}

	public void setFormula76KillRightMinCount(int formula76KillRightMinCount) {
	this.formula76KillRightMinCount = formula76KillRightMinCount;
	}

	@Column(name = "fformula77KRMaxCount", nullable = false)
	public int getFormula77KillRightMaxCount() {
	return formula77KillRightMaxCount;
	}

	public void setFormula77KillRightMaxCount(int formula77KillRightMaxCount) {
	this.formula77KillRightMaxCount = formula77KillRightMaxCount;
	}


	@Column(name = "fformula77KRMinCount", nullable = false)
	public int getFormula77KillRightMinCount() {
	return formula77KillRightMinCount;
	}

	public void setFormula77KillRightMinCount(int formula77KillRightMinCount) {
	this.formula77KillRightMinCount = formula77KillRightMinCount;
	}

	@Column(name = "fformula78KRMaxCount", nullable = false)
	public int getFormula78KillRightMaxCount() {
	return formula78KillRightMaxCount;
	}

	public void setFormula78KillRightMaxCount(int formula78KillRightMaxCount) {
	this.formula78KillRightMaxCount = formula78KillRightMaxCount;
	}


	@Column(name = "fformula78KRMinCount", nullable = false)
	public int getFormula78KillRightMinCount() {
	return formula78KillRightMinCount;
	}

	public void setFormula78KillRightMinCount(int formula78KillRightMinCount) {
	this.formula78KillRightMinCount = formula78KillRightMinCount;
	}

	@Column(name = "fformula79KRMaxCount", nullable = false)
	public int getFormula79KillRightMaxCount() {
	return formula79KillRightMaxCount;
	}

	public void setFormula79KillRightMaxCount(int formula79KillRightMaxCount) {
	this.formula79KillRightMaxCount = formula79KillRightMaxCount;
	}


	@Column(name = "fformula79KRMinCount", nullable = false)
	public int getFormula79KillRightMinCount() {
	return formula79KillRightMinCount;
	}

	public void setFormula79KillRightMinCount(int formula79KillRightMinCount) {
	this.formula79KillRightMinCount = formula79KillRightMinCount;
	}

	@Column(name = "fformula80KRMaxCount", nullable = false)
	public int getFormula80KillRightMaxCount() {
	return formula80KillRightMaxCount;
	}

	public void setFormula80KillRightMaxCount(int formula80KillRightMaxCount) {
	this.formula80KillRightMaxCount = formula80KillRightMaxCount;
	}


	@Column(name = "fformula80KRMinCount", nullable = false)
	public int getFormula80KillRightMinCount() {
	return formula80KillRightMinCount;
	}

	public void setFormula80KillRightMinCount(int formula80KillRightMinCount) {
	this.formula80KillRightMinCount = formula80KillRightMinCount;
	}

	@Column(name = "fformula81KRMaxCount", nullable = false)
	public int getFormula81KillRightMaxCount() {
	return formula81KillRightMaxCount;
	}

	public void setFormula81KillRightMaxCount(int formula81KillRightMaxCount) {
	this.formula81KillRightMaxCount = formula81KillRightMaxCount;
	}


	@Column(name = "fformula81KRMinCount", nullable = false)
	public int getFormula81KillRightMinCount() {
	return formula81KillRightMinCount;
	}

	public void setFormula81KillRightMinCount(int formula81KillRightMinCount) {
	this.formula81KillRightMinCount = formula81KillRightMinCount;
	}

	@Column(name = "fformula82KRMaxCount", nullable = false)
	public int getFormula82KillRightMaxCount() {
	return formula82KillRightMaxCount;
	}

	public void setFormula82KillRightMaxCount(int formula82KillRightMaxCount) {
	this.formula82KillRightMaxCount = formula82KillRightMaxCount;
	}


	@Column(name = "fformula82KRMinCount", nullable = false)
	public int getFormula82KillRightMinCount() {
	return formula82KillRightMinCount;
	}

	public void setFormula82KillRightMinCount(int formula82KillRightMinCount) {
	this.formula82KillRightMinCount = formula82KillRightMinCount;
	}

	@Column(name = "fformula83KRMaxCount", nullable = false)
	public int getFormula83KillRightMaxCount() {
	return formula83KillRightMaxCount;
	}

	public void setFormula83KillRightMaxCount(int formula83KillRightMaxCount) {
	this.formula83KillRightMaxCount = formula83KillRightMaxCount;
	}


	@Column(name = "fformula83KRMinCount", nullable = false)
	public int getFormula83KillRightMinCount() {
	return formula83KillRightMinCount;
	}

	public void setFormula83KillRightMinCount(int formula83KillRightMinCount) {
	this.formula83KillRightMinCount = formula83KillRightMinCount;
	}

	@Column(name = "fformula84KRMaxCount", nullable = false)
	public int getFormula84KillRightMaxCount() {
	return formula84KillRightMaxCount;
	}

	public void setFormula84KillRightMaxCount(int formula84KillRightMaxCount) {
	this.formula84KillRightMaxCount = formula84KillRightMaxCount;
	}


	@Column(name = "fformula84KRMinCount", nullable = false)
	public int getFormula84KillRightMinCount() {
	return formula84KillRightMinCount;
	}

	public void setFormula84KillRightMinCount(int formula84KillRightMinCount) {
	this.formula84KillRightMinCount = formula84KillRightMinCount;
	}

	@Column(name = "fformula85KRMaxCount", nullable = false)
	public int getFormula85KillRightMaxCount() {
	return formula85KillRightMaxCount;
	}

	public void setFormula85KillRightMaxCount(int formula85KillRightMaxCount) {
	this.formula85KillRightMaxCount = formula85KillRightMaxCount;
	}


	@Column(name = "fformula85KRMinCount", nullable = false)
	public int getFormula85KillRightMinCount() {
	return formula85KillRightMinCount;
	}

	public void setFormula85KillRightMinCount(int formula85KillRightMinCount) {
	this.formula85KillRightMinCount = formula85KillRightMinCount;
	}

	@Column(name = "fformula86KRMaxCount", nullable = false)
	public int getFormula86KillRightMaxCount() {
	return formula86KillRightMaxCount;
	}

	public void setFormula86KillRightMaxCount(int formula86KillRightMaxCount) {
	this.formula86KillRightMaxCount = formula86KillRightMaxCount;
	}


	@Column(name = "fformula86KRMinCount", nullable = false)
	public int getFormula86KillRightMinCount() {
	return formula86KillRightMinCount;
	}

	public void setFormula86KillRightMinCount(int formula86KillRightMinCount) {
	this.formula86KillRightMinCount = formula86KillRightMinCount;
	}

	@Column(name = "fformula87KRMaxCount", nullable = false)
	public int getFormula87KillRightMaxCount() {
	return formula87KillRightMaxCount;
	}

	public void setFormula87KillRightMaxCount(int formula87KillRightMaxCount) {
	this.formula87KillRightMaxCount = formula87KillRightMaxCount;
	}


	@Column(name = "fformula87KRMinCount", nullable = false)
	public int getFormula87KillRightMinCount() {
	return formula87KillRightMinCount;
	}

	public void setFormula87KillRightMinCount(int formula87KillRightMinCount) {
	this.formula87KillRightMinCount = formula87KillRightMinCount;
	}

	@Column(name = "fformula88KRMaxCount", nullable = false)
	public int getFormula88KillRightMaxCount() {
	return formula88KillRightMaxCount;
	}

	public void setFormula88KillRightMaxCount(int formula88KillRightMaxCount) {
	this.formula88KillRightMaxCount = formula88KillRightMaxCount;
	}


	@Column(name = "fformula88KRMinCount", nullable = false)
	public int getFormula88KillRightMinCount() {
	return formula88KillRightMinCount;
	}

	public void setFormula88KillRightMinCount(int formula88KillRightMinCount) {
	this.formula88KillRightMinCount = formula88KillRightMinCount;
	}

	@Column(name = "fformula89KRMaxCount", nullable = false)
	public int getFormula89KillRightMaxCount() {
	return formula89KillRightMaxCount;
	}

	public void setFormula89KillRightMaxCount(int formula89KillRightMaxCount) {
	this.formula89KillRightMaxCount = formula89KillRightMaxCount;
	}


	@Column(name = "fformula89KRMinCount", nullable = false)
	public int getFormula89KillRightMinCount() {
	return formula89KillRightMinCount;
	}

	public void setFormula89KillRightMinCount(int formula89KillRightMinCount) {
	this.formula89KillRightMinCount = formula89KillRightMinCount;
	}

	@Column(name = "fformula90KRMaxCount", nullable = false)
	public int getFormula90KillRightMaxCount() {
	return formula90KillRightMaxCount;
	}

	public void setFormula90KillRightMaxCount(int formula90KillRightMaxCount) {
	this.formula90KillRightMaxCount = formula90KillRightMaxCount;
	}


	@Column(name = "fformula90KRMinCount", nullable = false)
	public int getFormula90KillRightMinCount() {
	return formula90KillRightMinCount;
	}

	public void setFormula90KillRightMinCount(int formula90KillRightMinCount) {
	this.formula90KillRightMinCount = formula90KillRightMinCount;
	}

	@Column(name = "fformula91KRMaxCount", nullable = false)
	public int getFormula91KillRightMaxCount() {
	return formula91KillRightMaxCount;
	}

	public void setFormula91KillRightMaxCount(int formula91KillRightMaxCount) {
	this.formula91KillRightMaxCount = formula91KillRightMaxCount;
	}


	@Column(name = "fformula91KRMinCount", nullable = false)
	public int getFormula91KillRightMinCount() {
	return formula91KillRightMinCount;
	}

	public void setFormula91KillRightMinCount(int formula91KillRightMinCount) {
	this.formula91KillRightMinCount = formula91KillRightMinCount;
	}

	@Column(name = "fcarenums", nullable = true)
	public String getCareNums() {
		return careNums;
	}

	public void setCareNums(String careNums) {
		this.careNums = careNums;
	}

	@Column(name = "fcarenumsrst", nullable = true)
	public String getCareNumsResult() {
		return careNumsResult;
	}

	public void setCareNumsResult(String careNumsResult) {
		this.careNumsResult = careNumsResult;
	}

	@Column(name = "fkillnums", nullable = true)
	public String getKillNums() {
		return killNums;
	}

	public void setKillNums(String killNums) {
		this.killNums = killNums;
	}

	@Column(name = "fkillnumsrst", nullable = true)
	public String getKillNumsResult() {
		return killNumsResult;
	}

	public void setKillNumsResult(String killNumsResult) {
		this.killNumsResult = killNumsResult;
	}

	@Column(name = "fselectnums", nullable = true)
	public String getSelectNums() {
		return selectNums;
	}

	public void setSelectNums(String selectNums) {
		this.selectNums = selectNums;
	}

	@Column(name = "fselectnumsrst", nullable = true)
	public String getSelectNumsResult() {
		return selectNumsResult;
	}

	public void setSelectNumsResult(String selectNumsResult) {
		this.selectNumsResult = selectNumsResult;
	}

	@Column(name = "fverified", nullable = false)
	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@Column(name = "fcareformulas", nullable = true)
	public String getCareFormulas() {
		return careFormulas;
	}

	public void setCareFormulas(String careFormulas) {
		this.careFormulas = careFormulas;
	}
	
	@Column(name = "fkillformulas", nullable = true)
	public String getKillFormulas() {
		return killFormulas;
	}

	public void setKillFormulas(String killFormulas) {
		this.killFormulas = killFormulas;
	}

	@Column(name = "fselectformulas", nullable = true)
	public String getSelectFormulas() {
		return selectFormulas;
	}

	public void setSelectFormulas(String selectFormulas) {
		this.selectFormulas = selectFormulas;
	}
	
	@Column(name = "fkillerrorformulas", nullable = true)
	public String getKillErrorFormulas() {
		return killErrorFormulas;
	}

	public void setKillErrorFormulas(String killErrorFormulas) {
		this.killErrorFormulas = killErrorFormulas;
	}

	@Column(name = "fsltrightformulas", nullable = true)
	public String getSelectRightFormulas() {
		return selectRightFormulas;
	}

	public void setSelectRightFormulas(String selectRightFormulas) {
		this.selectRightFormulas = selectRightFormulas;
	}
}
