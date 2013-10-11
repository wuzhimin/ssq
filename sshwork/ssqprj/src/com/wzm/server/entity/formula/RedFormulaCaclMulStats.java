package com.wzm.server.entity.formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：红球公式计算值正确性多期统计表
 * 
 */

@Entity
@Table(name = "t_redfulcaclmulsts", catalog = "ssq")
public class RedFormulaCaclMulStats extends BaseEntity {

	private static final long serialVersionUID = -955282363890508744L;

	public RedFormulaCaclMulStats() {

	}

	private int fromSsqIndex;     // 开始期
	
	private int toSsqIndex;       // 结束期

	private int spaceNum; // 间隔期数

	private int formula1KillRightCount;
	private int formula2KillRightCount;
	private int formula3KillRightCount;
	private int formula4KillRightCount;
	private int formula5KillRightCount;
	private int formula6KillRightCount;
	private int formula7KillRightCount;
	private int formula8KillRightCount;
	private int formula9KillRightCount;
	private int formula10KillRightCount;
	private int formula11KillRightCount;
	private int formula12KillRightCount;
	private int formula13KillRightCount;
	private int formula14KillRightCount;
	private int formula15KillRightCount;
	private int formula16KillRightCount;
	private int formula17KillRightCount;
	private int formula18KillRightCount;
	private int formula19KillRightCount;
	private int formula20KillRightCount;
	private int formula21KillRightCount;
	private int formula22KillRightCount;
	private int formula23KillRightCount;
	private int formula24KillRightCount;
	private int formula25KillRightCount;
	private int formula26KillRightCount;
	private int formula27KillRightCount;
	private int formula28KillRightCount;
	private int formula29KillRightCount;
	private int formula30KillRightCount;
	private int formula31KillRightCount;
	private int formula32KillRightCount;
	private int formula33KillRightCount;
	private int formula34KillRightCount;
	private int formula35KillRightCount;
	private int formula36KillRightCount;
	private int formula37KillRightCount;
	private int formula38KillRightCount;
	private int formula39KillRightCount;
	private int formula40KillRightCount;
	private int formula41KillRightCount;
	private int formula42KillRightCount;
	private int formula43KillRightCount;
	private int formula44KillRightCount;
	private int formula45KillRightCount;
	private int formula46KillRightCount;
	private int formula47KillRightCount;
	private int formula48KillRightCount;
	private int formula49KillRightCount;
	private int formula50KillRightCount;
	private int formula51KillRightCount;
	private int formula52KillRightCount;
	private int formula53KillRightCount;
	private int formula54KillRightCount;
	private int formula55KillRightCount;
	private int formula56KillRightCount;
	private int formula57KillRightCount;
	private int formula58KillRightCount;
	private int formula59KillRightCount;
	private int formula60KillRightCount;
	private int formula61KillRightCount;
	private int formula62KillRightCount;
	private int formula63KillRightCount;
	private int formula64KillRightCount;
	private int formula65KillRightCount;
	private int formula66KillRightCount;
	private int formula67KillRightCount;
	private int formula68KillRightCount;
	private int formula69KillRightCount;
	private int formula70KillRightCount;
	private int formula71KillRightCount;
	private int formula72KillRightCount;
	private int formula73KillRightCount;
	private int formula74KillRightCount;
	private int formula75KillRightCount;
	private int formula76KillRightCount;
	private int formula77KillRightCount;
	private int formula78KillRightCount;
	private int formula79KillRightCount;
	private int formula80KillRightCount;
	private int formula81KillRightCount;
	private int formula82KillRightCount;
	private int formula83KillRightCount;
	private int formula84KillRightCount;
	private int formula85KillRightCount;
	private int formula86KillRightCount;
	private int formula87KillRightCount;
	private int formula88KillRightCount;
	private int formula89KillRightCount;
	private int formula90KillRightCount;
	private int formula91KillRightCount;
	
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

	@Column(name = "fspacenum", nullable = false)
	public int getSpaceNum() {
		return spaceNum;
	}

	public void setSpaceNum(int spaceNum) {
		this.spaceNum = spaceNum;
	}

	@Column(name = "fformula1krcount", nullable = false)
	public int getFormula1KillRightCount() {
		return formula1KillRightCount;
	}

