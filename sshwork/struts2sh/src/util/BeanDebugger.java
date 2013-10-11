package util;
import java.beans.PropertyDescriptor;
import java.util.Vector;

/**
 * Debugger at runtime, internal used by BeanSoft.
 */
public class BeanDebugger {
    /**
     * 调试, 打印出给定 Bean 的所有属性的取值.
     * @date 2005-07-31
     * @author BeanSoft
     * @param bean 需要调试的对象
     */
    public static void dump(Object bean) {
        java.beans.PropertyDescriptor[] descriptors =
            getAvailablePropertyDescriptors(bean);

        for(int i = 0; descriptors != null && i < descriptors.length; i++) {
            java.lang.reflect.Method readMethod = descriptors[i].getReadMethod();

            try {
                Object value = readMethod.invoke(bean, null);
                System.out.println("[" + bean.getClass().getName() + "]." +
                        descriptors[i].getName() + "(" + 
                        descriptors[i].getPropertyType().getName() + ") = "
                        + value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	/**
	 * 从 bean 中读取有效的属性描述符.
	 *
	 * NOTE: 名称为 class 的 PropertyDescriptor 被排除在外.
	 *
	 * @param bean
	 *            Object - 需要读取的 Bean
	 * @return PropertyDescriptor[] - 属性列表
	 */
	public static java.beans.PropertyDescriptor[] getAvailablePropertyDescriptors(
			Object bean) {
		try {
			// 从 Bean 中解析属性信息并查找相关的 write 方法
			java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(bean
					.getClass());
			if (info != null) {
				java.beans.PropertyDescriptor pd[] = info
						.getPropertyDescriptors();
				Vector columns = new Vector();

				for (int i = 0; i < pd.length; i++) {
					String fieldName = pd[i].getName();

					if (fieldName != null && !fieldName.equals("class")) {
						columns.add(pd[i]);
					}
				}

				java.beans.PropertyDescriptor[] arrays = new java.beans.PropertyDescriptor[columns
						.size()];

				for (int j = 0; j < columns.size(); j++) {
					arrays[j] = (PropertyDescriptor) columns.get(j);
				}

				return arrays;
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
		return null;
	}

}