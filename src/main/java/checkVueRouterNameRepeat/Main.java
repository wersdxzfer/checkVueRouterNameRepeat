package checkVueRouterNameRepeat;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 张超
 * main方法，程序入口。
 */
public class Main {
	
	/**
	 * 打印集合
	 * @param <T>
	 * @param collection 集合。
	 */
	private static <T> void print(final Collection<T> collection){
		for (T f : collection) {
			System.out.println(f);
		}
	}
	
	public static void main(String[] args) {
		String[] folders = ConfigUtils.read();
		if (folders == null) {
			throw new RuntimeException("配置文件有错误");
		}
		
		List<File> list = new ArrayList<File>();
		for (String folderName : folders){
			// 搜索js文件
			List<File> tmpList = SearchFileUtils.search(folderName, (file)->{
				String tmpName = file.getName();
				// 后缀是.js的文件全部筛选出来。
				if (tmpName.endsWith(".js")) {
					return true;
				}
				return false;
			});
			list.addAll(tmpList);
		}
		// 找出重复的路由名称
		findRepeat(list);
	}
	
	/**
	 * 找出重复的路由名称
	 * @param list 文件列表
	 */
	static void findRepeat(final List<File> list){
		// 标记是否路由名称重复
		boolean isRepeated = false;
		// 存放不重复的路由名称
		List<String> noRepeatList = new ArrayList<String>();
		for (File file : list) {
			List<String> nameList = RouterFileUtils.readRouterNames(file.getAbsolutePath());
			for (String name : nameList) {
				if (!noRepeatList.isEmpty() && noRepeatList.contains(name)) {
					System.out.println("重复的路由名称是：  " + name);
					print(findRepeatFile(list, name));
					isRepeated = true;
				} else {
					noRepeatList.add(name);
				}
			}
		}
		if (!isRepeated) {
			System.out.println("路由名称没有重复。");
		}
	}
	
	/**
	 * 查找出包含指定路由名称的路由文件。
	 * @param list 文件列表
	 * @param name 路由名称
	 * @return 包含指定路由名称的路由文件的文件路径
	 */
	static List<String> findRepeatFile(final List<File> list, String name){
		ArrayList<String> r = new ArrayList<String>();
		for (File file : list) {
			// 找出文件中所有的路由名称。
			List<String> nameList = RouterFileUtils.readRouterNames(file.getAbsolutePath());
			if (nameList.contains(name)) {
				r.add(file.getAbsolutePath());
			}
		}
		r.trimToSize();
		return r;
	}

}