	public void setFormula1KillRightCount(int formula1KillRightCount) {
		this.formula1KillRightCount = formula1KillRightCount;
	}

	@Column(name = "fformula2krcount", nullable = false)
	public int getFormula2KillRightCount() {
		return formula2KillRightCount;
	}

	public void setFormula2KillRightCount(int formula2KillRightCount) {
		this.formula2KillRightCount = formula2KillRightCount;
	}

	@Column(name = "fformula3krcount", nullable = false)
	public int getFormula3KillRightCount() {
		return formula3KillRightCount;
	}

	public void setFormula3KillRightCount(int formula3KillRightCount) {
		this.formula3KillRightCount = formula3KillRightCount;
	}

	@Column(name = "fformula4krcount", nullable = false)
	public int getFormula4KillRightCount() {
		return formula4KillRightCount;
	}

	public void setFormula4KillRightCount(int formula4KillRightCount) {
		this.formula4KillRightCount = formula4KillRightCount;
	}

	@Column(name = "fformula5krcount", nullable = false)
	public int getFormula5KillRightCount() {
		return formula5KillRightCount;
	}

	public void setFormula5KillRightCount(int formula5KillRightCount) {
		this.formula5KillRightCount = formula5KillRightCount;
	}

	@Column(name = "fformula6krcount", nullable = false)
	public int getFormula6KillRightCount() {
		return formula6KillRightCount;
	}

	public void setFormula6KillRightCount(int formula6KillRightCount) {
		this.formula6KillRightCount = formula6KillRightCount;
	}

	@Column(name = "fformula7krcount", nullable = false)
	public int getFormula7KillRightCount() {
		return formula7KillRightCount;
	}

	public void setFormula7KillRightCount(int formula7KillRightCount) {
		this.formula7KillRightCount = formula7KillRightCount;
	}

	@Column(name = "fformula8krcount", nullable = false)
	public int getFormula8KillRightCount() {
		return formula8KillRightCount;
	}

	public void setFormula8KillRightCount(int formula8KillRightCount) {
		this.formula8KillRightCount = formula8KillRightCount;
	}

	@Column(name = "fformula9krcount", nullable = false)
	public int getFormula9KillRightCount() {
		return formula9KillRightCount;
	}

	public void setFormula9KillRightCount(int formula9KillRightCount) {
		this.formula9KillRightCount = formula9KillRightCount;
	}

	@Column(name = "fformula10krcount", nullable = false)
	public int getFormula10KillRightCount() {
		return formula10KillRightCount;
	}

	public void setFormula10KillRightCount(int formula10KillRightCount) {
		this.formula10KillRightCount = formula10KillRightCount;
	}

	@Column(name = "fformula11krcount", nullable = false)
	public int getFormula11KillRightCount() {
		return formula11KillRightCount;
	}

	public void setFormula11KillRightCount(int formula11KillRightCount) {
		this.formula11KillRightCount = formula11KillRightCount;
	}

	@Column(name = "fformula12krcount", nullable = false)
	public int getFormula12KillRightCount() {
		return formula12KillRightCount;
	}

	public void setFormula12KillRightCount(int formula12KillRightCount) {
		this.formula12KillRightCount = formula12KillRightCount;
	}

	@Column(name = "fformula13krcount", nullable = false)
	public int getFormula13KillRightCount() {
		return formula13KillRightCount;
	}

	public void setFormula13KillRightCount(int formula13KillRightCount) {
		this.formula13KillRightCount = formula13KillRightCount;
	}

	@Column(name = "fformula14krcount", nullable = false)
	public int getFormula14KillRightCount() {
		return formula14KillRightCount;
	}

	public void setFormula14KillRightCount(int formula14KillRightCount) {
		this.formula14KillRightCount = formula14KillRightCount;
	}

	@Column(name = "fformula15krcount", nullable = false)
	public int getFormula15KillRightCount() {
		return formula15KillRightCount;
	}

	public void setFormula15KillRightCount(int formula15KillRightCount) {
		this.formula15KillRightCount = formula15KillRightCount;
	}

	@Column(name = "fformula16krcount", nullable = false)
	public int getFormula16KillRightCount() {
		return formula16KillRightCount;
	}

