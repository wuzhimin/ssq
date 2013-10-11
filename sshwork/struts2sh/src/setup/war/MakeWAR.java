package setup.war;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * 根据给定目录生成WAR文件，并可选择将此WAR发布到Tomcat下.
 * @author BeanSoft
 *
 */
public class MakeWAR extends JFrame
	implements ActionListener
{

	// 标题
	private static final String TITLE = "WAR打包和发布工具 by BeanSoft";
	/** WAR 文件名字 TODO 请修改此文件名 */
	private static final String WAR_FILE = "struts2sh.war";
	private static ZipOutputStream zipOutputStream;
	private static JProgressBar progressBar;
	private JFileChooser warFileChooser;
	private JTextField warTextField;
	private static byte buf[] = new byte[1024];
	private static int len;
	private static int totalFileCount;
	private static int doneFileCount;

	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		new MakeWAR();
	}

	public MakeWAR()
	{
		super(TITLE);
		warTextField = new JTextField(40);
		warFileChooser = new JFileChooser();
		warFileChooser.setFileSelectionMode(1);
		File warDir = new File("WebRoot");
		if (warDir.exists())
		{
			try
			{
				warTextField.setText(warDir.getCanonicalPath());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			warFileChooser.setCurrentDirectory(warDir.getAbsoluteFile().getParentFile());
		}
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}

		}
);
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
		mainPanel.setLayout(new BoxLayout(mainPanel, 1));
		JPanel dirPanel = new JPanel();
		dirPanel.setBorder(BorderFactory.createTitledBorder("选中需要打包为" + WAR_FILE + "的目录"));
		dirPanel.add(warTextField);
		JButton button = new JButton("浏览...");
		button.addActionListener(this);
		dirPanel.add(button);
		JPanel panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel3.setLayout(new BorderLayout());
		JButton startButton = new JButton("生成 WAR");
		startButton.addActionListener(this);
		panel3.add(startButton, "East");
		mainPanel.add(dirPanel);
		mainPanel.add(panel3);
		getContentPane().add(mainPanel);
//		pack();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screen.width / 2 , screen.height / 2);
		
		Dimension size = getSize();
		setLocation((screen.width - size.width) / 2, (screen.height - size.height) / 2);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("浏览..."))
		{
			warFileChooser.setCurrentDirectory(new File(warTextField.getText()));
			int returnVal = warFileChooser.showDialog(this, "选择目录");
			if (returnVal == 0)
				try
				{
					warTextField.setText(warFileChooser.getSelectedFile().getCanonicalFile().getPath());
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
		} else
		if (e.getActionCommand().equals("生成 WAR"))
		{
			final File warDir = new File(warTextField.getText());
			if (!(new File(warDir, "WEB-INF")).exists())
			{
				String msg = "您选中的目录不是有效的Web应用目录.\n请选择其他目录.";
				JOptionPane.showMessageDialog(this, msg, "错误!", 0);
			} else
			{
				countFiles(warDir);
				progressBar = new JProgressBar(0, totalFileCount);
				progressBar.setValue(0);
				progressBar.setStringPainted(true);
				setVisible(false);
				getContentPane().removeAll();
				JPanel panel = new JPanel();
				panel.add(progressBar);
				getContentPane().add(panel);
				//pack();
				setVisible(true);
				final JFrame frame = this;
				Thread t = new Thread() {

					public void run()
					{
						try
						{
							File warFile = new File(WAR_FILE);
							MakeWAR.makeZip(warDir, warFile);
							String msg = "成功打包为WAR文件, 存放路径为: " + warFile.getCanonicalPath();
							JOptionPane.showMessageDialog(frame, msg, "WAR 生成完毕", -1);
							
							// 发布 WAR 包
							String tomcatPath = JOptionPane.showInputDialog(frame, "请输入 Tomcat 的安装目录以发布此项目,如果不希望发布,请选择取消.");
							
							if(tomcatPath != null) {
								File tomcatDir = new File(tomcatPath);
								if (!(new File(tomcatDir, "webapps")).exists())
								{
									JOptionPane.showMessageDialog(frame, "您选中的目录不是有效的Tomcat应用目录.\n请选择其他目录.", "错误!", 0);
								} else {
									util.FileOperate.copyFile(warFile.getCanonicalPath(), new File(tomcatDir, "webapps" + File.separator + WAR_FILE).getCanonicalPath());
									JOptionPane.showMessageDialog(frame, "项目成功发布到了如下的Tomcat目录下:" + tomcatPath + ", 您现在可以启动服务器来查看结果.", "项目发布完毕", -1);
								}
							}
						}
						catch (Exception e)
						{
							System.err.println(e);
						}
						System.exit(0);
					}

				}
;
				t.start();
			}
		}
	}

	/**
	 * 递归生成 JAR 文件.
	 * @param directory
	 * @param zipFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void makeZip(File directory, File zipFile)
		throws IOException, FileNotFoundException
	{
		// 删除老文件
		if(zipFile.exists()) {
			zipFile.delete();
		}
		
		zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
		String fileNames[] = directory.list();
		if (fileNames != null)
		{
			for (int i = 0; i < fileNames.length; i++)
				recurseFiles(new File(directory, fileNames[i]), "");

		}
		zipOutputStream.close();
	}

	/**
	 * 计算文件总数.
	 * @param file
	 */
	private static void countFiles(File file)
	{
		if (file.isDirectory())
		{
			String fileNames[] = file.list();
			if (fileNames != null)
			{
				for (int i = 0; i < fileNames.length; i++)
					countFiles(new File(file, fileNames[i]));

			}
		} else
		{
			totalFileCount++;
		}
	}

	/**
	 * 递归打包文件.
	 * @param file
	 * @param pathName
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void recurseFiles(File file, String pathName)
		throws IOException, FileNotFoundException
	{
		if (file.isDirectory())
		{
			pathName = pathName + file.getName() + "/";
			zipOutputStream.putNextEntry(new ZipEntry(pathName));
			String fileNames[] = file.list();
			if (fileNames != null)
			{
				for (int i = 0; i < fileNames.length; i++)
					recurseFiles(new File(file, fileNames[i]), pathName);

			}
		} else
		{
			ZipEntry zipEntry = new ZipEntry(pathName + file.getName());
			System.out.println(pathName + "  " + file.getName());
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fin);
			zipOutputStream.putNextEntry(zipEntry);
			while ((len = in.read(buf)) >= 0) 
				zipOutputStream.write(buf, 0, len);
			in.close();
			zipOutputStream.closeEntry();
			doneFileCount++;
			progressBar.setValue(doneFileCount);
		}
	}

}
