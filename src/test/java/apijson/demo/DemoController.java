package apijson.demo;

import static apijson.framework.APIJSONConstant.FORMAT;
import static apijson.framework.APIJSONConstant.USER_ID;
import static apijson.framework.APIJSONConstant.VERSION;

import com.alibaba.fastjson.JSONObject;

import apijson.StringUtil;
import apijson.demo.model.Privacy;
import apijson.demo.model.User;
import apijson.demo.model.Verify;
import apijson.framework.APIJSONController;
import apijson.framework.HttpSession;
import apijson.orm.exception.ConditionErrorException;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

public class DemoController extends APIJSONController<Long> {

	public static final String USER_;
	public static final String PRIVACY_;
	public static final String VERIFY_; // 加下划线后缀是为了避免 Verify 和 verify 都叫VERIFY，分不清
	static {
		USER_ = User.class.getSimpleName();
		PRIVACY_ = Privacy.class.getSimpleName();
		VERIFY_ = Verify.class.getSimpleName();
	}

	public static final String PHONE = "phone";
	public static final String PASSWORD = "password";
	public static final String TYPE = "type";
	public static final int LOGIN_TYPE_PASSWORD = 0;// 密码登录
	public static final int LOGIN_TYPE_VERIFY = 1;// 验证码登录

	public static final String LOGIN = "login";
	public static final String REMEMBER = "remember";
	public static final String DEFAULTS = "defaults";
	public static final String TOKEN = "token";

	/**
	 * 登录：必填phone,password，全局参数在登录后的请求都生效
	 * 
	 * @param body    {"phone":"13000038710","password":"66666"},type=0密码1验证码,version+format+defaults全局参数
	 * @param session
	 */
	public JSONObject login(String body, HttpSession session) {
		JSONObject requestObject = null;
		String phone, password;
		boolean isPassword;
		int version;
		Boolean format;
		boolean remember;
		JSONObject defaults;
		try {
			requestObject = DemoParser.parseRequest(body);
			isPassword = requestObject.getIntValue(TYPE) == LOGIN_TYPE_PASSWORD;// 登录方式
			phone = requestObject.getString(PHONE);// 手机
			password = requestObject.getString(PASSWORD);// 密码

			if (StringUtil.isPhone(phone) == false) {
				throw new IllegalArgumentException("手机号不合法！");
			}

			if (isPassword) {
				if (StringUtil.isPassword(password) == false) {
					throw new IllegalArgumentException("密码不合法！");
				}
			} else {
				if (StringUtil.isVerify(password) == false) {
					throw new IllegalArgumentException("验证码不合法！");
				}
			}

			version = requestObject.getIntValue(VERSION);
			format = requestObject.getBoolean(FORMAT);
			remember = requestObject.getBooleanValue(REMEMBER);
			defaults = requestObject.getJSONObject(DEFAULTS); // 默认加到每个请求最外层的字段
			requestObject.remove(VERSION);
			requestObject.remove(FORMAT);
			requestObject.remove(REMEMBER);
			requestObject.remove(DEFAULTS);

			// 根据熟练程度考虑是否参照示例以json的方式查库，这里使用hutool.db直接联表查询更简单，密码可以考虑hash保密，verify验证码暂不实现
			Entity entity = Db.use().queryOne(
					"select * from apijson_user u left join apijson_privacy p on u.id=p.id where p.phone=? and p._password=?",
					phone, password);
			if (entity == null) {
				return DemoParser.newErrorResult(new ConditionErrorException("账号或密码错误"));
			}

			User user = entity.toBean(User.class);
			Privacy privacy = entity.toBean(Privacy.class);

			// 可以使用hutool.jwt计算token
			String jwt = "" + user.getId();
			session = DemoSession.of(user.getId());

			// 登录状态保存至session
			super.login(session, user, version, format, defaults);
			session.setAttribute(USER_ID, user.getId()); // 用户id
			session.setAttribute(TYPE, isPassword ? LOGIN_TYPE_PASSWORD : LOGIN_TYPE_VERIFY); // 登录方式
			session.setAttribute(USER_, user); // 用户
			session.setAttribute(PRIVACY_, privacy); // 用户隐私信息
			session.setAttribute(REMEMBER, remember); // 是否记住登录

			JSONObject response = new JSONObject();
			response.put(USER_, user);
			response.put(PRIVACY_, privacy);
			response.put(REMEMBER, remember);
			response.put(DEFAULTS, defaults);
			response.put(TOKEN, jwt);
			return response;
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}
	}

	public JSONObject logout(HttpSession session) {
		try {
			super.logout(session);
			return DemoParser.newSuccessResult();
		} catch (Exception e) {
			return DemoParser.newErrorResult(e);
		}
	}

	/**
	 * 重载配置，需要登录管理员权限
	 * 
	 * @param request {"type":"ALL"}，重载对象，ALL, FUNCTION, REQUEST, ACCESS，非必须
	 * @param session
	 */
	public JSONObject reload(String request, HttpSession session) {
		JSONObject requestObject = null;
		String type;
		try {
			// 要求管理员权限，不同于demo里校验码的方式
			DemoVerifier.verifyAdmin(session);
			requestObject = DemoParser.parseRequest(request);
			type = requestObject.getString(TYPE);
			return super.reload(type);
		} catch (Exception e) {
			return DemoParser.extendErrorResult(requestObject, e);
		}
	}
}