	public void setFormula16KillRightCount(int formula16KillRightCount) {
		this.formula16KillRightCount = formula16KillRightCount;
	}

	@Column(name = "fformula17krcount", nullable = false)
	public int getFormula17KillRightCount() {
		return formula17KillRightCount;
	}

	public void setFormula17KillRightCount(int formula17KillRightCount) {
		this.formula17KillRightCount = formula17KillRightCount;
	}

	@Column(name = "fformula18krcount", nullable = false)
	public int getFormula18KillRightCount() {
		return formula18KillRightCount;
	}

	public void setFormula18KillRightCount(int formula18KillRightCount) {
		this.formula18KillRightCount = formula18KillRightCount;
	}

	@Column(name = "fformula19krcount", nullable = false)
	public int getFormula19KillRightCount() {
		return formula19KillRightCount;
	}

	public void setFormula19KillRightCount(int formula19KillRightCount) {
		this.formula19KillRightCount = formula19KillRightCount;
	}

	@Column(name = "fformula20krcount", nullable = false)
	public int getFormula20KillRightCount() {
		return formula20KillRightCount;
	}

	public void setFormula20KillRightCount(int formula20KillRightCount) {
		this.formula20KillRightCount = formula20KillRightCount;
	}

	@Column(name = "fformula21krcount", nullable = false)
	public int getFormula21KillRightCount() {
		return formula21KillRightCount;
	}

	public void setFormula21KillRightCount(int formula21KillRightCount) {
		this.formula21KillRightCount = formula21KillRightCount;
	}

	@Column(name = "fformula22krcount", nullable = false)
	public int getFormula22KillRightCount() {
		return formula22KillRightCount;
	}

	public void setFormula22KillRightCount(int formula22KillRightCount) {
		this.formula22KillRightCount = formula22KillRightCount;
	}

	@Column(name = "fformula23krcount", nullable = false)
	public int getFormula23KillRightCount() {
		return formula23KillRightCount;
	}

	public void setFormula23KillRightCount(int formula23KillRightCount) {
		this.formula23KillRightCount = formula23KillRightCount;
	}

	@Column(name = "fformula24krcount", nullable = false)
	public int getFormula24KillRightCount() {
		return formula24KillRightCount;
	}

	public void setFormula24KillRightCount(int formula24KillRightCount) {
		this.formula24KillRightCount = formula24KillRightCount;
	}

	@Column(name = "fformula25krcount", nullable = false)
	public int getFormula25KillRightCount() {
		return formula25KillRightCount;
	}

	public void setFormula25KillRightCount(int formula25KillRightCount) {
		this.formula25KillRightCount = formula25KillRightCount;
	}

	@Column(name = "fformula26krcount", nullable = false)
	public int getFormula26KillRightCount() {
		return formula26KillRightCount;
	}

	public void setFormula26KillRightCount(int formula26KillRightCount) {
		this.formula26KillRightCount = formula26KillRightCount;
	}

	@Column(name = "fformula27krcount", nullable = false)
	public int getFormula27KillRightCount() {
		return formula27KillRightCount;
	}

	public void setFormula27KillRightCount(int formula27KillRightCount) {
		this.formula27KillRightCount = formula27KillRightCount;
	}

	@Column(name = "fformula28krcount", nullable = false)
	public int getFormula28KillRightCount() {
		return formula28KillRightCount;
	}

	public void setFormula28KillRightCount(int formula28KillRightCount) {
		this.formula28KillRightCount = formula28KillRightCount;
	}

	@Column(name = "fformula29krcount", nullable = false)
	public int getFormula29KillRightCount() {
		return formula29KillRightCount;
	}

	public void setFormula29KillRightCount(int formula29KillRightCount) {
		this.formula29KillRightCount = formula29KillRightCount;
	}

	@Column(name = "fformula30krcount", nullable = false)
	public int getFormula30KillRightCount() {
		return formula30KillRightCount;
	}

	public void setFormula30KillRightCount(int formula30KillRightCount) {
		this.formula30KillRightCount = formula30KillRightCount;
	}

	@Column(name = "fformula31krcount", nullable = false)
	public int getFormula31KillRightCount() {
		return formula31KillRightCount;
	}

