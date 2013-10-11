/**
 * 
 */
package setup.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPage;

/**
 * 抽象的显示多行文本(自动换行)并提供一个必须被点击的按钮的向导页面, 可用来触发执行一些任务并输出执行日志.
 * 
 * 2008-08-03
 * 
 * @author BeanSoft
 * 
 */
public abstract class TextAreaButtonWizardPage extends WizardPage {
	JTextArea textArea = new JTextArea();
	JButton button = new JButton();
	final JCheckBox skipButton = new JCheckBox("跳过此步骤");
	private boolean allowSkip = false;

	public TextAreaButtonWizardPage() {
		// textArea.setFocusable(false);// 如果不希望显示的文字被选择复制，可设置为不可获取焦点
		textArea.setEditable(false);
		textArea.setBackground(getBackground());
		textArea.setLineWrap(true);

		setLayout(new BorderLayout());
		add(new JScrollPane(textArea));

		// 处理主界面按钮点击事件
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				buttonClick();
			}

		});
		add(button, BorderLayout.EAST);
		
		
		// 处理跳过按钮点击事件
		skipButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setAllowSkip(skipButton.isSelected());
				
				setProblem( validateContents(null, null) );
			}

		});
		
		
		skipButton.setEnabled(false);
		add(skipButton, BorderLayout.SOUTH);
		
	}
	
	/**
	 * 允许跳过步骤按钮.
	 */
	public void enableSkip() {
		skipButton.setEnabled(true);
	}
	
	/**
	 * 覆盖此方法并显示提示信息, 必须点击 "按钮" 才可继续向下进行.
	 */
	protected String validateContents(Component component, Object event) {
		if(isAllowSkip()) {
			return null;
		}
		
		return "请点击 " + button.getText() + " 按钮以继续";
	}

	/**
	 * 设置提示文字.
	 * @param text
	 */
	public void setText(String text) {
		textArea.setText(text);
	}

	/**
	 * 设置按钮文字.
	 * @param text
	 */
	public void setButtonText(String text) {
		button.setText(text);
	}

	/**
	 * 添加一行文字
	 * @param text
	 */
	public void appendLine(String text) {
		textArea.append(text + "\n");
	}

	/**
	 * 提供按钮点击时被调用的回调方法.
	 */
	public abstract void buttonClick();

	/**
	 * 获得按钮.
	 * @return
	 */
	public JButton getJButton() {
		return button;
	}

	/**
	 * @return the allowSkip
	 */
	public boolean isAllowSkip() {
		return allowSkip;
	}

	/**
	 * @param allowSkip the allowSkip to set
	 */
	public void setAllowSkip(boolean allowSkip) {
		this.allowSkip = allowSkip;
	}

}
