package tuan.kul.common;

import com.google.gson.Gson;

public class JsonUtils {

	public static <T> String convertObjectToString(T object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}
}