	public void setFormula31KillRightCount(int formula31KillRightCount) {
		this.formula31KillRightCount = formula31KillRightCount;
	}

	@Column(name = "fformula32krcount", nullable = false)
	public int getFormula32KillRightCount() {
		return formula32KillRightCount;
	}

	public void setFormula32KillRightCount(int formula32KillRightCount) {
		this.formula32KillRightCount = formula32KillRightCount;
	}

	@Column(name = "fformula33krcount", nullable = false)
	public int getFormula33KillRightCount() {
		return formula33KillRightCount;
	}

	public void setFormula33KillRightCount(int formula33KillRightCount) {
		this.formula33KillRightCount = formula33KillRightCount;
	}

	@Column(name = "fformula34krcount", nullable = false)
	public int getFormula34KillRightCount() {
		return formula34KillRightCount;
	}

	public void setFormula34KillRightCount(int formula34KillRightCount) {
		this.formula34KillRightCount = formula34KillRightCount;
	}

	@Column(name = "fformula35krcount", nullable = false)
	public int getFormula35KillRightCount() {
		return formula35KillRightCount;
	}

	public void setFormula35KillRightCount(int formula35KillRightCount) {
		this.formula35KillRightCount = formula35KillRightCount;
	}

	@Column(name = "fformula36krcount", nullable = false)
	public int getFormula36KillRightCount() {
		return formula36KillRightCount;
	}

	public void setFormula36KillRightCount(int formula36KillRightCount) {
		this.formula36KillRightCount = formula36KillRightCount;
	}

	@Column(name = "fformula37krcount", nullable = false)
	public int getFormula37KillRightCount() {
		return formula37KillRightCount;
	}

	public void setFormula37KillRightCount(int formula37KillRightCount) {
		this.formula37KillRightCount = formula37KillRightCount;
	}

	@Column(name = "fformula38krcount", nullable = false)
	public int getFormula38KillRightCount() {
		return formula38KillRightCount;
	}

	public void setFormula38KillRightCount(int formula38KillRightCount) {
		this.formula38KillRightCount = formula38KillRightCount;
	}

	@Column(name = "fformula39krcount", nullable = false)
	public int getFormula39KillRightCount() {
		return formula39KillRightCount;
	}

	public void setFormula39KillRightCount(int formula39KillRightCount) {
		this.formula39KillRightCount = formula39KillRightCount;
	}

	@Column(name = "fformula40krcount", nullable = false)
	public int getFormula40KillRightCount() {
		return formula40KillRightCount;
	}

	public void setFormula40KillRightCount(int formula40KillRightCount) {
		this.formula40KillRightCount = formula40KillRightCount;
	}

	@Column(name = "fformula41krcount", nullable = false)
	public int getFormula41KillRightCount() {
		return formula41KillRightCount;
	}

	public void setFormula41KillRightCount(int formula41KillRightCount) {
		this.formula41KillRightCount = formula41KillRightCount;
	}

	@Column(name = "fformula42krcount", nullable = false)
	public int getFormula42KillRightCount() {
		return formula42KillRightCount;
	}

	public void setFormula42KillRightCount(int formula42KillRightCount) {
		this.formula42KillRightCount = formula42KillRightCount;
	}

	@Column(name = "fformula43krcount", nullable = false)
	public int getFormula43KillRightCount() {
		return formula43KillRightCount;
	}

	public void setFormula43KillRightCount(int formula43KillRightCount) {
		this.formula43KillRightCount = formula43KillRightCount;
	}

	@Column(name = "fformula44krcount", nullable = false)
	public int getFormula44KillRightCount() {
		return formula44KillRightCount;
	}

	public void setFormula44KillRightCount(int formula44KillRightCount) {
		this.formula44KillRightCount = formula44KillRightCount;
	}

	@Column(name = "fformula45krcount", nullable = false)
	public int getFormula45KillRightCount() {
		return formula45KillRightCount;
	}

	public void setFormula45KillRightCount(int formula45KillRightCount) {
		this.formula45KillRightCount = formula45KillRightCount;
	}

	@Column(name = "fformula46krcount", nullable = false)
	public int getFormula46KillRightCount() {
		return formula46KillRightCount;
	}

