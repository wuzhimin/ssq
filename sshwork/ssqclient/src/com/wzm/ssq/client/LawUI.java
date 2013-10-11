/*
 * LawUI.java
 *
 * Created on __DATE__, __TIME__
 */

package com.wzm.ssq.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ssq.common.util.CommonConstant;
import com.ssq.common.util.FileUtil;
import com.ssq.common.util.SsqUtils;
import com.ssq.util.FilterChainUtil;
import com.ssq.util.filter.FilterChain;
import com.ssq.util.filter.LoseRowColFeatureSameOrDiffFilter;
import com.wzm.biz.BlueHotLawBuild;
import com.wzm.biz.MulFormulaLawBuild;
import com.wzm.biz.MulFormulaLawBuild2;
import com.wzm.biz.NumCountHotLawBuild;
import com.wzm.biz.PrimeHotLawBuild;
import com.wzm.biz.RedHotLawBuild;
import com.wzm.biz.StatsLawBuild;
import com.wzm.biz.SumHotLawBuild;
import com.wzm.biz.TailHotLawBuild;
import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.dao.ssq.SsqRecordDao;
import com.wzm.server.entity.base.BaseEntity;
import com.wzm.server.entity.formula.RedFormulaCaclMulForcast;
import com.wzm.server.entity.ssq.SsqRecord;
import com.wzm.test.BlueFormulaTest;
import com.wzm.test.RedFormulaTest;
import com.wzm.test.TestBuildLoseRowCol;
import com.wzm.test.TestHistoryFeatureForAnyOrder;
import com.wzm.test.TestMergeColRow;
import com.wzm.util.ClientBeanUtil;

/**
 *
 * @author  __USER__
 */
public class LawUI extends javax.swing.JFrame {

	private static final long serialVersionUID = -976010273976970459L;

	private class CommonType {
		private String title;
		private String value;

		public CommonType(String title, String value) {
			this.setTitle(title);
			this.setValue(value);
		}

		public String toString() {
			return getTitle();
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}

	/** Creates new form LawUI */
	public LawUI() {
		initComponents();

		initData();
	}

	private void initData() {
		ssqRecordDao = (SsqRecordDao) ClientBeanUtil.getDao("ssqRecordDao");

		basedao = (BaseDao) ClientBeanUtil.getDao("baseDao");

		String hql = "select max(ssqIndex) from SsqRecord";
		int ssqIndex = ssqRecordDao.getFunctionIntValue(hql);
		txtCurrentSsqIndex.setText(String.valueOf(ssqIndex));

		comboForcastNum.addItem(300);
		comboForcastNum.addItem(400);
		comboForcastNum.setSelectedIndex(0);

		comboSpaceNum.addItem(50);
		comboSpaceNum.addItem(60);
		comboSpaceNum.addItem(80);
		comboSpaceNum.addItem(100);
		comboSpaceNum.setSelectedIndex(0);

		initSumType();

		initCountType();
		
		txtExcludeFile1.setText(CommonConstant.FILTER_PATH + "red-exclude.txt");
		txtExcludeFile2.setText(CommonConstant.FILTER_PATH + "red-exclude-third-1.txt");
	}

	private void initSumType() {
		CommonType type1 = new CommonType("红球和值", SumHotLawBuild.RED_SUM);
		comboSumType.addItem(type1);

		CommonType type2 = new CommonType("红球偶数和值", SumHotLawBuild.EVEN_SUM);
		comboSumType.addItem(type2);

		CommonType type3 = new CommonType("红球奇数和值", SumHotLawBuild.ODD_SUM);
		comboSumType.addItem(type3);

		CommonType type4 = new CommonType("红球质数和值", SumHotLawBuild.PRIME_SUM);
		comboSumType.addItem(type4);

		CommonType type5 = new CommonType("红球大数和值", SumHotLawBuild.BIG_SUM);
		comboSumType.addItem(type5);

		CommonType type6 = new CommonType("红球小数和值", SumHotLawBuild.SMALL_SUM);
		comboSumType.addItem(type6);

		CommonType type7 = new CommonType("红球小区数和值",
				SumHotLawBuild.FIRST_ZONE_SUM);
		comboSumType.addItem(type7);

		CommonType type8 = new CommonType("红球中区数和值",
				SumHotLawBuild.SECOND_ZONE_SUM);
		comboSumType.addItem(type8);

		CommonType type9 = new CommonType("红球大区数和值",
				SumHotLawBuild.THIRD_ZONE_SUM);
		comboSumType.addItem(type9);

	}

