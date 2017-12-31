package checkVueRouterNameRepeat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

/**
 * 处理路由文件的工具类。
 * @author 张超
 *
 */
public final class RouterFileUtils {
	
	static final String NAME_STR = "name";
	
	/**
	 * 按行读取文件，每行文字构成一个字符串。返回一个由每行字符串构成的列表。
	 * @param filePath 文件路径。
	 * @return 返回ArrayList<String>。列表中的每个字符串对应着文件中的每行文字。
	 */
	static final private ArrayList<String> readAsStringList(String filePath){
		ArrayList<String> r = new ArrayList<String>();
		File f = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String str = null;
			
			while(null != (str = br.readLine())){
				r.add(str.trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		r.trimToSize();
		return r;
	}
	
	/**
	 * 删除js代码中的注释
	 * @param strList
	 */
	static final void deletContent(ArrayList<String> strList){
		// 删除行注释。
		int size = strList.size();
		for (int i = size-1; i >= 0; i--) {
			String str = strList.get(i);
			if (str.startsWith("//")) {
				strList.remove(i);
			} else if (str.contains("//")) {
				int index = str.indexOf("//");
				str = str.substring(0, index-1);
				strList.set(i, str);
			}
		}
		
		// 删除注释 /* .. */
		size = strList.size();
		boolean isInContent = false;
		ArrayList<Integer> lineNoList = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			String str = strList.get(i);
			if (str.startsWith("/*")) {
				isInContent = true;
				lineNoList.add(new Integer(i));
			}
			if (isInContent) {
				lineNoList.add(new Integer(i));
			}
			if (str.endsWith("*/")) {
				lineNoList.add(new Integer(i));
				isInContent = false;
			}
		}
		
		for (int i = size-1; i >= 0; i--) {
			if (lineNoList.contains(i)) {
				strList.remove(i);
			}
		}
	}
	
	/**
	 * 找到指定文件中，所有的路由名称。
	 * @param filePath 文件路径
	 * @return 路由名称组成的java.util.List.
	 */
	static final List<String> readRouterNames(String filePath){
		List<String> routerNames = new ArrayList<String>();
		ArrayList<String> strList = readAsStringList(filePath);
		// 删除评论
		deletContent(strList);
		for (String str : strList) {
			str = str.trim();
			// 找到开头是name的行
			if (str.startsWith(NAME_STR)) {
				// 去掉 name
				str = str.substring(NAME_STR.length());
				// 去掉name和冒号之间可能存在的空格。
				str = str.trim();
				// 排除这种情况：这一行代码开头是name，但是name后面没有冒号。
				if (str.startsWith(":")) {
					// 去掉冒号
					str = str.substring(1);
					// 去掉冒号和引号（包括单引号和双引号）之间的空格
					str = str.trim();
					// 排除这种情况：冒号后面没有跟着引号（包括单引号和双引号）。
					if (str.startsWith("'") || str.startsWith("\"")) {
						// 去掉左侧引号
						str = str.substring(1);
						str = str.trim();
						
						// 如果存在右侧逗号，就去除右侧逗号
						if (str.endsWith(",")) {
							str = str.substring(0, str.length()-1);
							str = str.trim();
						}
						// 去除右侧引号（包括单引号和双引号）
						if (str.endsWith("'") || str.endsWith("\"")) {
							str = str.substring(0, str.length() - 1);
							str = str.trim();
							routerNames.add(str);
						}
						
					}
				}
			}
		} // end for
		return routerNames;
	}
}