	public void setFormula46KillRightCount(int formula46KillRightCount) {
		this.formula46KillRightCount = formula46KillRightCount;
	}

	@Column(name = "fformula47krcount", nullable = false)
	public int getFormula47KillRightCount() {
		return formula47KillRightCount;
	}

	public void setFormula47KillRightCount(int formula47KillRightCount) {
		this.formula47KillRightCount = formula47KillRightCount;
	}

	@Column(name = "fformula48krcount", nullable = false)
	public int getFormula48KillRightCount() {
		return formula48KillRightCount;
	}

	public void setFormula48KillRightCount(int formula48KillRightCount) {
		this.formula48KillRightCount = formula48KillRightCount;
	}

	@Column(name = "fformula49krcount", nullable = false)
	public int getFormula49KillRightCount() {
		return formula49KillRightCount;
	}

	public void setFormula49KillRightCount(int formula49KillRightCount) {
		this.formula49KillRightCount = formula49KillRightCount;
	}

	@Column(name = "fformula50krcount", nullable = false)
	public int getFormula50KillRightCount() {
		return formula50KillRightCount;
	}

	public void setFormula50KillRightCount(int formula50KillRightCount) {
		this.formula50KillRightCount = formula50KillRightCount;
	}

	@Column(name = "fformula51krcount", nullable = false)
	public int getFormula51KillRightCount() {
		return formula51KillRightCount;
	}

	public void setFormula51KillRightCount(int formula51KillRightCount) {
		this.formula51KillRightCount = formula51KillRightCount;
	}

	@Column(name = "fformula52krcount", nullable = false)
	public int getFormula52KillRightCount() {
		return formula52KillRightCount;
	}

	public void setFormula52KillRightCount(int formula52KillRightCount) {
		this.formula52KillRightCount = formula52KillRightCount;
	}

	@Column(name = "fformula53krcount", nullable = false)
	public int getFormula53KillRightCount() {
		return formula53KillRightCount;
	}

	public void setFormula53KillRightCount(int formula53KillRightCount) {
		this.formula53KillRightCount = formula53KillRightCount;
	}

	@Column(name = "fformula54krcount", nullable = false)
	public int getFormula54KillRightCount() {
		return formula54KillRightCount;
	}

	public void setFormula54KillRightCount(int formula54KillRightCount) {
		this.formula54KillRightCount = formula54KillRightCount;
	}

	@Column(name = "fformula55krcount", nullable = false)
	public int getFormula55KillRightCount() {
		return formula55KillRightCount;
	}

	public void setFormula55KillRightCount(int formula55KillRightCount) {
		this.formula55KillRightCount = formula55KillRightCount;
	}

	@Column(name = "fformula56krcount", nullable = false)
	public int getFormula56KillRightCount() {
		return formula56KillRightCount;
	}

	public void setFormula56KillRightCount(int formula56KillRightCount) {
		this.formula56KillRightCount = formula56KillRightCount;
	}

	@Column(name = "fformula57krcount", nullable = false)
	public int getFormula57KillRightCount() {
		return formula57KillRightCount;
	}

	public void setFormula57KillRightCount(int formula57KillRightCount) {
		this.formula57KillRightCount = formula57KillRightCount;
	}

	@Column(name = "fformula58krcount", nullable = false)
	public int getFormula58KillRightCount() {
		return formula58KillRightCount;
	}

	public void setFormula58KillRightCount(int formula58KillRightCount) {
		this.formula58KillRightCount = formula58KillRightCount;
	}

	@Column(name = "fformula59krcount", nullable = false)
	public int getFormula59KillRightCount() {
		return formula59KillRightCount;
	}

	public void setFormula59KillRightCount(int formula59KillRightCount) {
		this.formula59KillRightCount = formula59KillRightCount;
	}

	@Column(name = "fformula60krcount", nullable = false)
	public int getFormula60KillRightCount() {
		return formula60KillRightCount;
	}

	public void setFormula60KillRightCount(int formula60KillRightCount) {
		this.formula60KillRightCount = formula60KillRightCount;
	}

	@Column(name = "fformula61krcount", nullable = false)
	public int getFormula61KillRightCount() {
		return formula61KillRightCount;
	}

	public void setFormula61KillRightCount(int formula61KillRightCount) {
		this.formula61KillRightCount = formula61KillRightCount;
	}

