package apijson.hutool;

import java.io.File;

import org.junit.Test;

import cn.hutool.core.io.FileUtil;

public class SimpleTest {
	/**
	 * 处理apijson/MySQL/single下的sys_{table}.sql文件，也可以直接复制后替换\"为"，拆分语句可选
	 */
	@Test
	public void testMySQL() {
		File dir = new File("apijson/MySQL/single");
		String file = "sys_Access.sql";
		// 打开注释处理其他文文件
		file = "sys_Function.sql";
		file = "sys_Request.sql";
		file = "sys_Document.sql";
		file = "Auto/sys_Script.sql";
		file = "Demo/sys_Moment.sql";
//		file = "Demo/sys_Comment.sql";

		FileUtil.readUtf8Lines(new File(dir, file)).forEach(line -> {
			if (line != null && line.startsWith("INSERT INTO")) {
				line = line.replaceAll("\\\\\"", "\"");// 去掉双引号前面的反斜杠：\" => "
				int p1 = line.indexOf('('), p2 = 0;
				String insert = line.substring(0, p1);
				do {
					p2 = line.indexOf("),", p1);
					if (p2 < 0)
						break;
					System.out.println(insert + line.substring(p1, p2) + ");");
					p1 = line.indexOf('(', p2);
				} while (p2 > 0);
				System.out.println(insert + line.substring(p1));
			}
		});
	}
}