	private void initCountType() {
		CommonType type1 = new CommonType("红球偶数个数",
				NumCountHotLawBuild.EVEN_COUNT);
		comboCountType.addItem(type1);

		CommonType type2 = new CommonType("红球奇数个数",
				NumCountHotLawBuild.ODD_COUNT);
		comboCountType.addItem(type2);

		CommonType type3 = new CommonType("红球质数个数",
				NumCountHotLawBuild.PRIME_COUNT);
		comboCountType.addItem(type3);

		CommonType type4 = new CommonType("红球大数个数",
				NumCountHotLawBuild.BIG_COUNT);
		comboCountType.addItem(type4);

		CommonType type5 = new CommonType("红球小数个数",
				NumCountHotLawBuild.SMALL_COUNT);
		comboCountType.addItem(type5);

		CommonType type6 = new CommonType("红球小区数个数",
				NumCountHotLawBuild.FIRST_ZONE_COUNT);
		comboCountType.addItem(type6);

		CommonType type7 = new CommonType("红球中区数个数",
				NumCountHotLawBuild.SECOND_ZONE_COUNT);
		comboCountType.addItem(type7);

		CommonType type8 = new CommonType("红球大区数个数",
				NumCountHotLawBuild.THIRD_ZONE_COUNT);
		comboCountType.addItem(type8);

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		lawTabPanel = new javax.swing.JTabbedPane();
		basePanel = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		comboForcastNum = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();
		comboSpaceNum = new javax.swing.JComboBox();
		jScrollPane4 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jScrollPane5 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		btnGenerateSql = new javax.swing.JButton();
		btnCreateStats = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		bluePanel = new javax.swing.JPanel();
		btnBlueLose = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtAreaBlueOut = new javax.swing.JTextArea();
		btnBlueIsEvenLose = new javax.swing.JButton();
		btnBlueIsOddLose = new javax.swing.JButton();
		redPanel = new javax.swing.JPanel();
		btnRedLose = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtAreaRedOut = new javax.swing.JTextArea();
		btnRedContinueLose = new javax.swing.JButton();
		btnRedNotContinueLose = new javax.swing.JButton();
		btnRedInLastLose = new javax.swing.JButton();
		tailPanel = new javax.swing.JPanel();
		btnTailLose = new javax.swing.JButton();
		btnTailLose2 = new javax.swing.JButton();
		btnTailLoseNoRepeat = new javax.swing.JButton();
		btnTailLoseRepeat = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		txtAreaTailOut = new javax.swing.JTextArea();
		sumPanel = new javax.swing.JPanel();
		comboSumType = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		btnSumLose = new javax.swing.JButton();
		jScrollPane6 = new javax.swing.JScrollPane();
		txtAreaSumOut = new javax.swing.JTextArea();
		txtSum = new javax.swing.JTextField();
		primePanel = new javax.swing.JPanel();
		btnPrimeCountLose = new javax.swing.JButton();
		jScrollPane7 = new javax.swing.JScrollPane();
		txtAreaPrimeOut = new javax.swing.JTextArea();
		countPanel = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		comboCountType = new javax.swing.JComboBox();
		btnCountLose = new javax.swing.JButton();
		jSeparator3 = new javax.swing.JSeparator();
		jScrollPane8 = new javax.swing.JScrollPane();
		txtAreaCountOut = new javax.swing.JTextArea();
		mulFormulaPanel = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		txtBeginSsqIndex1 = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		txtRightCount1 = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		txtRightCount2 = new javax.swing.JTextField();
		jSeparator4 = new javax.swing.JSeparator();
		btn50Right = new javax.swing.JButton();
		jScrollPane9 = new javax.swing.JScrollPane();
		txtAreaMulFormulaOut = new javax.swing.JTextArea();
		jLabel11 = new javax.swing.JLabel();
		txtContinueRightCount1 = new javax.swing.JTextField();
		jLabel12 = new javax.swing.JLabel();
		txtContinueRightCount2 = new javax.swing.JTextField();
		btnContinueRight = new javax.swing.JButton();
		btnMulFormula1 = new javax.swing.JButton();
		btnGetStatsInfo = new javax.swing.JButton();
		myFilterPanel = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jLabel13 = new javax.swing.JLabel();
		txtExcludeFile1 = new javax.swing.JTextField();
		btnExcludeFileSelect1 = new javax.swing.JButton();
		jLabel14 = new javax.swing.JLabel();
		txtExcludeFile2 = new javax.swing.JTextField();
		btnExcludeFileSelect2 = new javax.swing.JButton();
		jLabel15 = new javax.swing.JLabel();
		txtExcludeFile3 = new javax.swing.JTextField();
		btnExcludeFileSelect3 = new javax.swing.JButton();
		jLabel16 = new javax.swing.JLabel();
		txtExcludeFile4 = new javax.swing.JTextField();
		btnExcludeFileSelect4 = new javax.swing.JButton();
		btnClear1 = new javax.swing.JButton();
		btnClear2 = new javax.swing.JButton();
		btnClear3 = new javax.swing.JButton();
		btnClear4 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		raColRowSame = new javax.swing.JRadioButton();
		raNotColRowSame = new javax.swing.JRadioButton();
		raColSame = new javax.swing.JRadioButton();
		raNotColSame = new javax.swing.JRadioButton();
		raRowSame = new javax.swing.JRadioButton();
		raNotRowSame = new javax.swing.JRadioButton();
		raColRowSpace1 = new javax.swing.JRadioButton();
		raNotColRowSpace1 = new javax.swing.JRadioButton();
		raColSpace1 = new javax.swing.JRadioButton();
		raNotColSpace1 = new javax.swing.JRadioButton();
		raRowSpace1 = new javax.swing.JRadioButton();
		raNotRowSpace1 = new javax.swing.JRadioButton();
		jPanel4 = new javax.swing.JPanel();
		boxStandard = new javax.swing.JCheckBox();
		boxResidueFeature = new javax.swing.JCheckBox();
		boxSum = new javax.swing.JCheckBox();
		boxAC = new javax.swing.JCheckBox();
		boxSandu = new javax.swing.JCheckBox();
		boxHistoryFeature = new javax.swing.JCheckBox();
		boxLoseRowCol = new javax.swing.JCheckBox();
		boxLoseRowColFeature = new javax.swing.JCheckBox();
		boxLoseRowColSpecial = new javax.swing.JCheckBox();
		boxFormula = new javax.swing.JCheckBox();
		btnFilter = new javax.swing.JButton();
		otherPanel = new javax.swing.JPanel();
		btnRedTxt = new javax.swing.JButton();
		btnBlueTxt = new javax.swing.JButton();
		btnGetColLose = new javax.swing.JButton();
		btnGetRowLose = new javax.swing.JButton();
		btnMergeColRowLose = new javax.swing.JButton();
		btnHistory = new javax.swing.JButton();
		btnMulFormula = new javax.swing.JButton();
		labCurrentSsqIndex = new javax.swing.JLabel();
		txtCurrentSsqIndex = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u89c4\u5f8b\u5c55\u793a");

		jLabel1.setText("forcastNum");

		comboForcastNum.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboForcastNumActionPerformed(evt);
			}
		});

		jLabel2.setText("spaceNum");

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jTextArea2.setName("txtResult");
		jScrollPane4.setViewportView(jTextArea2);

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jTextArea1.setName("txt1");
		jScrollPane5.setViewportView(jTextArea1);

		btnGenerateSql.setText("\u4ea7\u751fsql");
		btnGenerateSql.setName("btnGen");
		btnGenerateSql.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGenerateSqlActionPerformed(evt);
			}
		});

		btnCreateStats.setText("\u751f\u6210\u7edf\u8ba1\u4fe1\u606f");
		btnCreateStats.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCreateStatsActionPerformed(evt);
			}
		});

		jLabel3.setText("\u8f93\u51fa\u533a\u57df\uff1a");

		jLabel4.setText("\u8f93\u5165\u533a\u57df\uff1a");

		javax.swing.GroupLayout basePanelLayout = new javax.swing.GroupLayout(
				basePanel);
		basePanel.setLayout(basePanelLayout);
		basePanelLayout
				.setHorizontalGroup(basePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jSeparator1,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 1395,
								Short.MAX_VALUE)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								basePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPane5,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												1371, Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								basePanelLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel3)
										.addContainerGap(1323, Short.MAX_VALUE))
						.addGroup(
								basePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												basePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																basePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				77,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				comboForcastNum,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(126,
																				126,
																				126)
																		.addComponent(
																				jLabel2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				71,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				comboSpaceNum,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				110,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																basePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnCreateStats)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnGenerateSql)))
										.addContainerGap(876, Short.MAX_VALUE))
						.addGroup(
								basePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPane4,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												1371, Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								basePanelLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel4)
										.addContainerGap(1323, Short.MAX_VALUE)));
		basePanelLayout
				.setVerticalGroup(basePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								basePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												basePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(jLabel2)
														.addComponent(
																comboForcastNum,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																comboSpaceNum,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jSeparator1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												10,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												basePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnGenerateSql)
														.addComponent(
																btnCreateStats))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabel4)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												73,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jLabel3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												269,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(65, 65, 65)));

		lawTabPanel.addTab("\u6784\u9020\u89c4\u5f8b\u57fa\u7840\u6570\u636e",
				basePanel);

		btnBlueLose.setText("\u84dd\u7403\u9057\u6f0f");
		btnBlueLose.setName("btnblueLose");
		btnBlueLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBlueLoseActionPerformed(evt);
			}
		});

		txtAreaBlueOut.setColumns(20);
		txtAreaBlueOut.setRows(5);
		jScrollPane2.setViewportView(txtAreaBlueOut);

		btnBlueIsEvenLose
				.setText("\u84dd\u7403\u662f\u5426\u5076\u6570\u9057\u6f0f");
		btnBlueIsEvenLose
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnBlueIsEvenLoseActionPerformed(evt);
					}
				});

		btnBlueIsOddLose
				.setText("\u84dd\u7403\u662f\u5426\u5947\u6570\u9057\u6f0f");
		btnBlueIsOddLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBlueIsOddLoseActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout bluePanelLayout = new javax.swing.GroupLayout(
				bluePanel);
		bluePanel.setLayout(bluePanelLayout);
		bluePanelLayout
				.setHorizontalGroup(bluePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								bluePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												bluePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																bluePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnBlueLose)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnBlueIsEvenLose)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnBlueIsOddLose))
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1371,
																Short.MAX_VALUE))
										.addContainerGap()));
		bluePanelLayout
				.setVerticalGroup(bluePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								bluePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												bluePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnBlueLose)
														.addComponent(
																btnBlueIsEvenLose)
														.addComponent(
																btnBlueIsOddLose))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												447, Short.MAX_VALUE)
										.addContainerGap()));

		lawTabPanel.addTab("\u84dd\u7403\u9057\u6f0f", bluePanel);

		btnRedLose.setText("\u7ea2\u7403\u9057\u6f0f");
		btnRedLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRedLoseActionPerformed(evt);
			}
		});

		txtAreaRedOut.setColumns(20);
		txtAreaRedOut.setRows(5);
		jScrollPane1.setViewportView(txtAreaRedOut);

		btnRedContinueLose.setText("\u7ea2\u7403\u8fde\u7eed\u9057\u6f0f");
		btnRedContinueLose
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnRedContinueLoseActionPerformed(evt);
					}
				});

		btnRedNotContinueLose
				.setText("\u7ea2\u7403\u4e0d\u8fde\u7eed\u9057\u6f0f");
		btnRedNotContinueLose
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnRedNotContinueLoseActionPerformed(evt);
					}
				});

		btnRedInLastLose
				.setText("\u4e0a\u671f\u76f8\u540c\u4e2a\u6570\u9057\u6f0f");
		btnRedInLastLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRedInLastLoseActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout redPanelLayout = new javax.swing.GroupLayout(
				redPanel);
		redPanel.setLayout(redPanelLayout);
		redPanelLayout
				.setHorizontalGroup(redPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								redPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												redPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1371,
																Short.MAX_VALUE)
														.addGroup(
																redPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnRedLose)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnRedContinueLose)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnRedNotContinueLose)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnRedInLastLose)))
										.addContainerGap()));
		redPanelLayout
				.setVerticalGroup(redPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								redPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												redPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnRedLose)
														.addComponent(
																btnRedContinueLose)
														.addComponent(
																btnRedNotContinueLose)
														.addComponent(
																btnRedInLastLose))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												447, Short.MAX_VALUE)
										.addContainerGap()));

		lawTabPanel.addTab("\u7ea2\u7403\u9057\u6f0f", redPanel);

		btnTailLose.setText("\u5c3e\u6570\u4e2a\u65701\u9057\u6f0f");
		btnTailLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTailLoseActionPerformed(evt);
			}
		});

		btnTailLose2.setText("\u5c3e\u6570\u4e2a\u65702\u9057\u6f0f");
		btnTailLose2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTailLose2ActionPerformed(evt);
			}
		});

		btnTailLoseNoRepeat
				.setText("\u5c3e\u6570\u4e0d\u91cd\u590d\u9057\u6f0f");
		btnTailLoseNoRepeat
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnTailLoseNoRepeatActionPerformed(evt);
					}
				});

		btnTailLoseRepeat.setText("\u5c3e\u6570\u91cd\u590d\u9057\u6f0f");
		btnTailLoseRepeat
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnTailLoseRepeatActionPerformed(evt);
					}
				});

		txtAreaTailOut.setColumns(20);
		txtAreaTailOut.setRows(5);
		jScrollPane3.setViewportView(txtAreaTailOut);

		javax.swing.GroupLayout tailPanelLayout = new javax.swing.GroupLayout(
				tailPanel);
		tailPanel.setLayout(tailPanelLayout);
		tailPanelLayout
				.setHorizontalGroup(tailPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tailPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												tailPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1371,
																Short.MAX_VALUE)
														.addGroup(
																tailPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnTailLose)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnTailLose2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnTailLoseNoRepeat)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnTailLoseRepeat)))
										.addContainerGap()));
		tailPanelLayout
				.setVerticalGroup(tailPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tailPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												tailPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnTailLose)
														.addComponent(
																btnTailLose2)
														.addComponent(
																btnTailLoseNoRepeat)
														.addComponent(
																btnTailLoseRepeat))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												447, Short.MAX_VALUE)
										.addContainerGap()));

		lawTabPanel.addTab("\u5c3e\u6570\u9057\u6f0f", tailPanel);

		jLabel5.setText("\u548c\u503c\u7c7b\u578b\uff1a");

		jLabel6.setText("\u548c\u503c\uff1a");

		btnSumLose.setText("\u548c\u503c\u9057\u6f0f");
		btnSumLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSumLoseActionPerformed(evt);
			}
		});

		txtAreaSumOut.setColumns(20);
		txtAreaSumOut.setRows(5);
		jScrollPane6.setViewportView(txtAreaSumOut);

		javax.swing.GroupLayout sumPanelLayout = new javax.swing.GroupLayout(
				sumPanel);
		sumPanel.setLayout(sumPanelLayout);
		sumPanelLayout
				.setHorizontalGroup(sumPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								sumPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel5)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												comboSumType,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												158,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(65, 65, 65)
										.addComponent(jLabel6)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												txtSum,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												201,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(853, Short.MAX_VALUE))
						.addComponent(jSeparator2,
								javax.swing.GroupLayout.DEFAULT_SIZE, 1395,
								Short.MAX_VALUE)
						.addGroup(
								sumPanelLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnSumLose)
										.addContainerGap(1302, Short.MAX_VALUE))
						.addGroup(
								sumPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPane6,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												1371, Short.MAX_VALUE)
										.addContainerGap()));
		sumPanelLayout
				.setVerticalGroup(sumPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								sumPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												sumPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(jLabel6)
														.addComponent(
																comboSumType,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtSum,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jSeparator2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												10,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnSumLose)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane6,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												398, Short.MAX_VALUE)
										.addContainerGap()));

		lawTabPanel.addTab("\u548c\u503c\u9057\u6f0f", sumPanel);

		btnPrimeCountLose.setText("\u8d28\u6570\u4e2a\u6570\u9057\u6f0f");
		btnPrimeCountLose
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnPrimeCountLoseActionPerformed(evt);
					}
				});

		txtAreaPrimeOut.setColumns(20);
		txtAreaPrimeOut.setRows(5);
		jScrollPane7.setViewportView(txtAreaPrimeOut);

		javax.swing.GroupLayout primePanelLayout = new javax.swing.GroupLayout(
				primePanel);
		primePanel.setLayout(primePanelLayout);
		primePanelLayout
				.setHorizontalGroup(primePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								primePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												primePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane7,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1371,
																Short.MAX_VALUE)
														.addComponent(
																btnPrimeCountLose))
										.addContainerGap()));
		primePanelLayout
				.setVerticalGroup(primePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								primePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnPrimeCountLose)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane7,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												447, Short.MAX_VALUE)
										.addContainerGap()));

		lawTabPanel.addTab("\u8d28\u6570\u9057\u6f0f", primePanel);

		jLabel7.setText("\u4e2a\u6570\u7c7b\u578b\uff1a");

		btnCountLose.setText("\u4e2a\u6570\u9057\u6f0f");
		btnCountLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCountLoseActionPerformed(evt);
			}
		});

		txtAreaCountOut.setColumns(20);
		txtAreaCountOut.setRows(5);
		jScrollPane8.setViewportView(txtAreaCountOut);

		javax.swing.GroupLayout countPanelLayout = new javax.swing.GroupLayout(
				countPanel);
		countPanel.setLayout(countPanelLayout);
		countPanelLayout
				.setHorizontalGroup(countPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								countPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												countPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																countPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane8,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				1371,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																countPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel7)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				comboCountType,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				158,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap(
																				1160,
																				Short.MAX_VALUE))
														.addComponent(
																btnCountLose)))
						.addComponent(jSeparator3,
								javax.swing.GroupLayout.DEFAULT_SIZE, 1395,
								Short.MAX_VALUE));
		countPanelLayout
				.setVerticalGroup(countPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								countPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												countPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(
																comboCountType,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jSeparator3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												10,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnCountLose)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane8,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												398, Short.MAX_VALUE)
										.addContainerGap()));

		lawTabPanel.addTab("\u4e2a\u6570\u9057\u6f0f", countPanel);

		jLabel8.setText("\u5f00\u59cb\u671f\uff1a");

		jLabel9.setText("\u6b63\u786e\u671f\u65701\uff1a");

		jLabel10.setText("\u6b63\u786e\u671f\u65702\uff1a");

		btn50Right.setText("50\u671f\u5185\u6b63\u786e\u89c4\u5f8b");
		btn50Right.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn50RightActionPerformed(evt);
			}
		});

		txtAreaMulFormulaOut.setColumns(20);
		txtAreaMulFormulaOut.setRows(5);
		jScrollPane9.setViewportView(txtAreaMulFormulaOut);

		jLabel11.setText("\u8fde\u7eed\u6b63\u786e\u671f\u65701\uff1a");

		jLabel12.setText("\u8fde\u7eed\u6b63\u786e\u671f\u65702\uff1a");

		btnContinueRight.setText("\u8fde\u7eed\u6b63\u786e\u89c4\u5f8b");
		btnContinueRight.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnContinueRightActionPerformed(evt);
			}
		});

		btnMulFormula1.setText("\u591a\u671f\u7edf\u8ba1\u4fe1\u606f");
		btnMulFormula1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMulFormula1ActionPerformed(evt);
			}
		});

		btnGetStatsInfo.setText("\u83b7\u53d6\u7edf\u8ba1\u4fe1\u606f");
		btnGetStatsInfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGetStatsInfoActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout mulFormulaPanelLayout = new javax.swing.GroupLayout(
				mulFormulaPanel);
		mulFormulaPanel.setLayout(mulFormulaPanelLayout);
		mulFormulaPanelLayout
				.setHorizontalGroup(mulFormulaPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mulFormulaPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel8)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												txtBeginSsqIndex1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												81,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jLabel9)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												txtRightCount1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												81,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jLabel10)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												txtRightCount2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												81,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)
										.addComponent(jLabel11)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												txtContinueRightCount1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												81,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabel12)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												txtContinueRightCount2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												81,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(493, Short.MAX_VALUE))
						.addComponent(jSeparator4,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 1395,
								Short.MAX_VALUE)
						.addGroup(
								mulFormulaPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btn50Right)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnContinueRight)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnMulFormula1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnGetStatsInfo)
										.addContainerGap(928, Short.MAX_VALUE))
						.addGroup(
								mulFormulaPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPane9,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												1371, Short.MAX_VALUE)
										.addContainerGap()));
		mulFormulaPanelLayout
				.setVerticalGroup(mulFormulaPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mulFormulaPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												mulFormulaPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																txtBeginSsqIndex1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel9)
														.addComponent(
																txtRightCount1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel10)
														.addComponent(
																txtRightCount2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel11)
														.addComponent(
																txtContinueRightCount1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel12)
														.addComponent(
																txtContinueRightCount2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jSeparator4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												10,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												mulFormulaPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btn50Right)
														.addComponent(
																btnContinueRight)
														.addComponent(
																btnMulFormula1)
														.addComponent(
																btnGetStatsInfo))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane9,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												401, Short.MAX_VALUE)
										.addContainerGap()));

		lawTabPanel.addTab("\u591a\u671f\u516c\u5f0f", mulFormulaPanel);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "\u6392\u9664"));

		jLabel13.setText("\u6392\u9664\u6587\u4ef61\uff1a");

		txtExcludeFile1.setEditable(false);

		btnExcludeFileSelect1.setText("\u9009\u62e9");
		btnExcludeFileSelect1
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnExcludeFileSelect1ActionPerformed(evt);
					}
				});

		jLabel14.setText("\u6392\u9664\u6587\u4ef62\uff1a");

		txtExcludeFile2.setEditable(false);

		btnExcludeFileSelect2.setText("\u9009\u62e9");
		btnExcludeFileSelect2
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnExcludeFileSelect2ActionPerformed(evt);
					}
				});

		jLabel15.setText("\u6392\u9664\u6587\u4ef63\uff1a");

		txtExcludeFile3.setEditable(false);

		btnExcludeFileSelect3.setText("\u9009\u62e9");
		btnExcludeFileSelect3
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnExcludeFileSelect3ActionPerformed(evt);
					}
				});

		jLabel16.setText("\u6392\u9664\u6587\u4ef64\uff1a");

		txtExcludeFile4.setEditable(false);

		btnExcludeFileSelect4.setText("\u9009\u62e9");
		btnExcludeFileSelect4
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnExcludeFileSelect4ActionPerformed(evt);
					}
				});

		btnClear1.setText("\u6e05\u9664");
		btnClear1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClear1ActionPerformed(evt);
			}
		});

		btnClear2.setText("\u6e05\u9664");
		btnClear2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClear2ActionPerformed(evt);
			}
		});

		btnClear3.setText("\u6e05\u9664");
		btnClear3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClear3ActionPerformed(evt);
			}
		});

		btnClear4.setText("\u6e05\u9664");
		btnClear4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClear4ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel13)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtExcludeFile1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				189,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExcludeFileSelect1))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel14)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtExcludeFile2,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				189,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExcludeFileSelect2))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel15)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtExcludeFile3,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				189,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExcludeFileSelect3))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel16)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtExcludeFile4,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				189,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExcludeFileSelect4)))
										.addGap(31, 31, 31)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(btnClear1)
														.addComponent(btnClear4)
														.addComponent(btnClear3)
														.addComponent(btnClear2))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel13)
																						.addComponent(
																								btnExcludeFileSelect1)
																						.addComponent(
																								txtExcludeFile1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel14)
																						.addComponent(
																								btnExcludeFileSelect2)
																						.addComponent(
																								txtExcludeFile2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel15)
																						.addComponent(
																								btnExcludeFileSelect3)
																						.addComponent(
																								txtExcludeFile3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel16)
																						.addComponent(
																								btnExcludeFileSelect4)
																						.addComponent(
																								txtExcludeFile4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				btnClear1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnClear2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnClear3)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnClear4)))
										.addContainerGap(18, Short.MAX_VALUE)));

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u884c\u5217\u9057\u6f0f"));

		buttonGroup1.add(raColRowSame);
		raColRowSame.setSelected(true);
		raColRowSame.setText("\u884c\u5217\u76f8\u540c");

		buttonGroup1.add(raNotColRowSame);
		raNotColRowSame.setText("\u884c\u5217\u4e0d\u76f8\u540c");

		buttonGroup1.add(raColSame);
		raColSame.setText("\u5217\u8fde\u7eed");

		buttonGroup1.add(raNotColSame);
		raNotColSame.setText("\u5217\u4e0d\u8fde\u7eed");

		buttonGroup1.add(raRowSame);
		raRowSame.setText("\u884c\u8fde\u7eed");

		buttonGroup1.add(raNotRowSame);
		raNotRowSame.setText("\u884c\u4e0d\u8fde\u7eed");

		buttonGroup1.add(raColRowSpace1);
		raColRowSpace1.setText("\u884c\u5217\u5dee\u503c\u4e3a1");

		buttonGroup1.add(raNotColRowSpace1);
		raNotColRowSpace1.setText("\u884c\u5217\u5dee\u503c\u4e0d\u4e3a1");

		buttonGroup1.add(raColSpace1);
		raColSpace1.setText("\u5217\u5dee\u503c\u4e3a1");

		buttonGroup1.add(raNotColSpace1);
		raNotColSpace1.setText("\u5217\u5dee\u503c\u4e0d\u4e3a1");

		buttonGroup1.add(raRowSpace1);
		raRowSpace1.setText("\u884c\u5dee\u503c\u4e3a1");

		buttonGroup1.add(raNotRowSpace1);
		raNotRowSpace1.setText("\u884c\u5dee\u503c\u4e0d\u4e3a1");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addGap(23, 23, 23)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																raNotRowSame)
														.addComponent(raRowSame)
														.addComponent(
																raNotColSame)
														.addComponent(raColSame)
														.addComponent(
																raNotColRowSame)
														.addComponent(
																raColRowSame))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												208, Short.MAX_VALUE)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																raNotRowSpace1)
														.addComponent(
																raRowSpace1)
														.addComponent(
																raNotColSpace1)
														.addComponent(
																raColSpace1)
														.addComponent(
																raNotColRowSpace1)
														.addComponent(
																raColRowSpace1))
										.addGap(16, 16, 16)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				raColRowSpace1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raNotColRowSpace1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raColSpace1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raNotColSpace1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raRowSpace1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raNotRowSpace1))
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				raColRowSame)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raNotColRowSame)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raColSame)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raNotColSame)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raRowSame)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				raNotRowSame)))
										.addContainerGap(34, Short.MAX_VALUE)));

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "\u8fc7\u6ee4"));

		boxStandard.setSelected(true);
		boxStandard.setText("\u6807\u51c6");

		boxResidueFeature.setSelected(true);
		boxResidueFeature.setText("\u4f59\u6570\u7279\u5f81");

		boxSum.setSelected(true);
		boxSum.setText("\u548c\u503c");

		boxAC.setSelected(true);
		boxAC.setText("AC\u503c");

		boxSandu.setSelected(true);
		boxSandu.setText("\u6563\u5ea6");

		boxHistoryFeature.setSelected(true);
		boxHistoryFeature.setText("\u5386\u53f2\u7279\u5f81\u503c");

		boxLoseRowCol.setSelected(true);
		boxLoseRowCol.setText("\u884c\u5217\u9057\u6f0f");

		boxLoseRowColFeature.setSelected(true);
		boxLoseRowColFeature.setText("\u884c\u5217\u9057\u6f0f\u7279\u5f81");

		boxLoseRowColSpecial.setSelected(true);
		boxLoseRowColSpecial.setText("\u884c\u5217\u9057\u6f0f\u7279\u6b8a");

		boxFormula.setSelected(true);
		boxFormula.setText("\u516c\u5f0f");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																boxStandard)
														.addComponent(
																boxResidueFeature)
														.addComponent(boxSum)
														.addComponent(boxAC)
														.addComponent(boxSandu)
														.addComponent(
																boxHistoryFeature)
														.addComponent(
																boxLoseRowCol)
														.addComponent(
																boxLoseRowColFeature)
														.addComponent(
																boxLoseRowColSpecial)
														.addComponent(
																boxFormula))
										.addContainerGap(182, Short.MAX_VALUE)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addComponent(boxStandard)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxResidueFeature)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxSum)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxAC)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxSandu)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxHistoryFeature)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxLoseRowCol)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxLoseRowColFeature)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxLoseRowColSpecial)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(boxFormula)
										.addContainerGap(142, Short.MAX_VALUE)));

		btnFilter.setText("\u8fc7\u6ee4");
		btnFilter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFilterActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout myFilterPanelLayout = new javax.swing.GroupLayout(
				myFilterPanel);
		myFilterPanel.setLayout(myFilterPanelLayout);
		myFilterPanelLayout
				.setHorizontalGroup(myFilterPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								myFilterPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												myFilterPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnFilter)
										.addContainerGap(559, Short.MAX_VALUE)));
		myFilterPanelLayout
				.setVerticalGroup(myFilterPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								myFilterPanelLayout
										.createSequentialGroup()
										.addGroup(
												myFilterPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																myFilterPanelLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				myFilterPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								myFilterPanelLayout
																										.createSequentialGroup()
																										.addComponent(
																												jPanel1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(27,
																												27,
																												27)
																										.addComponent(
																												jPanel3,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								jPanel4,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addGroup(
																myFilterPanelLayout
																		.createSequentialGroup()
																		.addGap(23,
																				23,
																				23)
																		.addComponent(
																				btnFilter)))
										.addContainerGap(73, Short.MAX_VALUE)));

		lawTabPanel.addTab("\u6211\u7684\u8fc7\u6ee4", myFilterPanel);

		btnRedTxt.setText("red.txt");
		btnRedTxt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRedTxtActionPerformed(evt);
			}
		});

		btnBlueTxt.setText("blue.txt");
		btnBlueTxt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBlueTxtActionPerformed(evt);
			}
		});

		btnGetColLose.setText("testCol.txt");
		btnGetColLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGetColLoseActionPerformed(evt);
			}
		});

		btnGetRowLose.setText("testRow.txt");
		btnGetRowLose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGetRowLoseActionPerformed(evt);
			}
		});

		btnMergeColRowLose.setText("colrow.txt");
		btnMergeColRowLose
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnMergeColRowLoseActionPerformed(evt);
					}
				});

		btnHistory.setText("historyFeatureAnyorder.txt");
		btnHistory.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHistoryActionPerformed(evt);
			}
		});

		btnMulFormula.setText("\u591a\u671f\u516c\u5f0f\u7edf\u8ba1.txt");
		btnMulFormula.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMulFormulaActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout otherPanelLayout = new javax.swing.GroupLayout(
				otherPanel);
		otherPanel.setLayout(otherPanelLayout);
		otherPanelLayout
				.setHorizontalGroup(otherPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								otherPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnRedTxt)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnBlueTxt)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnGetColLose)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnGetRowLose)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnMergeColRowLose)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnHistory)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnMulFormula)
										.addContainerGap(618, Short.MAX_VALUE)));
		otherPanelLayout
				.setVerticalGroup(otherPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								otherPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												otherPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnRedTxt)
														.addComponent(
																btnBlueTxt)
														.addComponent(
																btnGetColLose)
														.addComponent(
																btnGetRowLose)
														.addComponent(
																btnMergeColRowLose)
														.addComponent(
																btnHistory)
														.addComponent(
																btnMulFormula))
										.addContainerGap(469, Short.MAX_VALUE)));

		lawTabPanel.addTab("\u5176\u4ed6", otherPanel);

		labCurrentSsqIndex.setText("\u5f53\u524d\u671f\uff1a");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(1257, Short.MAX_VALUE)
								.addComponent(labCurrentSsqIndex)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtCurrentSsqIndex,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										78,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addComponent(lawTabPanel,
						javax.swing.GroupLayout.DEFAULT_SIZE, 1400,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtCurrentSsqIndex,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														labCurrentSsqIndex))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lawTabPanel,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										539, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnClear4ActionPerformed(java.awt.event.ActionEvent evt) {
		clearExcludeFile(txtExcludeFile4);
	}

	private void btnClear3ActionPerformed(java.awt.event.ActionEvent evt) {
		clearExcludeFile(txtExcludeFile3);
	}

	private void btnClear2ActionPerformed(java.awt.event.ActionEvent evt) {
		clearExcludeFile(txtExcludeFile2);
	}

	private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {
		clearExcludeFile(txtExcludeFile1);
	}

	private void btnExcludeFileSelect4ActionPerformed(
			java.awt.event.ActionEvent evt) {
		selectExcludeFile(txtExcludeFile4);
	}

	private void btnExcludeFileSelect3ActionPerformed(
			java.awt.event.ActionEvent evt) {
		selectExcludeFile(txtExcludeFile3);
	}

	private void btnExcludeFileSelect2ActionPerformed(
			java.awt.event.ActionEvent evt) {
		selectExcludeFile(txtExcludeFile2);
	}

	private void btnExcludeFileSelect1ActionPerformed(
			java.awt.event.ActionEvent evt) {
		selectExcludeFile(txtExcludeFile1);
	}

	public void selectExcludeFile(JTextField txtFile) {
		File path = new File("d:/ddd/");
		JFileChooser fChooser = new JFileChooser(path);

		fChooser.setDialogTitle("选择文件");
		int returnVal = fChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String filePathAndName = fChooser.getSelectedFile().getPath();
			txtFile.setText(filePathAndName);
		}
	}

	public void clearExcludeFile(JTextField txtFile) {
		txtFile.setText("");
	}

	/**
	 * 获取为微信准备的统计信息
	 * @param evt
	 */
	private void btnGetStatsInfoActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = StatsLawBuild.buildStatsForWeixin(ssqIndex);

		txtAreaMulFormulaOut.setText("");
		for (String str1 : result) {
			txtAreaMulFormulaOut.append(str1 + "\n");
		}
	}

	private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {
		FilterChain chain = new FilterChain();

		// 标准
		if (boxStandard.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil
						.buildAStandardFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 余数特征
		if (boxResidueFeature.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil
						.buildAResidueFeatureFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 和值
		if (boxSum.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil.buildASumFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// AC值
		if (boxAC.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil.buildAACFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 散度值
		if (boxSandu.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil.buildASanduFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 历史特征
		if (boxHistoryFeature.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil
						.buildAHistoryFeatureFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 行列遗漏数目
		if (boxLoseRowCol.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil
						.buildALoseRowColFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 行列遗漏特征
		if (boxLoseRowColFeature.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil
						.buildALoseRowColFeatureFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 行列遗漏特殊
		if (boxLoseRowColSpecial.isSelected()) {
			try {

				int type = 1;
				if (raColRowSame.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.COL_ROW_SAME;
				} else if (raNotColRowSame.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.NOT_COL_ROW_SAME;
				} else if (raColSame.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.COL_SAME;
				} else if (raNotColSame.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.NOT_COL_SAME;
				} else if (raRowSame.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.ROW_SAME;
				} else if (raNotRowSame.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.NOT_ROW_SAME;
				}

				else if (raColRowSpace1.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.COL_ROW_SPACE_1;
				} else if (raNotColRowSpace1.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.NOT_COL_ROW_SPACE_1;
				} else if (raColSpace1.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.COL_SPACE_1;
				} else if (raNotColSpace1.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.NOT_COL_SPACE_1;
				} else if (raRowSpace1.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.ROW_SPACE_1;
				} else if (raNotRowSpace1.isSelected()) {
					type = LoseRowColFeatureSameOrDiffFilter.NOT_ROW_SPACE_1;
				}

				int ssqIndex = getCurrentSsqIndex();
				SsqRecord record = ssqRecordDao
						.findSsqRecordBySsqIndex(ssqIndex);

				LoseRowColFeatureSameOrDiffFilter filter = new LoseRowColFeatureSameOrDiffFilter(
						type);

				int r1 = record.getR1();
				int r2 = record.getR2();
				int r3 = record.getR3();
				int r4 = record.getR4();
				int r5 = record.getR5();
				int r6 = record.getR6();

				String lastColLoseStr = filter.buildColFeatureStr(r1, r2, r3,
						r4, r5, r6);
				String lastRowLoseStr = filter.buildRowFeatureStr(r1, r2, r3,
						r4, r5, r6);
				filter.setLastColLoseStr(lastColLoseStr);
				filter.setLastRowLoseStr(lastRowLoseStr);

				System.out.println(lastColLoseStr + "---" + lastRowLoseStr);
				FilterChain chain1 = new FilterChain(filter);

				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 行列遗漏特殊
		if (boxFormula.isSelected()) {
			try {
				FilterChain chain1 = FilterChainUtil.buildAFormulaFilterChain();
				chain = move(chain, chain1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// 指针移到第一位
		chain = FilterChainUtil.makePoint2First(chain);

		List<String> sourceList = null;
//		List<String> excludeList = null;
//		List<String> excludeThirdList = null;
		List<List<String>> excludeList1 = new ArrayList<List<String>>(); 
		try {
			sourceList = FileUtil
					.readLineFileWithoutCheckRepeat("C:/Users/Administrator/Documents/编辑2.TXT");
//			excludeList = FileUtil
//					.readLineFileWithoutCheckRepeat(CommonConstant.FILTER_PATH
//							+ "red-exclude.TXT");
//			excludeThirdList = FileUtil
//					.readLineFileWithoutCheckRepeat(CommonConstant.FILTER_PATH
//							+ "red-exclude-third-1.txt");
			
			fillExcludeList(excludeList1,txtExcludeFile1);
			fillExcludeList(excludeList1,txtExcludeFile2);
			fillExcludeList(excludeList1,txtExcludeFile3);
			fillExcludeList(excludeList1,txtExcludeFile4);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

//		// 标准排除库
//		SsqUtils.excludeRedBets(sourceList, excludeList);
//
//		// 第三方排除库
//		SsqUtils.excludeRedBets(sourceList, excludeThirdList);
		
		for(List<String> tmp:excludeList1) {
			SsqUtils.excludeRedBets(sourceList, tmp);
		}

		// 公式过滤
		for (int i = sourceList.size() - 1; i >= 0; i--) {
			if (chain.doFilter(sourceList.get(i))) {
				sourceList.remove(i);
			}
		}

		try {
			FileUtil.writeToFile("C:/Users/Administrator/Documents/编辑3.TXT",
					sourceList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ok");
	}

	private void fillExcludeList(List<List<String>> excludeList1, JTextField txt)
			throws IOException {
		if(txt.getText().trim().length()>0) {
			String fileName = txt.getText().trim();
			excludeList1.add(FileUtil.readLineFileWithoutCheckRepeat(fileName));
		}
	}

	private FilterChain move(FilterChain chain, FilterChain chain1) {
		chain1 = FilterChainUtil.makePoint2First(chain1);

		chain.setNextNode(chain1);

		// 指针移到最后一位
		chain = FilterChainUtil.makePoint2End(chain);
		return chain;
	}

	private void btnMulFormula1ActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = MulFormulaLawBuild2.buildMulLaw(ssqIndex);

		txtAreaMulFormulaOut.setText("");
		for (String str1 : result) {
			txtAreaMulFormulaOut.append(str1 + "\n");
		}
	}

	/**
	 * 连续正确规律
	 * @param evt
	 */
	private void btnContinueRightActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();

		int continueRightCount1 = Integer.parseInt(txtContinueRightCount1
				.getText().trim());
		int continueRightCount2 = Integer.parseInt(txtContinueRightCount2
				.getText().trim());

		List<String> result = MulFormulaLawBuild.buildMulFormulaContinueRight1(
				ssqIndex, continueRightCount1, continueRightCount2);

		txtAreaMulFormulaOut.setText("");
		for (String str1 : result) {
			txtAreaMulFormulaOut.append(str1 + "\n");
		}
	}

	private void btn50RightActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex1 = Integer.parseInt(txtBeginSsqIndex1.getText().trim());
		int ssqIndex2 = getCurrentSsqIndex();

		int rightCount1 = Integer.parseInt(txtRightCount1.getText().trim());
		int rightCount2 = Integer.parseInt(txtRightCount2.getText().trim());

		List<String> result = MulFormulaLawBuild.buildMulFormula50Right(
				ssqIndex1, ssqIndex2, rightCount1, rightCount2);

		txtAreaMulFormulaOut.setText("");
		for (String str1 : result) {
			txtAreaMulFormulaOut.append(str1 + "\n");
		}
	}

	private void btnMulFormulaActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = MulFormulaLawBuild.buildMulFormulaContinueRight1(
				ssqIndex, 13, 100);
		String fileName = "C:\\Users\\Administrator\\Documents\\多期公式统计.txt";
		try {
			FileUtil.writeToFile(fileName, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 个数遗漏
	 * @param evt
	 */
	private void btnCountLoseActionPerformed(java.awt.event.ActionEvent evt) {
		String countType = ((CommonType) comboCountType.getSelectedItem())
				.getValue();
		int ssqIndex = getCurrentSsqIndex();

		List<String> result = new ArrayList<String>();
		result = NumCountHotLawBuild.buildNumCount(countType, ssqIndex);

		txtAreaCountOut.setText("");
		for (String str1 : result) {
			txtAreaCountOut.append(str1 + "\n");
		}
	}

	private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {
		TestHistoryFeatureForAnyOrder.buildHistoryFeatureAnyOrder();
	}

	private void btnRedInLastLoseActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = RedHotLawBuild.buildRedHotLastSameLaw(ssqIndex);

		txtAreaRedOut.setText("");
		for (String str1 : result) {
			txtAreaRedOut.append(str1 + "\n");
		}
	}

	private void btnPrimeCountLoseActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = PrimeHotLawBuild.buildPrimeCountHotLaw(ssqIndex);

		txtAreaPrimeOut.setText("");
		for (String str1 : result) {
			txtAreaPrimeOut.append(str1 + "\n");
		}
	}

	private void btnMergeColRowLoseActionPerformed(
			java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		TestMergeColRow.mergeColRowLose(ssqIndex);
	}

	private void btnGetRowLoseActionPerformed(java.awt.event.ActionEvent evt) {
		String fileName = "C:\\Users\\Administrator\\Documents\\testRow.TXT";
		TestBuildLoseRowCol
				.buildFeature(TestBuildLoseRowCol.ROW_TYPE, fileName);
	}

	private void btnGetColLoseActionPerformed(java.awt.event.ActionEvent evt) {
		String fileName = "C:\\Users\\Administrator\\Documents\\testCol.TXT";
		TestBuildLoseRowCol
				.buildFeature(TestBuildLoseRowCol.COL_TYPE, fileName);
	}

	private void btnSumLoseActionPerformed(java.awt.event.ActionEvent evt) {
		String sumType = ((CommonType) comboSumType.getSelectedItem())
				.getValue();
		int ssqIndex = getCurrentSsqIndex();

		List<String> result = new ArrayList<String>();
		if (txtSum.getText().trim().length() == 0) {
			result = SumHotLawBuild.buildSum(sumType, ssqIndex);
		} else {
			String[] strs = txtSum.getText().trim().split(",");
			for (String str : strs) {
				int sum = Integer.parseInt(str);
				result.addAll(SumHotLawBuild.buildSum(sumType, ssqIndex, sum));
				result.add("\n");
			}
		}

		txtAreaSumOut.setText("");
		for (String str1 : result) {
			txtAreaSumOut.append(str1 + "\n");
		}
	}

	private void btnCreateStatsActionPerformed(java.awt.event.ActionEvent evt) {
		TestUIHelper.buildSSqRecordAndStats();
	}

	private void btnGenerateSqlActionPerformed(java.awt.event.ActionEvent evt) {
		jTextArea2.setText("");
		String tmp = jTextArea1.getText().trim();
		if (tmp.length() == 0) {
			return;
		}

		int forcastNum = ((Integer) comboForcastNum.getSelectedItem())
				.intValue();
		int spaceNum = ((Integer) comboSpaceNum.getSelectedItem()).intValue();
		int currentIndex = Integer
				.parseInt(txtCurrentSsqIndex.getText().trim());

		String[] tmps = tmp.split(",");

		String[] str1 = new String[4];
		for (int i = 0; i < str1.length; i++) {
			str1[i] = "select  ffromssqindex,ftossqindex \n";
		}

		String str2 = "select  ffromssqindex,ftossqindex \n";

		for (int i = 0; i < tmps.length; i++) {
			String nums = tmps[i].substring(1);
			str1[0] = str1[0] + ",max(fformula" + nums
					+ "krcount) , min(fformula" + nums + "krcount) \n";

			str1[1] = str1[1] + ",max(fformula" + nums
					+ "krcount) , min(fformula" + nums + "krcount) \n";

			str1[2] = str1[2] + ",max(fformula" + nums
					+ "krcount) , min(fformula" + nums + "krcount) \n";

			str1[3] = str1[3] + ",max(fformula" + nums
					+ "krcount) , min(fformula" + nums + "krcount) \n";

			str2 = str2 + ",fformula" + nums + "krcount \n";
		}

		String hql = "from RedFormulaCaclMulForcast a where a.forcastSpaceNum = ? "
				+ " and a.spaceNum=? and a.toSsqIndex=?";
		List<BaseEntity> list = basedao.find(hql, new Object[] { forcastNum,
				spaceNum, currentIndex });
		int beginssqindex = ((RedFormulaCaclMulForcast) list.get(0))
				.getFromSsqIndex();

		str1[0] = str1[0] + " from t_redfulcaclmulsts where fspacenum = "
				+ spaceNum + " and ffromssqindex >= " + beginssqindex
				+ " and ftossqindex <= " + currentIndex
				+ "  order by ftossqindex desc";

		str1[1] = str1[1] + " from t_redfulcaclmulsts where fspacenum = "
				+ spaceNum + " and ffromssqindex >= " + (beginssqindex - 1000)
				+ " and ftossqindex <= " + currentIndex
				+ "  order by ftossqindex desc";

		str1[2] = str1[2] + " from t_redfulcaclmulsts where fspacenum = "
				+ spaceNum + " and ffromssqindex >= " + (beginssqindex - 2000)
				+ " and ftossqindex <= " + currentIndex
				+ "  order by ftossqindex desc";

		str1[3] = str1[3] + " from t_redfulcaclmulsts where fspacenum = "
				+ spaceNum + " and ffromssqindex >= " + (beginssqindex - 3000)
				+ " and ftossqindex <= " + currentIndex
				+ "  order by ftossqindex desc";

		str2 = str2 + " from t_redfulcaclmulsts where fspacenum = " + spaceNum
				+ " and ftossqindex=" + currentIndex;

		jTextArea2.setText(str2 + ";\n\n" + str1[0] + ";\n\n" + str1[1]
				+ ";\n\n" + str1[2] + ";\n\n" + str1[3] + ";\n\n");
	}

	private void comboForcastNumActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnBlueTxtActionPerformed(java.awt.event.ActionEvent evt) {
		// 生成blue.txt
		int ssqIndex = getCurrentSsqIndex();
		BlueFormulaTest.buildBlue(ssqIndex);
	}

	private void btnRedTxtActionPerformed(java.awt.event.ActionEvent evt) {
		// 生成red.txt
		int ssqIndex = getCurrentSsqIndex();
		RedFormulaTest.buildRed(ssqIndex);
	}

	private void btnBlueIsOddLoseActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = BlueHotLawBuild.buildBlueHotIsEvenLaw(false,
				ssqIndex);

		txtAreaBlueOut.setText("");
		for (String str1 : result) {
			txtAreaBlueOut.append(str1 + "\n");
		}
	}

	private void btnBlueIsEvenLoseActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = BlueHotLawBuild.buildBlueHotIsEvenLaw(true,
				ssqIndex);

		txtAreaBlueOut.setText("");
		for (String str1 : result) {
			txtAreaBlueOut.append(str1 + "\n");
		}
	}

	private void btnTailLoseRepeatActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = TailHotLawBuild.buildTailHotLawWithRepeat(true,
				ssqIndex);

		txtAreaTailOut.setText("");
		for (String str1 : result) {
			txtAreaTailOut.append(str1 + "\n");
		}
	}

	private void btnTailLoseNoRepeatActionPerformed(
			java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = TailHotLawBuild.buildTailHotLawWithRepeat(false,
				ssqIndex);

		txtAreaTailOut.setText("");
		for (String str1 : result) {
			txtAreaTailOut.append(str1 + "\n");
		}
	}

	private void btnTailLose2ActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = TailHotLawBuild.buildTailHotLaw(ssqIndex, 2);

		txtAreaTailOut.setText("");
		for (String str1 : result) {
			txtAreaTailOut.append(str1 + "\n");
		}
	}

	private void btnTailLoseActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = TailHotLawBuild.buildTailHotLaw(ssqIndex, 1);

		txtAreaTailOut.setText("");
		for (String str1 : result) {
			txtAreaTailOut.append(str1 + "\n");
		}
	}

	private void btnBlueLoseActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = BlueHotLawBuild.buildBlueHotLaw(ssqIndex);

		txtAreaBlueOut.setText("");
		for (String str1 : result) {
			txtAreaBlueOut.append(str1 + "\n");
		}
	}

	private void btnRedLoseActionPerformed(java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = RedHotLawBuild.buildRedHotLaw(ssqIndex);

		txtAreaRedOut.setText("");
		for (String str1 : result) {
			txtAreaRedOut.append(str1 + "\n");
		}
	}

	private void btnRedNotContinueLoseActionPerformed(
			java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = RedHotLawBuild.buildRedHotContinueLaw(false,
				ssqIndex);

		txtAreaRedOut.setText("");
		for (String str1 : result) {
			txtAreaRedOut.append(str1 + "\n");
		}
	}

	private void btnRedContinueLoseActionPerformed(
			java.awt.event.ActionEvent evt) {
		int ssqIndex = getCurrentSsqIndex();
		List<String> result = RedHotLawBuild.buildRedHotContinueLaw(true,
				ssqIndex);

		txtAreaRedOut.setText("");
		for (String str1 : result) {
			txtAreaRedOut.append(str1 + "\n");
		}
	}

	private int getCurrentSsqIndex() {
		String str = txtCurrentSsqIndex.getText().trim();
		if (str.length() == 0) {
			JOptionPane.showMessageDialog(this, "请输入当前期！", "错误",
					JOptionPane.WARNING_MESSAGE);
		}

		int ssqIndex = 0;
		try {
			ssqIndex = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "当前期输入错误！", "错误",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		return ssqIndex;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LawUI().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel basePanel;
	private javax.swing.JPanel bluePanel;
	private javax.swing.JCheckBox boxAC;
	private javax.swing.JCheckBox boxFormula;
	private javax.swing.JCheckBox boxHistoryFeature;
	private javax.swing.JCheckBox boxLoseRowCol;
	private javax.swing.JCheckBox boxLoseRowColFeature;
	private javax.swing.JCheckBox boxLoseRowColSpecial;
	private javax.swing.JCheckBox boxResidueFeature;
	private javax.swing.JCheckBox boxSandu;
	private javax.swing.JCheckBox boxStandard;
	private javax.swing.JCheckBox boxSum;
	private javax.swing.JButton btn50Right;
	private javax.swing.JButton btnBlueIsEvenLose;
	private javax.swing.JButton btnBlueIsOddLose;
	private javax.swing.JButton btnBlueLose;
	private javax.swing.JButton btnBlueTxt;
	private javax.swing.JButton btnClear1;
	private javax.swing.JButton btnClear2;
	private javax.swing.JButton btnClear3;
	private javax.swing.JButton btnClear4;
	private javax.swing.JButton btnContinueRight;
	private javax.swing.JButton btnCountLose;
	private javax.swing.JButton btnCreateStats;
	private javax.swing.JButton btnExcludeFileSelect1;
	private javax.swing.JButton btnExcludeFileSelect2;
	private javax.swing.JButton btnExcludeFileSelect3;
	private javax.swing.JButton btnExcludeFileSelect4;
	private javax.swing.JButton btnFilter;
	private javax.swing.JButton btnGenerateSql;
	private javax.swing.JButton btnGetColLose;
	private javax.swing.JButton btnGetRowLose;
	private javax.swing.JButton btnGetStatsInfo;
	private javax.swing.JButton btnHistory;
	private javax.swing.JButton btnMergeColRowLose;
	private javax.swing.JButton btnMulFormula;
	private javax.swing.JButton btnMulFormula1;
	private javax.swing.JButton btnPrimeCountLose;
	private javax.swing.JButton btnRedContinueLose;
	private javax.swing.JButton btnRedInLastLose;
	private javax.swing.JButton btnRedLose;
	private javax.swing.JButton btnRedNotContinueLose;
	private javax.swing.JButton btnRedTxt;
	private javax.swing.JButton btnSumLose;
	private javax.swing.JButton btnTailLose;
	private javax.swing.JButton btnTailLose2;
	private javax.swing.JButton btnTailLoseNoRepeat;
	private javax.swing.JButton btnTailLoseRepeat;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.JComboBox comboCountType;
	private javax.swing.JComboBox comboForcastNum;
	private javax.swing.JComboBox comboSpaceNum;
	private javax.swing.JComboBox comboSumType;
	private javax.swing.JPanel countPanel;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JScrollPane jScrollPane7;
	private javax.swing.JScrollPane jScrollPane8;
	private javax.swing.JScrollPane jScrollPane9;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JLabel labCurrentSsqIndex;
	private javax.swing.JTabbedPane lawTabPanel;
	private javax.swing.JPanel mulFormulaPanel;
	private javax.swing.JPanel myFilterPanel;
	private javax.swing.JPanel otherPanel;
	private javax.swing.JPanel primePanel;
	private javax.swing.JRadioButton raColRowSame;
	private javax.swing.JRadioButton raColRowSpace1;
	private javax.swing.JRadioButton raColSame;
	private javax.swing.JRadioButton raColSpace1;
	private javax.swing.JRadioButton raNotColRowSame;
	private javax.swing.JRadioButton raNotColRowSpace1;
	private javax.swing.JRadioButton raNotColSame;
	private javax.swing.JRadioButton raNotColSpace1;
	private javax.swing.JRadioButton raNotRowSame;
	private javax.swing.JRadioButton raNotRowSpace1;
	private javax.swing.JRadioButton raRowSame;
	private javax.swing.JRadioButton raRowSpace1;
	private javax.swing.JPanel redPanel;
	private javax.swing.JPanel sumPanel;
	private javax.swing.JPanel tailPanel;
	private javax.swing.JTextArea txtAreaBlueOut;
	private javax.swing.JTextArea txtAreaCountOut;
	private javax.swing.JTextArea txtAreaMulFormulaOut;
	private javax.swing.JTextArea txtAreaPrimeOut;
	private javax.swing.JTextArea txtAreaRedOut;
	private javax.swing.JTextArea txtAreaSumOut;
	private javax.swing.JTextArea txtAreaTailOut;
	private javax.swing.JTextField txtBeginSsqIndex1;
	private javax.swing.JTextField txtContinueRightCount1;
	private javax.swing.JTextField txtContinueRightCount2;
	private javax.swing.JTextField txtCurrentSsqIndex;
	private javax.swing.JTextField txtExcludeFile1;
	private javax.swing.JTextField txtExcludeFile2;
	private javax.swing.JTextField txtExcludeFile3;
	private javax.swing.JTextField txtExcludeFile4;
	private javax.swing.JTextField txtRightCount1;
	private javax.swing.JTextField txtRightCount2;
	private javax.swing.JTextField txtSum;
	// End of variables declaration//GEN-END:variables

	private BaseDao basedao = null;
	private SsqRecordDao ssqRecordDao = null;

}