	@Column(name = "fformula62krcount", nullable = false)
	public int getFormula62KillRightCount() {
		return formula62KillRightCount;
	}

	public void setFormula62KillRightCount(int formula62KillRightCount) {
		this.formula62KillRightCount = formula62KillRightCount;
	}

	@Column(name = "fformula63krcount", nullable = false)
	public int getFormula63KillRightCount() {
		return formula63KillRightCount;
	}

	public void setFormula63KillRightCount(int formula63KillRightCount) {
		this.formula63KillRightCount = formula63KillRightCount;
	}

	@Column(name = "fformula64krcount", nullable = false)
	public int getFormula64KillRightCount() {
		return formula64KillRightCount;
	}

	public void setFormula64KillRightCount(int formula64KillRightCount) {
		this.formula64KillRightCount = formula64KillRightCount;
	}

	@Column(name = "fformula65krcount", nullable = false)
	public int getFormula65KillRightCount() {
		return formula65KillRightCount;
	}

	public void setFormula65KillRightCount(int formula65KillRightCount) {
		this.formula65KillRightCount = formula65KillRightCount;
	}

	@Column(name = "fformula66krcount", nullable = false)
	public int getFormula66KillRightCount() {
		return formula66KillRightCount;
	}

	public void setFormula66KillRightCount(int formula66KillRightCount) {
		this.formula66KillRightCount = formula66KillRightCount;
	}

	@Column(name = "fformula67krcount", nullable = false)
	public int getFormula67KillRightCount() {
		return formula67KillRightCount;
	}

	public void setFormula67KillRightCount(int formula67KillRightCount) {
		this.formula67KillRightCount = formula67KillRightCount;
	}

	@Column(name = "fformula68krcount", nullable = false)
	public int getFormula68KillRightCount() {
		return formula68KillRightCount;
	}

	public void setFormula68KillRightCount(int formula68KillRightCount) {
		this.formula68KillRightCount = formula68KillRightCount;
	}

	@Column(name = "fformula69krcount", nullable = false)
	public int getFormula69KillRightCount() {
		return formula69KillRightCount;
	}

	public void setFormula69KillRightCount(int formula69KillRightCount) {
		this.formula69KillRightCount = formula69KillRightCount;
	}

	@Column(name = "fformula70krcount", nullable = false)
	public int getFormula70KillRightCount() {
		return formula70KillRightCount;
	}

	public void setFormula70KillRightCount(int formula70KillRightCount) {
		this.formula70KillRightCount = formula70KillRightCount;
	}

	@Column(name = "fformula71krcount", nullable = false)
	public int getFormula71KillRightCount() {
		return formula71KillRightCount;
	}

	public void setFormula71KillRightCount(int formula71KillRightCount) {
		this.formula71KillRightCount = formula71KillRightCount;
	}

	@Column(name = "fformula72krcount", nullable = false)
	public int getFormula72KillRightCount() {
		return formula72KillRightCount;
	}

	public void setFormula72KillRightCount(int formula72KillRightCount) {
		this.formula72KillRightCount = formula72KillRightCount;
	}

	@Column(name = "fformula73krcount", nullable = false)
	public int getFormula73KillRightCount() {
		return formula73KillRightCount;
	}

	public void setFormula73KillRightCount(int formula73KillRightCount) {
		this.formula73KillRightCount = formula73KillRightCount;
	}

	@Column(name = "fformula74krcount", nullable = false)
	public int getFormula74KillRightCount() {
		return formula74KillRightCount;
	}

	public void setFormula74KillRightCount(int formula74KillRightCount) {
		this.formula74KillRightCount = formula74KillRightCount;
	}

	@Column(name = "fformula75krcount", nullable = false)
	public int getFormula75KillRightCount() {
		return formula75KillRightCount;
	}

	public void setFormula75KillRightCount(int formula75KillRightCount) {
		this.formula75KillRightCount = formula75KillRightCount;
	}

	@Column(name = "fformula76krcount", nullable = false)
	public int getFormula76KillRightCount() {
		return formula76KillRightCount;
	}

	public void setFormula76KillRightCount(int formula76KillRightCount) {
		this.formula76KillRightCount = formula76KillRightCount;
	}

