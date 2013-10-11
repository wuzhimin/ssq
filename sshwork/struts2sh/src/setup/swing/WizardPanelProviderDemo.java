package setup.swing;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardException;
import org.netbeans.spi.wizard.WizardPage;
import org.netbeans.spi.wizard.WizardPanelProvider;

public class WizardPanelProviderDemo 
              extends WizardPanelProvider {
    
   private static final String ID = "page1-id";
    
   public WizardPanelProviderDemo() {
      super("A Demo Wizard", 
                new String [] {ID}, 
                        new String [] {"Page #1"});
   }

   protected JComponent createPanel(
                     final WizardController wizardController,
                                         String str, final Map map) {
      if (str.equals(ID) == false) {
         System.err.println("I should                          never need to print that!!");
         return null;
      } else {
         JPanel p = new JPanel(
                             new FlowLayout(FlowLayout.LEADING));
         final JCheckBox cb = 
                  new JCheckBox("You must select me to finish", false);
         ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               boolean sel = cb.isSelected();
               wizardController.setProblem(
                                 sel ? null : "please select the checkbox");
               map.put("boxSelected",  
                                      sel ? Boolean.TRUE : Boolean.FALSE);
            }
         };
         cb.addActionListener(al);
         al.actionPerformed(new ActionEvent(cb,
                         0, cb.getActionCommand()));
         p.add(cb);
         return p;
      }
   }
    
   protected Object finish(Map settings) throws WizardException {
      Set keys = settings.keySet();
      Iterator it = keys.iterator();
      while (it.hasNext()) {
         Object key = it.next();
         System.out.println(key + "=" + settings.get(key));
      }
      return settings;
   }
    
   public static void main(String [] args) {
      WizardPanelProvider provider = new WizardPanelProviderDemo();
      Wizard wizard = provider.createWizard();
      Object result = WizardDisplayer.showWizard(wizard);
   }
}