	@Column(name = "fformula77krcount", nullable = false)
	public int getFormula77KillRightCount() {
		return formula77KillRightCount;
	}

	public void setFormula77KillRightCount(int formula77KillRightCount) {
		this.formula77KillRightCount = formula77KillRightCount;
	}

	@Column(name = "fformula78krcount", nullable = false)
	public int getFormula78KillRightCount() {
		return formula78KillRightCount;
	}

	public void setFormula78KillRightCount(int formula78KillRightCount) {
		this.formula78KillRightCount = formula78KillRightCount;
	}

	@Column(name = "fformula79krcount", nullable = false)
	public int getFormula79KillRightCount() {
		return formula79KillRightCount;
	}

	public void setFormula79KillRightCount(int formula79KillRightCount) {
		this.formula79KillRightCount = formula79KillRightCount;
	}

	@Column(name = "fformula80krcount", nullable = false)
	public int getFormula80KillRightCount() {
		return formula80KillRightCount;
	}

	public void setFormula80KillRightCount(int formula80KillRightCount) {
		this.formula80KillRightCount = formula80KillRightCount;
	}

	@Column(name = "fformula81krcount", nullable = false)
	public int getFormula81KillRightCount() {
		return formula81KillRightCount;
	}

	public void setFormula81KillRightCount(int formula81KillRightCount) {
		this.formula81KillRightCount = formula81KillRightCount;
	}

	@Column(name = "fformula82krcount", nullable = false)
	public int getFormula82KillRightCount() {
		return formula82KillRightCount;
	}

	public void setFormula82KillRightCount(int formula82KillRightCount) {
		this.formula82KillRightCount = formula82KillRightCount;
	}

	@Column(name = "fformula83krcount", nullable = false)
	public int getFormula83KillRightCount() {
		return formula83KillRightCount;
	}

	public void setFormula83KillRightCount(int formula83KillRightCount) {
		this.formula83KillRightCount = formula83KillRightCount;
	}

	@Column(name = "fformula84krcount", nullable = false)
	public int getFormula84KillRightCount() {
		return formula84KillRightCount;
	}

	public void setFormula84KillRightCount(int formula84KillRightCount) {
		this.formula84KillRightCount = formula84KillRightCount;
	}

	@Column(name = "fformula85krcount", nullable = false)
	public int getFormula85KillRightCount() {
		return formula85KillRightCount;
	}

	public void setFormula85KillRightCount(int formula85KillRightCount) {
		this.formula85KillRightCount = formula85KillRightCount;
	}

	@Column(name = "fformula86krcount", nullable = false)
	public int getFormula86KillRightCount() {
		return formula86KillRightCount;
	}

	public void setFormula86KillRightCount(int formula86KillRightCount) {
		this.formula86KillRightCount = formula86KillRightCount;
	}

	@Column(name = "fformula87krcount", nullable = false)
	public int getFormula87KillRightCount() {
		return formula87KillRightCount;
	}

	public void setFormula87KillRightCount(int formula87KillRightCount) {
		this.formula87KillRightCount = formula87KillRightCount;
	}

	@Column(name = "fformula88krcount", nullable = false)
	public int getFormula88KillRightCount() {
		return formula88KillRightCount;
	}

	public void setFormula88KillRightCount(int formula88KillRightCount) {
		this.formula88KillRightCount = formula88KillRightCount;
	}

	@Column(name = "fformula89krcount", nullable = false)
	public int getFormula89KillRightCount() {
		return formula89KillRightCount;
	}

	public void setFormula89KillRightCount(int formula89KillRightCount) {
		this.formula89KillRightCount = formula89KillRightCount;
	}

	@Column(name = "fformula90krcount", nullable = false)
	public int getFormula90KillRightCount() {
		return formula90KillRightCount;
	}

	public void setFormula90KillRightCount(int formula90KillRightCount) {
		this.formula90KillRightCount = formula90KillRightCount;
	}

	@Column(name = "fformula91krcount", nullable = false)
	public int getFormula91KillRightCount() {
		return formula91KillRightCount;
	}

	public void setFormula91KillRightCount(int formula91KillRightCount) {
		this.formula91KillRightCount = formula91KillRightCount;
	}

}
